package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.StringsVariables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputLastDate {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(InputLastDate.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public long printInfo() {
        System.out.println(StringsVariables.endDate);
        LocalDateTime temp;
        try {
            reader.mark(20);
            temp = LocalDateTime.parse(reader.readLine(), formatter);
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
            return printInfo();
        } catch (DateTimeParseException e) {
            log.warn(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return printInfo();
        }
        return temp.toEpochSecond(ZoneOffset.UTC);
    }
}
