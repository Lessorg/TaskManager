package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.view.View;

public abstract class Controller {
    /**
     * Class which implements View
     */
    protected View view;
    /**
     * unique controller number
     */
    protected int actionToDo;

    /**
     * Constructs and initializes a Controller
     * @param view controller view class
     * @param actionToDo new controller actionToDo parameter
     */
    public Controller(View view, int actionToDo) {
        this.view = view;
        this.actionToDo = actionToDo;
    }

    /**
     * Checks equality of action and actionToDo
     * @param action actionToDo which you need
     * @return true if action equals actionToDo in other cases false
     */
    public boolean canProcess(int action) { return action == actionToDo; }

    /**
     * Main Controller method
     * @return return values from ControllerConstants
     */
    public abstract int process();
}
