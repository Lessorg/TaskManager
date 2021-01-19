package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.Task;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.View;

public class DeleteController extends Controller {
    private final AbstractTaskList taskList;
    private final int previousActionToDo;

    public DeleteController(View view, int actionToDo, int previousActionToDo, AbstractTaskList taskList) {
        super(view, actionToDo);
        this.taskList = taskList;
        this.previousActionToDo = previousActionToDo;
    }

    @Override
    public int process() {
        int id = (int)view.printInfo();
        for (Task task:taskList) {
            if (task.getId() == id)
                taskList.remove(task);
        }
        return previousActionToDo;
    }
}
