package Projeto;

import java.io.*;
import java.util.ArrayList;

public class CISUC {

    public static void main(String[] args) {

        new CISUC();
    }
    private ArrayList<Person> people;
    private ArrayList<Project> projects;

    public CISUC(){

        readFilePeople();

    }

    private ArrayList<Person> readFilePeople(){
        ArrayList<Person> personList = new ArrayList<>();

        File f = new File("People.txt");
        if(f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while((line = br.readLine()) != null) {
                    System.out.println(line);
                }

                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        }
        else {
            System.out.println("Ficheiro não existe.");
        }




        return personList;
    }

    private ArrayList<Project> readFileProjects(){
        ArrayList<Project> projectList = new ArrayList<>();
        return projectList;
    }

    private Project createProject(){
        Project proj = new Project();
        return proj;
    }

    private Person createPerson(){
        Person person = new Person();
        return person;
    }

    private void personAssociation(Person person, Project proj){

    }

    private boolean projectState(Project proj){
        return true;
    }




}
