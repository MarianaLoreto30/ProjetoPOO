package Projeto;

import java.util.ArrayList;
import java.util.Date;

public class Bachelor extends Scholar {

    private ArrayList<Teacher> adivisor;

    public Bachelor(Date startDate, Date finalDate, int index) {
        super(startDate, finalDate, index);
    }

    public Bachelor(String name, String eMail, Date startDate, Date finalDate, int index) {
        super(name, eMail, startDate, finalDate, index);
    }


    public int calcCost(){
        return 500;
    }

    public int addTeacher(Teacher t, int index){
        if(projects.get(0).getIndex() == index){
            adivisor.add(t);
            return 1;
        }
        return 0;
    }
}
