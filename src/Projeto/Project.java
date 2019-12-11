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

    public ArrayList<Task> listTasks() {
        ArrayList<Task> listTask = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++){
            listTask.add(tasks.get(i));
        }
        return listTask;
    }

    public ArrayList<Task> listNonInitialized(){
        ArrayList<Task> nonInitializedTasks = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getConclusionState() == 0){
                nonInitializedTasks.add(tasks.get(i));
            }
        }
        return nonInitializedTasks;
    }

    public ArrayList<Task> listOutOfDateTasks(){
        ArrayList<Task> outOfDateTasks = new ArrayList<>();
        Date currentDate = new Date();
        for(int i = 0; i < tasks.size(); i++){
            if(currentDate.after(tasks.get(i).getEndDate())){
                outOfDateTasks.add(tasks.get(i));
            }
        }
        return outOfDateTasks;
    }

    public ArrayList<Task> listCompleteTasks(){
        ArrayList<Task> completeTasks = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getConclusionState() == 100){
                completeTasks.add(tasks.get(i));
            }
        }
        return completeTasks;
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

    /**
     * check if a person belong to the project
     * @param person person
     * @return true if belong or false if not
     */
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
        if (person.addProjectToPerson(this)){
            people.add(person);
        }
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
        return "Project." + index + " name: " + name  + "; acronym: " + acronym + "; startDate: " + startDate + "; duration: " + duration + " dias";
    }
}
