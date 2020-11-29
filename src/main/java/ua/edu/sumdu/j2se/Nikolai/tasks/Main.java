package ua.edu.sumdu.j2se.Nikolai.tasks;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {

		Task A = new Task("A", 10);
		Task B = new Task("B", 20);
		LinkedTaskList listA = new LinkedTaskList();
		listA.add(A);
		System.out.println("list: " + listA);
		listA.remove(A);
		System.out.println("list: " + listA);
		listA.add(B);
		System.out.println("list: " + listA);
	}
}
