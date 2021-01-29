package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Nikolai.tasks.controller.MainController;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Notifications extends Thread {
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
            if (LocalDateTime.now().withSecond(0).withNano(0).isEqual(comingTaskTime)) {
                System.out.println(comingTasks() + " Is coming");
                if (SystemTray.isSupported()) {
                    try {
                        displayMessage(comingTasks());
                    } catch (AWTException e) {
                        log.warn(e);
                    }
                }
                changeComingTaskTime();
            }
        }
        while (!MainController.isIsEnd());
    }

    public static void changeComingTaskTime() {
        if (comingTaskTime.compareTo(comingTasksTime()) != 0)
            comingTaskTime = comingTasksTime().withSecond(0).withNano(0);
    }

    private static LocalDateTime comingTasksTime() {
        SortedMap<LocalDateTime, Set<Task>> map = Tasks.calendar(taskList, LocalDateTime.now(), LocalDateTime.MAX);
        for (SortedMap.Entry<LocalDateTime, Set<Task>> entry:map.entrySet()) {
            if (entry.getKey().compareTo(LocalDateTime.now().withSecond(0).withNano(0))>0)
                return entry.getKey();
        }
        return LocalDateTime.MAX;
    }

    private static String comingTasks() {
        StringBuilder tasks = new StringBuilder(comingTaskTime.getHour() + ":" +
                comingTaskTime.getMinute());

        TreeMap<LocalDateTime, Set<Task>> map = (TreeMap<LocalDateTime, Set<Task>>)
                Tasks.calendar(taskList, LocalDateTime.now().withSecond(0).withNano(0).minusMinutes(2),
                        LocalDateTime.MAX);

        for (SortedMap.Entry<LocalDateTime, Set<Task>> entry:map.entrySet()) {
            if (comingTaskTime.isEqual(entry.getKey())) {
                for (Task task : entry.getValue()) {
                    tasks.append(" ").append(task.getTitle()).append(", ");
                }
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
