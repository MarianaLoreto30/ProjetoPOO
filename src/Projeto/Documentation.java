package Projeto;

import java.util.Date;

public class Documentation extends Task {

    public Documentation() {
    }

    public Documentation(Date startDate, Date endDate, int duration, int conclusionState, Person responsible, float effortRate) {
        super(startDate, endDate, duration, conclusionState, responsible, effortRate);
    }

    public boolean checkOverload(){
        return false;
    }
}
