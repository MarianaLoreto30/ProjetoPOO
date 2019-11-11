package Projeto;

import java.util.ArrayList;

public class Person {

    private String name;
    private String email;
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
