package Projeto;

import java.util.ArrayList;

public class Teacher extends Person{
    private int mechaNumber;
    private String reserchArea;
    private ArrayList<Scholar> scholars;

    public Teacher(){
        mechaNumber=0;
        reserchArea="Reserch area: unknown";
        scholars = new ArrayList<>();
    }

    public Teacher(int mechaNumber, String reserchArea) {
        this.mechaNumber = mechaNumber;
        this.reserchArea = reserchArea;
        scholars = new ArrayList<>();
    }

    public Teacher(String name, String eMail, int mechaNumber, String reserchArea) {
        super(name, eMail);
        this.mechaNumber = mechaNumber;
        this.reserchArea = reserchArea;
        scholars = new ArrayList<>();
    }

    /**
     * associate a scholar to the teacher
     * @param scholar must be a bachelor or a master (in case of being doctor doesn't add)
     * @return true in success or false in error (or in case scholar is a doctor)
     */
    public boolean addScholars(Scholar scholar){
        if(scholar.calcCost()<=800 && scholar.getProjects().size() !=0){
            for (Project p : getProjects()) {
                if (scholar.getProject(0).getIndex() == p.getIndex() && !scholars.contains(scholar)) {
                    if (scholar.addTeacher(this, p.getIndex())) {
                        scholars.add(scholar);
                        return true;
                    }
                }
                else if(scholar.getProject(0).getIndex() == p.getIndex() && scholars.contains(scholar)){
                    scholar.addTeacher(this, p.getIndex());
                    return true;
                }
            }
            scholars.remove(scholar);
        }
        return false;
    }

    /**
     * add a scholar (bachelor or master) to the ArrayList scholars without checking whether they belong to the same project or not
     * @param scholar bachelor or master
     */
    public void addScholarsFile(Scholar scholar){
        if(scholar.calcCost()<=800){
            scholars.add(scholar);
        }
    }

    /**
     * add a project to a teacher
     * @param project
     * @return true in success or false in case of the current project already belong to the project's list
     */
    public boolean addProjectToPerson(Project project){
        if(getProjects().contains(project)){
            return false;
        }
        getProjects().add(project);
        return true;
    }

    /**
     * return the teacher's cost for the project
     * @return 0
     */
    public int calcCost(){
        return 0;
    }

    public ArrayList<Scholar> getScholars(){
        return this.scholars;
    }

    public int getMechaNumber() {
        return mechaNumber;
    }

    public void setMechaNumber(int mechaNumber) {
        this.mechaNumber = mechaNumber;
    }

    public String getReserchArea() {
        return reserchArea;
    }

    public void setReserchArea(String reserchArea) {
        this.reserchArea = reserchArea;
    }

    @Override
    public String toString() {
        return super.toString() + " -> Teacher. mechaNumber:" + mechaNumber + ", reserchArea=" + reserchArea;
    }
}
