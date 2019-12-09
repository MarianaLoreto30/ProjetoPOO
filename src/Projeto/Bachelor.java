package Projeto;

import java.util.ArrayList;
import java.util.Date;

public class Bachelor extends Scholar {

    private ArrayList<Teacher> adivisor;

    public Bachelor(Date startDate, Date finalDate) {
        super(startDate, finalDate);
    }

    public Bachelor(String name, String eMail, Date startDate, Date finalDate) {
        super(name, eMail, startDate, finalDate);
    }


    public int calcCost(){
        return 500;
    }

}
