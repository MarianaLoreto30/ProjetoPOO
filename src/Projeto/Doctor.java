package Projeto;

import java.util.ArrayList;
import java.util.Date;

public class Doctor extends  Scholar{

    public Doctor(Date startDate, Date finalDate, int index) {
        super(startDate, finalDate, index);
    }

    public Doctor(String name, String eMail, Date startDate, Date finalDate, int index) {
        super(name, eMail, startDate, finalDate, index);
    }

    public int calcCost(){
        return 1000;
    }

}
