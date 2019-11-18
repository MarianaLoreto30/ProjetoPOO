package Projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CISUC {

    public static void main(String[] args) {

        new CISUC();
    }
    private ArrayList<Person> people;
    private ArrayList<Project> projects;

    public CISUC(){

        readFilePeople();

    }

    public ArrayList<Person> readFilePeople(){
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

    public ArrayList<Project> readFileProjects(){
        ArrayList<Project> projectList = new ArrayList<>();
        return projectList;
    }

    public Project createProject(){
        Project proj = new Project();
        return proj;
    }

    public Person createPerson(){
        Person person = new Person();
        return person;
    }

    public void personAssociation(Person person, Project proj){

    }

    public boolean projectState(Project proj){
        return true;
    }

    public void register(){
        Person newPerson = createPerson();
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert your name:");
        newPerson.name = sc.nextLine();


    }
}
