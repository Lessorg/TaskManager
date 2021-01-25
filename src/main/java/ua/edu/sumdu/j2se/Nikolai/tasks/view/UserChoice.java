package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.StringsVariables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserChoice {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(InputFirstDate.class);

    public static boolean printInfo() {
        boolean temp = false;
        System.out.println("Do you want to change task? (yes/no)");
        try {
            reader.mark(3);
            String input;
            input = reader.readLine();

            if(input.equals("yes") || input.equals("Yes")) {
                temp = true;
            } else if (input.equals("no") || input.equals("No")) {
            } else {
                System.out.println(StringsVariables.incorrectInput);
                return printInfo();
            }
        } catch (IOException e) {
            log.fatal(e.getMessage(),e);
            return printInfo();
        }
        return temp;
    }
}
