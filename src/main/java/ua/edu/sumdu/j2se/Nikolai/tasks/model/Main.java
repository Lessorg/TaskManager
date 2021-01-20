package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import ua.edu.sumdu.j2se.Nikolai.tasks.controller.MainController;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.observer.LinkedListObserver;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.MainView;

public class Main {

	public static void main(String[] args) {

		LinkedTaskList TaskList = new LinkedTaskList();
		TaskIO.LoadTaskList(TaskList);
		TaskList.setSaveChecker(new LinkedListObserver(TaskList));

		MainController program = new MainController(new MainView(), TaskList);
		program.process();
	}


}
