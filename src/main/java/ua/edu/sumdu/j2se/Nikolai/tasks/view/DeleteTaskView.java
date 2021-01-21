package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteTaskView implements  View {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(DeleteTaskView.class);
    @Override
    public int printInfo() {
        System.out.println("Input task id what you want to delete");
        try {
            reader.mark(5);
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            log.fatal(" DeleteTaskView ",e);
            return printInfo();
        } catch (NumberFormatException e) {
            log.warn("DeleteTaskView",e);
            System.out.println("Incorrect input please try again");
            return printInfo();
        }
    }
}
