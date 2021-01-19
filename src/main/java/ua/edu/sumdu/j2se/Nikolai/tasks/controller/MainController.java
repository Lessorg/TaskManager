package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    private List<Controller> controllers = new ArrayList<>();

    public MainController(View MainView, AbstractTaskList taskList) {
        super(MainView, ControllerConstants.MainControllerAction);

        controllers.add(this);
        controllers.add(new CalendarController(new CalendarView(), ControllerConstants.CalendarControllerAction, taskList));
        controllers.add(new AddEditTaskController(new AddEditTaskView(), ControllerConstants.AddEditControllerAction, 0, taskList));
        controllers.add(new DeleteController(new InputIdView(), ControllerConstants.DeleteControllerAction, 0, taskList));
        controllers.add(new ExitController(new ExitView(), ControllerConstants.ExitControllerAction));
    }

    @Override
    public int process() {
        int action = (int)view.printInfo();
        do {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process();
                }
            }
        } while (action != ControllerConstants.ExitControllerAction);
        return ControllerConstants.ExitControllerAction;
    }
}
