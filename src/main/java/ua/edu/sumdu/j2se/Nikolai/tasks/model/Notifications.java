package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Nikolai.tasks.controller.MainController;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class Notifications implements Runnable {
    private static final Logger log = Logger.getLogger(LinkedTaskList.class);
    private static LocalDateTime comingTaskTime;
    private static AbstractTaskList taskList;

    public Notifications(AbstractTaskList taskList) {
        Notifications.taskList = taskList;
    }

    @Override
    public void run() {
        comingTaskTime = comingTasksTime();

        do {
            if (LocalDateTime.now().isEqual(comingTaskTime)) {
                System.out.println(comingTasks());
                if (SystemTray.isSupported()) {
                    try {
                        displayMessage(comingTasks());
                    } catch (AWTException e) {
                        log.warn(e);
                    }
                }
            }
        }
        while (MainController.isIsEnd());
    }

    public static void changeComingTaskTime() {
        if (comingTaskTime.compareTo(comingTasksTime()) != 0)
            comingTaskTime = comingTasksTime();
    }

    private static LocalDateTime comingTasksTime() {
        for (SortedMap.Entry<LocalDateTime, Set<Task>> entry : Tasks.calendar(taskList, LocalDateTime.now(),
                LocalDateTime.MAX).entrySet()) {
            return entry.getKey();
        }
        return LocalDateTime.MAX;
    }

    private static String comingTasks() {
        StringBuilder tasks = new StringBuilder(comingTaskTime.getHour() + ":" +
                comingTaskTime.getMinute() + ":" +
                comingTaskTime.getSecond() + " ");
        for (SortedMap.Entry<LocalDateTime, Set<Task>> entry : Tasks.calendar(taskList, LocalDateTime.now(),
                LocalDateTime.MAX).entrySet()) {
            if (entry.getKey() == comingTaskTime) {
                for (Task task : entry.getValue()) {
                    tasks.append(" ").append(task.getTitle()).append(", ");
                }
                break;
            }

        }
        return tasks.toString();
    }

    public static void displayMessage(String tasks) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "TaskManager");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(StringsVariables.incomingTasks);
        tray.add(trayIcon);

        trayIcon.displayMessage(StringsVariables.incomingTasks, tasks, TrayIcon.MessageType.INFO);
    }
}
