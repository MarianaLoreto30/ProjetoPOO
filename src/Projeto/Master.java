package Projeto;

import java.util.ArrayList;
import java.util.Date;

public class Master extends  Scholar {

    private ArrayList<Teacher> adviser;

    /**
     * master's constructor
     * @param startDate start date of the scholarship
     * @param finalDate end date of the scholarship
     * @param index number that identify the scholar
     */
    public Master(Date startDate, Date finalDate, int index) {
        super(startDate, finalDate, index);
        this.adviser= new ArrayList<>();
    }

    /**
     * constructor
     * @param name person's name
     * @param eMail person's e-mail
     * @param startDate start date of the scholarship
     * @param finalDate end date of the scholarship
     * @param index number that identify the scholar
     */
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
