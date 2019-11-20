package Projeto;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    /**
     * read the people's file and create the object Person (this method does not associate people and projects)
     * @return ArrayList of people? QUERO ALTERAR ISTO!! Faz mais sentido adicionar a pessoa direamente à lista
     */
    public ArrayList<Person> readFilePeople(){
        ArrayList<Person> personList = new ArrayList<>();
        Person person;
        String[] aux;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date aux1 = new Date(), aux2 = new Date();
        int mechaNumber= 0;

        File f = new File("People.txt");
        if(f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while((line = br.readLine()) != null) {
                    System.out.println(line);
                    aux = line.split(";");
                    if(aux[0].equalsIgnoreCase("Teacher")){
                        try {
                            mechaNumber=Integer.parseInt(aux[7]);
                        } catch (NumberFormatException e){
                            System.out.println("Erro ao converter mechaNumber:\n" + e.getMessage());
                        }
                        //String name, String eMail, int mechaNumber, String reserchArea
                        person = new Teacher(aux[2], aux[3], mechaNumber, aux[8]);
                    }
                    else if(aux[0].equalsIgnoreCase("Scholar")){
                        try {
                            aux1=format.parse(aux[7]);
                            aux2=format.parse(aux[8]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            System.out.println("Erro na data do bolseiro.");
                        }

                        if(aux[1].equalsIgnoreCase("Bachelor")){
                            person = new Bachelor(aux[2], aux[3], aux1, aux2);
                        }
                        else if(aux[1].equalsIgnoreCase("Master")){
                            person = new Master(aux[2], aux[3], aux1, aux2);
                        }
                        else if(aux[1].equalsIgnoreCase("Doctor")){
                            person = new Doctor(aux[2], aux[3], aux1, aux2);
                        }
                    }
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


    public Person createTeacher(String name, String email, int mechaNumber, String reserchArea){
        //String name, String eMail, int mechaNumber, String reserchArea
        Person person;
        person = new Teacher(name, email, mechaNumber, reserchArea);

        return person;
    }

    public Person createScholar(String name, String email, Date startDate, Date finalDate){
        //String name, String eMail, int mechaNumber, String reserchArea
        Person person;
        person = new Bachelor(name, email, startDate, finalDate);

        return person;
    }

    public void personAssociation(Person person, Project proj){

    }

    public boolean projectState(Project proj){
        return true;
    }

    /*
    public void register(){
        Person newPerson = createPerson();
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert your name:");
        newPerson.name = sc.nextLine();


    }*/
}
