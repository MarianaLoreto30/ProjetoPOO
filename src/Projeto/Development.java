package Projeto;

import java.util.Date;

public class Development extends  Task {
    public Development() {

    }

    public Development(String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible, double effortRate, int index) {
        super(name, startDate, endDate, duration, conclusionState, responsible, effortRate, index);
    }

    /*public boolean checkOverload(){
        return false;
    }*/
}
