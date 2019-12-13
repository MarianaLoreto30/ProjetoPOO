package Projeto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class Person implements Serializable {

    private String name;
    private String email;
    private ArrayList<Project> projects;
    private ArrayList<Task> tasks;

    public Person(){
        name = "Unknown";
        email = "unknown@email.com";
        projects = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public Person(String name, String eMail) {
        this.name = name;
        this.email = eMail;
        projects = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    abstract int calcCost();

    abstract boolean addProjectToPerson(Project project);

    public void deleteProjectFromPerson(Project project){
        if(projects.contains(project)){
            projects.remove(project);
        }
    }

    /**
     * add task to a person (and vice-versa)
     * @param task task
     * @return true in success or false in error
     */
    public boolean addTaskToPerson(Task task){

        if(checkOverload(task)==true){

            if(calcCost() != 0){
                Scholar s = (Scholar) this;

                Date aux;
                Date currentDate = new Date();

                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, task.getDuration());
                aux = c.getTime();

                if(aux.before(s.getFinalDate())){
                    tasks.add(task);
                    task.addPersonToTask(this);
                    return true;
                }
                return false;
            }
            else{
                tasks.add(task);
                task.addPersonToTask(this);
                return true;
            }
        }
        return  false;
    }

    /**
     * delete task from person
     * @param t task
     */
    public void deleteTaskFromPerson(Task t){
        if(tasks.contains(t)){
            tasks.remove(t);
        }
    }

    /**
     * check if a person is overload with the task or not
     * @param task task
     * @return false in case of overload and true if not
     */
    public boolean checkOverload(Task task) {
        int load = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getConclusionState()!=100){
                load += tasks.get(i).returnEffortRate();
            }
        }
        if (load + task.returnEffortRate() <= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * the project in the ArrayList of projects with that index
     * @param index int
     * @return Project
     */
    public Project getProject(int index){
        return projects.get(index);
    }

    public int getProjectsLen(){
        return projects.size();
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

    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person. name: " + name + ", email: " + email;
    }
}
