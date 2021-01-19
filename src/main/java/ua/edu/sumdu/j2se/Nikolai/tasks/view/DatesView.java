package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import ua.edu.sumdu.j2se.Nikolai.tasks.controller.ControllerConstants;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class DatesView implements View {
    private SortedMap<LocalDateTime, Set<Task>> tasksList;

    public DatesView(SortedMap<LocalDateTime, Set<Task>> tasksList) {
        this.tasksList = tasksList;
    }

    @Override
    public long printInfo() {
        System.out.println("Calendar: ");
        for (SortedMap.Entry<LocalDateTime, Set<Task>> entry:tasksList.entrySet()) {
            for (Task task:entry.getValue()) {
                System.out.println(entry.getKey() + ": " + task.toString());
            }
        }
        return ControllerConstants.MainControllerAction;
    }
}
