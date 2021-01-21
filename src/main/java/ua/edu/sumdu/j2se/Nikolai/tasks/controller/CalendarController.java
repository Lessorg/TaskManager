package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.Tasks;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller show all tasks from taskList in given interval
 */
public class CalendarController extends Controller {
    private final AbstractTaskList taskList;
    /**
     * Controllers which may called from this class
     */
    private final List<Controller> controllers = new ArrayList<>();

    /**
     * Constructs and initializes a CalendarController with values (view, actionToDo, taskList)
     * @param view main Controller View class
     * @param actionToDo unique Controller number
     * @param taskList List of tasks
     */
    public CalendarController(View view, int actionToDo, AbstractTaskList taskList) {
        super(view, actionToDo);
        this.taskList = taskList;

        controllers.add(new AddEditTaskController(new AddEditTaskView(), ControllerConstants.AddEditControllerAction, ControllerConstants.CalendarControllerAction, taskList));
        controllers.add(new DeleteController(new DeleteTaskView(), ControllerConstants.DeleteControllerAction, ControllerConstants.CalendarControllerAction ,taskList));
    }

    /**
     * Method uses InputFirstDate and InputLastDate classes to get interval values from user
     * and call next Controller
     * @return ControllerConstants.MainControllerAction if user wants return to mainView or
     * ControllerConstants.CalendarControllerAction in all other cases
     */
    @Override
    public int process() {
        LocalDateTime from = InputFrom();
        LocalDateTime to = InputTo();
        new DatesView(Tasks.calendar(taskList, from, to)).printInfo();

        int action = view.printInfo();
        {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process();
                }
            }
            if (action == ControllerConstants.CalendarControllerAction)
                return ControllerConstants.CalendarControllerAction;
            else
                return ControllerConstants.MainControllerAction;
        }
    }

    /**
     * Get start interval Date
     * @return LocalDateTime, not null
     */
    public LocalDateTime InputFrom() {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(new InputFirstDate().printInfo()),
                ZoneId.of( "Etc/UTC" ));
    }

    /**
     * Get end interval Date
     * @return LocalDateTime, not null
     */
    public LocalDateTime InputTo() {
         return LocalDateTime.ofInstant(Instant.ofEpochSecond(new InputLastDate().printInfo()),
                ZoneId.of( "Etc/UTC" ));
    }
}
