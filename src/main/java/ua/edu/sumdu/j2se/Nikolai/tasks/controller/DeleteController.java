package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.View;

/**
 * Controller delete Task from taskList by Id
 */
public class DeleteController extends Controller {
    private final AbstractTaskList taskList;
    /**
     * ActionToDo from class in which constructor was called
     */
    private final int previousActionToDo;

    /**
     * Constructs and initializes a AddEditTaskController with values (view, actionToDo, previousActionToDo, taskList)
     * @param view main Controller View class
     * @param actionToDo unique Controller number
     * @param previousActionToDo previous Controller actionToDo
     * @param taskList List of tasks
     */
    public DeleteController( View view, int actionToDo, int previousActionToDo, AbstractTaskList taskList) {
        super(view, actionToDo);
        this.taskList = taskList;
        this.previousActionToDo = previousActionToDo;
    }


    /**
     * Get Task id from user and remove Task with such id from taskList if taskList contains this id
     * @return previousActionToDo
     */
    @Override
    public int process() {
        taskList.remove(view.printInfo());

        return previousActionToDo;
    }
}
