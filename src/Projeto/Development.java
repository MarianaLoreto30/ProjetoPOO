package Projeto;

import java.util.Date;

public class Development extends  Task {
    public Development() {

    }

    public Development(Date startDate, Date endDate, int duration, int conclusionState, Person responsible, float effortRate) {
        super(startDate, endDate, duration, conclusionState, responsible, effortRate);
    }

    public boolean checkOverload(){
        return false;
    }
}
