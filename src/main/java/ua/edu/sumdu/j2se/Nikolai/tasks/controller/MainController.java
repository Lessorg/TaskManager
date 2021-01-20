package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.*;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    private List<Controller> controllers = new ArrayList<>();
    private static AbstractTaskList taskList;
    private static boolean isEnd = false;

    public MainController(View MainView, AbstractTaskList taskList) {
        super(MainView, ControllerConstants.MainControllerAction);
        MainController.taskList = taskList;

        controllers.add(this);
        controllers.add(new CalendarController(new CalendarView(), ControllerConstants.CalendarControllerAction,
                taskList));
        controllers.add(new AddEditTaskController(new AddEditTaskView(), ControllerConstants.AddEditControllerAction,
                0, taskList));
        controllers.add(new DeleteController(new InputIdView(), ControllerConstants.DeleteControllerAction,
                0, taskList));
        controllers.add(new ExitController(new ExitView(), ControllerConstants.ExitControllerAction));
    }

    @Override
    public int process() {
        Thread tread = new Thread(new Notifications(taskList));
        tread.start();
        int action = (int)view.printInfo();
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

    public static boolean isIsEnd() {
        return isEnd;
    }
}
