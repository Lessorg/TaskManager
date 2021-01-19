package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

public class InputFirstDate implements View{
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(InputFirstDate.class);

    @Override
    public long printInfo() {
        System.out.println("Input start date(format 2021-01-18T00:31:12)");
        LocalDateTime temp;
        try {
            reader.mark(20);
            temp = LocalDateTime.parse(reader.readLine());
        } catch (IOException e) {
            log.fatal("InputFirstDate",e);
            return printInfo();
        }catch (DateTimeParseException e) {
            log.warn("InputFirstDate",e);
            System.out.println("Incorrect input please try again (2021-01-18T00:31:12)");
            return printInfo();
        }
        return temp.toEpochSecond(ZoneOffset.UTC);
    }
}
