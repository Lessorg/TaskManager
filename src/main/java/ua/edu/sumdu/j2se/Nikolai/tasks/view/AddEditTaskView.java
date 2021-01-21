package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import ua.edu.sumdu.j2se.Nikolai.tasks.controller.ControllerConstants;

public class AddEditTaskView implements View {

    @Override
    public int printInfo() {
        System.out.println("Task added or edited");
        return ControllerConstants.MainControllerAction;
    }
}
