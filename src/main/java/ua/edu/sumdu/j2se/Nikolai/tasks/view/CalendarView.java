package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class CalendarView implements View{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(CalendarView.class);

    @Override
    public long printInfo() {
        System.out.println("Chose activity");
        System.out.println("1) Set new interval");
        System.out.println("2) Add/Edit task");
        System.out.println("3) Delete task");
        System.out.println("4) Back");
        try {
            reader.mark(1);
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            log.fatal(" CalendarView ",e);
            return printInfo();
        } catch (NumberFormatException e) {
            log.warn("CalendarView",e);
            System.out.println("Incorrect input please try again(1 2 3 or 4)");
            return printInfo();
        }
    }
}
