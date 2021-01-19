package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class TaskInputView {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(TaskInputView.class);

    public static Task inputTask(int id) {
        String title = inputTitle();
        Boolean isRepeated = inputIsRepeated();
        if (isRepeated != null && isRepeated) {
            LocalDateTime startTime = inputStartTime();
            LocalDateTime endTime = inputEndTime();
            Integer interval = inputInterval();
            return new Task(id, title, startTime, endTime, interval);
        } else {
            LocalDateTime time = inputTime();return new Task(id, title, time, true);
        }
    }

    public static Task inputTask() {
        String title = inputTitle();
        Boolean isRepeated = inputIsRepeated();
        if (isRepeated != null && isRepeated) {
            LocalDateTime startTime = inputStartTime();
            LocalDateTime endTime = inputEndTime();
            Integer interval = inputInterval();
            return new Task(title, startTime, endTime, interval);
        } else {
            LocalDateTime time = inputTime();
            return new Task(title, time);
        }
    }

    private static String inputTitle() {
        System.out.println("Input task title:");
        try {
            return reader.readLine();
        } catch (IOException e) {
            log.warn("inputTitle",e);
        }
        return "Default task";
    }

    private static Boolean inputIsRepeated() {
        System.out.println("Is task recurring?(true/false)");
        try {
            reader.mark(5);
            return Boolean.parseBoolean(reader.readLine());
        } catch (IOException e) {
            log.warn("inputIsRepeated",e);
            System.out.println("Incorrect input please try again");
            return inputIsRepeated();
        }
    }

    private static LocalDateTime inputTime() {
        System.out.println("Input date (example: 2021-01-18T00:31:12)");
        try {
            reader.mark(20);
            return LocalDateTime.parse(reader.readLine());
        } catch (IOException e) {
            log.fatal("inputTime",e);
            System.out.println("Incorrect input please try again");
            return inputTime();
        } catch (DateTimeParseException e) {
            log.warn("InputLastDate",e);
            System.out.println("Incorrect input please try again");
            return inputTime();
        }
    }

    private static LocalDateTime inputStartTime() {
        System.out.println("Input start date (example: 2021-01-18T00:31:12)");
        try {
            reader.mark(20);
            return LocalDateTime.parse(reader.readLine());
        } catch (IOException e) {
            log.fatal("inputStartTime",e);
            System.out.println("Incorrect input please try again");
            return inputStartTime();
        } catch (DateTimeParseException e) {
            log.warn("InputLastDate",e);
            System.out.println("Incorrect input please try again");
            return inputStartTime();
        }
    }

    private static LocalDateTime inputEndTime() {
        System.out.println("Input end date (example: 2021-01-18T00:31:12)");
        try {
            reader.mark(20);
            return LocalDateTime.parse(reader.readLine());
        } catch (IOException e) {
            log.fatal("inputEndTime",e);
            System.out.println("Incorrect input please try again");
            return inputEndTime();
        } catch (DateTimeParseException e) {
            log.warn("InputLastDate",e);
            System.out.println("Incorrect input please try again");
            return inputEndTime();
        }
    }

    private static Integer inputInterval() {
        System.out.println("Input interval: (hours)");
        try {
            reader.mark(1000);
            return 3600*Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            log.fatal("inputInterval",e);
            return inputInterval();
        } catch (NumberFormatException e) {
            log.warn("inputInterval",e);
            System.out.println("Incorrect input please try again");
            return inputInterval();
        }
    }
}
