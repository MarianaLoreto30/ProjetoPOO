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
        name ="Unknown";
        tasks = new ArrayList<>();
        people = new  ArrayList<>();

    }

    public Project(String name, String acronym, Date startDate, int duration) {
        this.name = name;
        this.acronym = acronym;
        this.startDate = startDate;
        this.duration = duration;
    }

    public void listProjectFeatures(int index){
        System.out.println("-Name: " + getName() + '\n' + "-Acronym: " + getAcronym() + '\n' + "-Start date: " + getStartDate() + '\n' + "-Duration: " + getDuration());
    }

    public void listPeopleinProject(){
        for(int i = 0; i < people.size(); i++){
            System.out.println(i + '.' + people.get(i).name);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void listTasks() {
        for(int i = 0; i < tasks.size(); i++){
            System.out.println(i + '.' + tasks.get(i).getName());
        }
    }

    public void deleteTask(int index){
        tasks.remove(index);
    }

    public int taskConclusionState(int index){
        System.out.println("This task is " + tasks.get(index).getConclusionState() + "% complete");
        return tasks.get(index).getConclusionState();
    }

    public void listNonInitialized(){
        System.out.println("Non initialized tasks:");
        int j = 1;
        for(int i = 0; i < tasks.size(); i++){
            if(taskConclusionState(i) == 0){
                System.out.println(j + '.' + tasks.get(i).getName());
                j++;
            }
        }
    }

    public void listOutOfDateTasks(){
        System.out.println("Out of date tasks:");
        int j = 1;
        Date currentDate = new Date();
        for(int i = 0; i < tasks.size(); i++){
            if(currentDate.after(tasks.get(i).getEndDate())){
                System.out.println(j + '.' + tasks.get(i).getName());
                j++;
            }
        }
    }

    public void listCompleteTasks(){
        System.out.println("Completed tasks:");      //como fazemos para delimitar os valores de conclusao?
        int j = 1;
        for(int i = 0; i < tasks.size(); i++){
            if(taskConclusionState(i) == 100){
                System.out.println(j + '.' + tasks.get(i).getName());
                j++;
            }
        }
    }

    /*public double projectCost (){ !!nao aqui, na classe person!!
        return 0.0;
    }*/

    public void finishProject(){ // ainda nao sei o que fazer aqui

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
