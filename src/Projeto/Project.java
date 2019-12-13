package Projeto;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private Date endDate;

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

    /**
     * all people associate at the project
     * @return ArrayList of people
     */
    public ArrayList<Person> listPeopleInProject(){
        ArrayList<Person> listPerson = new ArrayList<>();
        for(int i = 0; i < people.size(); i++){
            listPerson.add(people.get(i));
        }
        return listPerson;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * delete task from project (and from the person responsible)
     * @param index
     */
    public void deleteTask(int index){
        for(Task t: tasks){
            if(t.getIndex()==index){
                tasks.remove(t);
                t.getResponsible().deleteTaskFromPerson(t);
            }
        }
    }

    /**
     * all tasks of the project
     * @return ArrayList of all project's tasks
     */
    public ArrayList<Task> listTasks() {
        ArrayList<Task> listTask = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++){
            listTask.add(tasks.get(i));
        }
        return listTask;
    }

    /**
     * list all tasks non initialized (conclusion state equals 0)
     * @return ArrayList of tasks
     */
    public ArrayList<Task> listNonInitialized(){
        ArrayList<Task> nonInitializedTasks = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getConclusionState() == 0){
                nonInitializedTasks.add(tasks.get(i));
            }
        }
        return nonInitializedTasks;
    }

    /**
     *list all tasks that doesn't finish in time
     * @return ArrayList of tasks
     */
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

    /**
     * list all complete tasks (conclusion state equals 100)
     * @return ArrayList of tasks
     */
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

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public int getTasksLen(){
        return tasks.size();
    }

    /**
     * project cost calculation
     * @return cost of the project
     */
    public double projectCost (){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        double cost = 0.0;
        Date currentDate = new Date();
        Date aux, aux2;

        try {
            if(getEndDate().compareTo(format.parse("00/00/0000")) == 1){
                for(Person p: people) {
                    if (p.calcCost() != 0) {
                        Scholar s = (Scholar) p;

                        if (s.getStartDate().before(startDate)) {
                            aux = startDate;
                        }
                        else /*if(s.getStartDate().after(startDate) || s.getStartDate().compareTo(startDate) == 1)*/{
                            aux = s.getStartDate();
                        }

                        if(s.getFinalDate().before(currentDate)){
                            aux2 = s.getFinalDate();
                        }
                        else {
                            aux2 = currentDate;
                        }
                        cost += p.calcCost() * ((aux2.getYear() - aux.getYear()) * 12 + (aux2.getMonth() - aux.getMonth()) + 1);
                    }
                }
            }
            else{
                for(Person p: people) {
                    if (p.calcCost() != 0) {
                        Scholar s = (Scholar) p;

                        if (s.getStartDate().before(startDate)) {
                            aux = startDate;
                        }
                        else {
                            aux = s.getStartDate();
                        }

                        if(s.getFinalDate().before(endDate)){
                            aux2 = s.getFinalDate();
                        }
                        else {
                            aux2 = currentDate;
                        }
                        cost += p.calcCost() * ((aux2.getYear() - aux.getYear()) * 12 + (aux2.getMonth() - aux.getMonth()) + 1);
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println("");
        }

        return cost;
    }

    /**
     * verify if a project is finish or not
     * @return true if the project already end or false if not
     */
    public boolean finishProject(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if(endDate.compareTo(format.parse("00/00/0000"))==1){
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
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

    /**
     * add a person to the project (and vice-versa)
     * @param person that we want to add
     */
    public void addPersonToProject(Person person){
        if (person.addProjectToPerson(this)){
            people.add(person);
        }
    }

    /**
     * return all people that belong to the project
     * @return ArrayList of people that belong to the project
     */
    public ArrayList<Person> getPeople(){
        return this.people;
    }

    /**
     * principal getter
     * @return Teacher responsible for the project
     */
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return "Project." + index + " name: " + name  + "; acronym: " + acronym + "; startDate: " + startDate + "; duration: " + duration + " dias";
    }
}
