package Projeto;

import java.util.ArrayList;

public abstract class Person {

    protected String name;
    protected String email;
    private ArrayList<Project> projects;
    private ArrayList<Task> tasks;

    public Person(){
        name = "Unknown";
        email = "unknown@email.com";
        projects= new ArrayList<>();
        tasks= new ArrayList<>();
    }

    public Person(String name, String eMail) {
        this.name = name;
        this.email = eMail;
    }

    abstract int calcCost();

    public void addProject( Project project){


    }

    public void deleteProject( Project project ){


    }

    public void addTask(Task task){


    }

    public void deleteTask( Task task){


    }

    public boolean ckeckOverload(){
        return false;
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
