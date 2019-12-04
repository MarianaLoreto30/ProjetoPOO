package Projeto;

import java.util.Date;

public class Documentation extends Task {

    public Documentation() {
    }

    public Documentation(String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible, double effortRate) {
        super(name, startDate, endDate, duration, conclusionState, responsible, effortRate);
    }

    public boolean checkOverload(){
        return false;
    }
}
