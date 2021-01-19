package ua.edu.sumdu.j2se.Nikolai.tasks.view;

import ua.edu.sumdu.j2se.Nikolai.tasks.controller.ControllerConstants;

public class ExitView implements View{

    static int flag = 0;

    @Override
    public long printInfo(){

        if (flag == 0) {
            flag++;
            System.out.println("Goodbye");
        }
        return ControllerConstants.ExitControllerAction;
    }
}
