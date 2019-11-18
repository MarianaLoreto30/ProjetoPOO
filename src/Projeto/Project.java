package Projeto;

import java.util.ArrayList;
import java.util.Date;

public class Project {
    private String name;
    private String acronym;
    private Date startDate;
    private int duration;
    private ArrayList<Task> tasks;
    private ArrayList<Person> people;

    public Project(){
        name="Unknown";
        tasks= new ArrayList<>();
        people = new  ArrayList<>();
    }

    public Project(String name, String acronym, Date startDate, int duration) {
        this.name = name;
        this.acronym = acronym;
        this.startDate = startDate;
        this.duration = duration;
    }
    /*
    public Task creatTask(){
        Task task = new Task();
        return task;
    }*/

    public void listTask(){

    }

    public void deleteTask(){

    }

    public void taskConclusionState(){

    }

    public void listNonInitialized(){

    }

    private void listOutOfDateTasks(){

    }

    private void listCompleteTasks(){

    }

    public double projectCost (){
        return 0.0;
    }

    public void finishProject(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
