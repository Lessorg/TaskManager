package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.view.View;

public class ExitController extends Controller {

    public ExitController(View view, int actionToDo) {
        super(view, actionToDo);
    }

    @Override
    public int process() {
        return (int)view.printInfo();
    }
}
