package ua.edu.sumdu.j2se.Nikolai.tasks.controller;

import ua.edu.sumdu.j2se.Nikolai.tasks.view.View;

public abstract class Controller {
    protected View view;
    protected int actionToDo;

    public Controller(View view, int actionToDo) {
        this.view = view;
        this.actionToDo = actionToDo;
    }

    public boolean canProcess(int action) { return action == actionToDo; }

    public abstract int process();
}
