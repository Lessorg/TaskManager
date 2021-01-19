package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.Tasks;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class CalendarController extends Controller {
    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();

    public CalendarController(View view, int actionToDo, AbstractTaskList taskList) {
        super(view, actionToDo);
        this.taskList = taskList;

        controllers.add(new AddEditTaskController(new AddEditTaskView(), ControllerConstants.AddEditControllerAction, ControllerConstants.CalendarControllerAction, taskList));
        controllers.add(new DeleteController(new DeleteTaskView(), ControllerConstants.DeleteControllerAction, ControllerConstants.CalendarControllerAction ,taskList));
    }

    @Override
    public int process() {
        LocalDateTime from = InputFrom();
        LocalDateTime to = InputTo();
        new DatesView(Tasks.calendar(taskList, from, to)).printInfo();

        int action = (int)view.printInfo();
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

    public LocalDateTime InputFrom() {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(new InputFirstDate().printInfo()),
                ZoneId.of( "Etc/UTC" ));
    }

    public LocalDateTime InputTo() {
         return LocalDateTime.ofInstant(Instant.ofEpochSecond(new InputLastDate().printInfo()),
                ZoneId.of( "Etc/UTC" ));
    }
}
