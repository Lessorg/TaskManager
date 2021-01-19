package ua.edu.sumdu.j2se.Nikolai.tasks.model;

import org.apache.log4j.Logger;

public class TaskListFactory {
    private static final Logger log = Logger.getLogger(TaskListFactory.class);

    public static AbstractTaskList createTaskList(ListTypes.types type) {

            switch(type) {
                case ARRAY:
                    return new ArrayTaskList();
                case LINKED:
                    return new LinkedTaskList();
                default:
                    log.fatal(new IllegalAccessException("Incorrect type"));
                    return new LinkedTaskList();
            }
    }
}
