package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {

     public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        LinkedList<Task> taskList = new LinkedList<>();

        for (Task task : tasks) {
            if (task.nextTimeAfter(start) != null && task.nextTimeAfter(start).compareTo(end) <= 0){
                taskList.add(task);
            }
        }
        return taskList;
    }

    static public SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start,
                                                               LocalDateTime end){
        SortedMap<LocalDateTime, Set<Task>> sortedMap = new TreeMap<>();

        for (Task task : incoming(tasks, start, end)) {
            LocalDateTime temp = start;
            int flag = 0;
            while (task.nextTimeAfter(temp) != null && task.nextTimeAfter(temp).compareTo(end) <= 0
                    && (flag < 1 || task.isRepeated())) {
                flag++;
                Set<Task> tasksSet = new HashSet<>();

                if(sortedMap.containsKey(task.nextTimeAfter(temp))) {
                    tasksSet = sortedMap.get(task.nextTimeAfter(temp));
                    tasksSet.add(task);
                }

                tasksSet.add(task);
                sortedMap.put(task.nextTimeAfter(temp), tasksSet);

                temp = temp.plusSeconds(task.getRepeatInterval());
            }
        }
        return sortedMap;
    }
}
