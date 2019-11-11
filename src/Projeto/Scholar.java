package Projeto;

import java.util.Date;

public abstract class Scholar extends Person {

    private Date startDate;
    private Date finalDate;

    public Scholar(Date startDate, Date finalDate) {
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public Scholar(String name, String eMail, Date startDate, Date finalDate, String cost) {
        super(name, eMail);
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    abstract int calcCost();

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

}
