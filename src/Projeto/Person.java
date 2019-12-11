package Projeto;

import java.io.Serializable;
import java.util.ArrayList;

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

    public void deleteProjectFromPerson(int index){
        projects.remove(index);//Não deviamos apagar! Apenas por como inativo??
        //projects.remove(project);
    }

    /**
     * add task to a person
     * @param task task
     */
    public void addTaskToPerson(Task task){
        if(checkOverload(task)==true){
            tasks.add(task);
        }
    }

    public void deleteTaskFromPerson(int index){
        tasks.remove(index); //Não deviamos apagar!?
    }

    /**
     * check if a person is overload or not
     * @param task
     * @return false in case of overload and true if not
     */
    public boolean checkOverload(Task task) {
        int load = 0;
        for (int i = 0; i < tasks.size(); i++) {
            load += tasks.get(i).getEffortRate();
        }
        if (load + task.getEffortRate() <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public Project getProject(int index){
        return projects.get(index);
    }

    public int getProjectsLen(){
        return projects.size();
    }

    public Task getTask(int index){
        return tasks.get(index);
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
