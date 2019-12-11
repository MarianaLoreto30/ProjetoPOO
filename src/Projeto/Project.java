package Projeto;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Project implements Serializable {
    private int index;
    private String name;
    private String acronym;
    private Date startDate;
    private int duration;
    private ArrayList<Task> tasks;
    private ArrayList<Person> people;
    private Teacher principal;

    public Project(){
        name ="Unknown";
        acronym="UKN";
        principal=new Teacher();
        tasks = new ArrayList<>();
        people = new  ArrayList<>();
    }

    public Project(int index, String name, String acronym, Date startDate, int duration) {
        this.index = index;
        this.name = name;
        this.acronym = acronym;
        this.startDate = startDate;
        this.duration = duration;
        tasks = new ArrayList<>();
        people = new  ArrayList<>();
    }

    public void listProjectFeatures(int index){
        System.out.println("-Name: " + getName() + '\n' + "-Acronym: " + getAcronym() + '\n' + "-Start date: " + getStartDate() + '\n' + "-Duration: " + getDuration());
    }

    public void listPeopleInProject(){
        for(int i = 0; i < people.size(); i++){
            System.out.println(i + ". " + people.get(i).getName());
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
        System.out.println("Completed tasks:");
        int j = 1;
        for(int i = 0; i < tasks.size(); i++){
            if(taskConclusionState(i) == 100){
                System.out.println(j + '.' + tasks.get(i).getName());
                j++;
            }
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public int getTasksLen(){
        return tasks.size();
    }

    /**
     * project cost calculation
     * @return cost of the project
     */
    public double projectCost (){
        double cost = 0.0;
        for(Person p: people){
            cost+=p.calcCost();
        }
        return cost;
    }

    public void finishProject(){ // ainda nao sei o que fazer aqui

    }

    public boolean checkPresence(Person person){
        for(Person p: people){
            if(p.calcCost()==0 && person.calcCost()==0){
                Teacher t = (Teacher) p;
                Teacher tp = (Teacher) person;
                if(t.getMechaNumber()== tp.getMechaNumber()){
                    return true;
                }
            }
            else if(p.calcCost()!=0 && person.calcCost()!=0){
                Scholar s= (Scholar) p;
                Scholar sp = (Scholar) person;
                if(s.getIndex()==sp.getIndex()){
                    return true;
                }
            }
        }
        return false;
    }

    public void addPersonToProject(Person person){
        people.add(person);
    }

    public ArrayList<Person> getPeople(){
        return this.people;
    }

    public Teacher getPrincipal() {
        return principal;
    }

    public void setPrincipal(Teacher principal) {
        this.principal = principal;
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

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return "Project." + index + " name:" + name  + "; acronym:" + acronym + "; startDate: " + startDate + "; duration=" + duration;
    }
}
