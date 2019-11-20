package Projeto;

import java.util.Date;

public class Master extends  Scholar {

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
