package Projeto;

import java.util.Date;

public class Development extends  Task {
    public Development() {

    }

    public Development(String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible,  int index) {
        super(name, startDate, endDate, duration, conclusionState, responsible, index);
    }

    public double returnEffortRate(){
        return 1.00;
    }

    @Override
    public String toString() {
        return "Development." + super.toString();
    }
}
