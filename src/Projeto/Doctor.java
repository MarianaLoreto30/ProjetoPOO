package Projeto;

import java.util.Date;

public class Doctor extends  Scholar{
    public Doctor(Date startDate, Date finalDate) {
        super(startDate, finalDate);
    }

    public Doctor(String name, String eMail, Date startDate, Date finalDate, String cost) {
        super(name, eMail, startDate, finalDate, cost);
    }

    public int calcCost(){
        return 1000;
    }

}
