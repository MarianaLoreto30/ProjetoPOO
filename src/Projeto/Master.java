package Projeto;

import java.util.ArrayList;
import java.util.Date;

public class Master extends  Scholar {

    private ArrayList<Teacher> adivisor;

    public Master(Date startDate, Date finalDate, int index) {
        super(startDate, finalDate, index);
    }

    public Master(String name, String eMail, Date startDate, Date finalDate, int index) {
        super(name, eMail, startDate, finalDate, index);
    }

    public int calcCost(){
        return 800;
    }
}
