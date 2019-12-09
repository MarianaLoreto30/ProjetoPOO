package Projeto;

import java.util.ArrayList;
import java.util.Date;

public class Master extends  Scholar {

    private ArrayList<Teacher> adivisor;

    public Master (Date startDate, Date finalDate) {
        super(startDate, finalDate);
    }

    public Master(String name, String eMail, Date startDate, Date finalDate) {
        super(name, eMail, startDate, finalDate);
    }

    public int calcCost(){
        return 800;
    }
}
