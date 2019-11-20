package Projeto;

import java.util.ArrayList;

public class Teacher extends Person{
    private int mechaNumber;
    private String reserchArea;
    //private ArrayList<Scholar> scholars;

    public Teacher(){
        mechaNumber=0;
        reserchArea="Reserch area: unknown";
        //scholars = new ArrayList<>();
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
    /*
    public void addScholars(Scholar scholar){
        scholars.add(scholar);
    }*/

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
