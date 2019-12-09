package Projeto;

import java.util.Date;

public abstract class Scholar extends Person {

    private Date startDate;
    private Date finalDate;
    private int index;

    public Scholar(Date startDate, Date finalDate, int index) {
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.index=index;
    }

    public Scholar(String name, String eMail, Date startDate, Date finalDate, int index) {
        super(name, eMail);
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.index=index;
    }

    abstract int calcCost();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

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
