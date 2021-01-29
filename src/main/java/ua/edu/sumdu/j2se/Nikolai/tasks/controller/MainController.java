package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.*;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller process program main menu
 */
public class MainController extends Controller {
    /**
     * Controllers which may called from this class
     */
    private final List<Controller> controllers = new ArrayList<>();
    private static AbstractTaskList taskList;
    /**
     * Flag to check is program work ended or not
     */
    private static boolean isEnd = false;

    /**
     * Constructs and initializes a MainController with values (MainView, taskList)
     * and fills controllers with new classes
     * @param MainView main Controller View class
     * @param taskList list of tasks
     */
    public MainController(View MainView, AbstractTaskList taskList) {
        super(MainView, ControllerConstants.MainControllerAction);
        MainController.taskList = taskList;

        controllers.add(this);
        controllers.add(new CalendarController(new CalendarView(), ControllerConstants.CalendarControllerAction,
                taskList));
        controllers.add(new AddEditTaskController(new AddEditTaskView(), ControllerConstants.AddEditControllerAction,
                0, taskList));
        controllers.add(new DeleteController(new DeleteTaskView(), ControllerConstants.DeleteControllerAction,
                0, taskList));
        controllers.add(new ExitController(new ExitView(), ControllerConstants.ExitControllerAction));
    }

    /**
     * process user choice and call corresponding controllers
     * @return ControllerConstants.ExitControllerAction
     */
    @Override
    public int process() {
        int action = view.printInfo();
        do {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process();
                }
            }
        } while (action != ControllerConstants.ExitControllerAction);
        isEnd = true;
        return ControllerConstants.ExitControllerAction;
    }

    /**
     * @return isEnd value
     */
    public static boolean isIsEnd() {
        return isEnd;
    }
}
