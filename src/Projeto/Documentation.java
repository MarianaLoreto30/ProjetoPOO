package Projeto;

import java.util.Date;

public class Documentation extends Task {

    public Documentation() {
    }

    public Documentation(String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible,  int index) {
        super(name, startDate, endDate, duration, conclusionState, responsible, index);
    }

    public double returnEffortRate(){
        return 0.25;
    }

    @Override
    public String toString() {
        return "Documentation." + super.toString();
    }
}
