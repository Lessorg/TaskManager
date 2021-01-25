package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {

    public ListTypes type;
    protected static final long serialVersionUID = 2L;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract boolean remove(int id);
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;
    public abstract String toString();
    public abstract Stream<Task> getStream();

}
