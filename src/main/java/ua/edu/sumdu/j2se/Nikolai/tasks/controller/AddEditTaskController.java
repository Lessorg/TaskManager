package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.Task;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.InputIdView;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.TaskInputView;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.View;

public class AddEditTaskController extends Controller{
    private AbstractTaskList taskList;
    private int previousActionToDo;

    public AddEditTaskController(View view, int actionToDo, int previousActionToDo, AbstractTaskList taskList) {
        super(view, actionToDo);
        this.taskList = taskList;
        this.previousActionToDo = previousActionToDo;
    }

    @Override
    public int process() {

        Task newTask = null;
        View inputIdView = new InputIdView();
        int id = (int)inputIdView.printInfo();
        for (Task task : taskList) {
            if (task.getId() == id) {
                taskList.remove(task);
                newTask = TaskInputView.inputTask(id);
                taskList.add(newTask);
                break;
            }
        }
        if (newTask == null) {
            newTask = TaskInputView.inputTask();
            taskList.add(newTask);
        }

        view.printInfo();
        return previousActionToDo;
    }
}
