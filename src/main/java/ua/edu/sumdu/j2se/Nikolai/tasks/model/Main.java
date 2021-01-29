package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import ua.edu.sumdu.j2se.Nikolai.tasks.controller.MainController;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.observer.LinkedListObserver;
import ua.edu.sumdu.j2se.Nikolai.tasks.view.MainView;

public class Main {

	public static void main(String[] args) {

		LinkedTaskList taskList = new LinkedTaskList();
		TaskIO.LoadTaskList(taskList);

		Thread tread = new Thread(new Notifications(taskList));
		tread.start();

		taskList.setSaveChecker(new LinkedListObserver(taskList));

		MainController program = new MainController(new MainView(), taskList);
		program.process();
	}


}
