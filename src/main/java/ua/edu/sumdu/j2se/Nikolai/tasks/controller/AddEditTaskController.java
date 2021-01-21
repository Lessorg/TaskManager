package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.Task;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.InputId;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.TaskInputView;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.View;

/**
 * Controller add Task in taskList or edit Task if inputted ID already exist
 */
public class AddEditTaskController extends Controller{
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
    public AddEditTaskController(View view, int actionToDo, int previousActionToDo, AbstractTaskList taskList) {
        super(view, actionToDo);
        this.taskList = taskList;
        this.previousActionToDo = previousActionToDo;
    }

    /**
     * Method use InputId and TaskInputView to get new Task parameters
     * @return previousActionToDo
     */
    @Override
    public int process() {

        Task newTask = null;
        InputId inputIdView = new InputId();
        int id = inputIdView.inputId();
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
