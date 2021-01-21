package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements View{
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(MainView.class);

    @Override
    public int printInfo(){
        System.out.println("Chose activity");
        System.out.println("1) Calendar");
        System.out.println("2) Add/Edit task");
        System.out.println("3) Delete task");
        System.out.println("4) Exit");
        try {
            reader.mark(1);
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            log.fatal("MainView",e);
            return printInfo();
        } catch (NumberFormatException e) {
            log.warn("MainView",e);
            System.out.println("Incorrect input please try again(1 2 3 or 4)");
            return printInfo();
        }
    }
}
