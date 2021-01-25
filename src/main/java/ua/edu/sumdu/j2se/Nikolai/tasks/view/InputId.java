package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Nikolai.tasks.model.StringsVariables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputId {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger log = Logger.getLogger(InputId.class);

    public int inputId() {
        System.out.println("Input task id:");
        try {
            reader.mark(4);
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            log.fatal(e.getMessage() ,e);
            return inputId();
        } catch (NumberFormatException e) {
            log.warn(e.getMessage(),e);
            System.out.println(StringsVariables.incorrectInput);
            return inputId();
        }
    }
}
