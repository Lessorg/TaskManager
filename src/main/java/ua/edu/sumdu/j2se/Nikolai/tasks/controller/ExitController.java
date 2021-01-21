package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.view.View;

/**
 * Controller finish program work
 */
public class ExitController extends Controller {

    /**
     * Constructs and initializes a ExitController with values (view, actionToDo)
     * @param view main Controller View class
     * @param actionToDo unique Controller number
     */
    public ExitController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    /**
     * Call view.printInfo()
     * @return view.printInfo() returning value
     */
    @Override
    public int process() {
        return view.printInfo();
    }
}
