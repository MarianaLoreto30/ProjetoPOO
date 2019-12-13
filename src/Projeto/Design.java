package Projeto;

import java.util.Date;

public class Design extends Task {

    public Design() {
    }

    public Design(String name,Date startDate, Date endDate, int duration, int conclusionState, Person responsible, int index) {
        super(name, startDate, endDate, duration, conclusionState, responsible, index);
    }

    /**
     * Design's effort rate
     * @return double
     */
    public double returnEffortRate(){
        return 0.50;
    }

    @Override
    public String toString() {
        return "Design." + super.toString();
    }
}
