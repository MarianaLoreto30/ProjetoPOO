package Projeto;

import java.util.ArrayList;
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

    /**
     * Add (only) a project to a scholar
     * @param project
     */
    public boolean addProjectToPerson(Project project){
        if(getProjects().size() < 1 ){
            getProjects().add(project);
            return true;
        }
        else{
            return false;
        }
    }

    abstract int calcCost();

    /**
     * add Teacher to the Scholar (only for bachelor and master)
     * @param t teacher that will be response for the scholar
     * @param index of the project
     * @return true in success or false in error
     */
    abstract boolean addTeacher(Teacher t, int index);

    abstract ArrayList<Teacher> getAdviser();

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

    @Override
    public String toString() {
        return super.toString() + "-> Scholar. startDate: " + startDate + ", finalDate: " + finalDate + ", index: " + index;
    }
}
