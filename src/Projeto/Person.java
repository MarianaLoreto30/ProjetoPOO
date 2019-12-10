package Projeto;

import java.util.ArrayList;

public abstract class Person {

    protected String name;
    protected String email;
    protected ArrayList<Project> projects;
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
    }

    abstract int calcCost();

    abstract void addProjectToPerson(Project project);

    public void deleteProjectFromPerson(int index){
        projects.remove(index);//??
        //projects.remove(project);
    }

    public void addTaskToPerson(Task task){
        tasks.add(task);
    }

    public void deleteTaskFromPerson(int index){
        tasks.remove(index);
    }

    public boolean ckeckOverload() {
        int load = 0;
        for (int i = 0; i < tasks.size(); i++) {
            load += tasks.get(i).getEffortRate();
        }
        if (load <= 1) {
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
}
