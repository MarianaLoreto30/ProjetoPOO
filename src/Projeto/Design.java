package Projeto;

import java.util.Date;

public class Design extends Task {

    public Design() {
    }

    public Design(Date startDate, Date endDate, int duration, int conclusionState, Person responsible, float effortRate) {
        super(startDate, endDate, duration, conclusionState, responsible, effortRate);
    }

    public boolean checkOverload(){
        return false;
    }
}
