package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.StringsVariables;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskInputView {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(TaskInputView.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
            log.warn(e.getMessage(),e);
        }
        return "Default task";
    }

    private static Boolean inputIsRepeated() {
        System.out.println("Is task recurring?(true/false)");
        try {
            reader.mark(5);
            return Boolean.parseBoolean(reader.readLine());
        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputIsRepeated();
        }
    }

    private static LocalDateTime inputTime() {
        System.out.println(StringsVariables.startDate);
        try {
            reader.mark(20);
            return LocalDateTime.parse(reader.readLine(), formatter);
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputTime();
        } catch (DateTimeParseException e) {
            log.warn(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputTime();
        }
    }

    private static LocalDateTime inputStartTime() {
        System.out.println(StringsVariables.startDate);
        try {
            reader.mark(20);
            return LocalDateTime.parse(reader.readLine(), formatter);
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputStartTime();
        } catch (DateTimeParseException e) {
            log.warn(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputStartTime();
        }
    }

    private static LocalDateTime inputEndTime() {
        System.out.println(StringsVariables.endDate);
        try {
            reader.mark(20);
            return LocalDateTime.parse(reader.readLine(), formatter);
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputEndTime();
        } catch (DateTimeParseException e) {
            log.warn(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputEndTime();
        }
    }

    private static Integer inputInterval() {
        System.out.println("Input interval: (hours)");
        try {
            reader.mark(1000);
            return 3600*Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
            return inputInterval();
        } catch (NumberFormatException e) {
            log.warn(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputInterval();
        }
    }
}
