package ua.edu.sumdu.j2se.Nikolai.tasks.model.observer;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.TaskIO;

public class LinkedListObserver {
    private final LinkedTaskList subject;

    public LinkedListObserver(LinkedTaskList subject) {
        this.subject = subject;
    }

    public void update(){
        TaskIO.SaveTaskList(subject);
    }
}
