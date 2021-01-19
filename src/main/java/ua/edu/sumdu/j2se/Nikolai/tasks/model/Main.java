package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import ua.edu.sumdu.j2se.Nikolai.tasks.controller.MainController;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.observer.LinkedListObserver;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.MainView;

public class Main {

	public static void main(String[] args) {

		LinkedTaskList list = new LinkedTaskList();
		TaskIO.LoadTaskList(list);
		list.setSaveChecker(new LinkedListObserver(list));

		MainController program = new MainController(new MainView(), list);
		program.process();
	}
}
