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
    }

    public Teacher(String name, String eMail, int mechaNumber, String reserchArea) {
        super(name, eMail);
        this.mechaNumber = mechaNumber;
        this.reserchArea = reserchArea;
    }

    /**
     * associate a scholar to the teacher
     * @param scholar must be a bachelor or a master (in case of being doctor return 0)
     * @return 1 in success or 0 in error
     */
    public int addScholars(Scholar scholar){
        if(scholar.calcCost()<=800){
            for (int i = 0; i < projects.size(); i++) {
                if(scholar.getProject(0).getIndex() == projects.get(i).getIndex()){
                    scholars.add(scholar);
                    if(scholar.addTeacher(this, projects.get(i).getIndex())==1){
                        return 1;
                    }
                    return 0;
                }
            }
        }
        return 0; //não foi adicionado, pois o docente e o bolseiro não pertencem ao mesmo projeto (ou o bolseiro é doctor)
    }

    public void addProjectToPerson(Project project){
        projects.add(project);
    }

    public int calcCost(){
        return 0;
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
}
