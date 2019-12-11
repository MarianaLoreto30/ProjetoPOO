package Projeto;

import java.util.ArrayList;
import java.util.Date;

public class Master extends  Scholar {

    private ArrayList<Teacher> adviser;

    public Master(Date startDate, Date finalDate, int index) {
        super(startDate, finalDate, index);
        this.adviser= new ArrayList<>();
    }

    public Master(String name, String eMail, Date startDate, Date finalDate, int index) {
        super(name, eMail, startDate, finalDate, index);
        this.adviser= new ArrayList<>();
    }

    public int calcCost(){
        return 800;
    }

    public boolean addTeacher(Teacher t, int index){
        if(getProjects().get(0).getIndex() == index && !adviser.contains(t)){
            adviser.add(t);
            return true;
        }
        return false;
    }

    public  ArrayList<Teacher> getAdviser(){
        return adviser;
    }
}
