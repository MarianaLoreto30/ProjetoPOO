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
     * read the people's file and create the object Person adding in the arrayList (this method does not associate people and projects)
     */
    public void readFilePeople(){
        Person person;
        String[] aux;
        String line;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date aux1 = new Date(), aux2 = new Date();
        int mechaNumber= 0;
        people = new ArrayList<>();

        File f = new File("People.txt");
        if(f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                while((line = br.readLine()) != null) {
                    aux = line.split(";");

                    try {
                        //FALTA ASSOCIAR Bolseiros
                        if (aux[0].equalsIgnoreCase("Teacher")) {
                            try {
                                mechaNumber = Integer.parseInt(aux[7]);
                            } catch (NumberFormatException e) {
                                System.out.println("Erro ao converter mechaNumber: " + e.getMessage());
                            }

                            person = new Teacher(aux[2], aux[3], mechaNumber, aux[8]);

                            //Tenho de criar tarefa para adicionar! Provavelmente é necessário um ficheiro só para tarefas!!

                            try{
                                people.add(person);
                            }catch (NullPointerException e){
                                System.out.println("Erro ao adicionar na lista: " + e.getMessage());
                            }
                        }
                        //FALTA ASSOCIAR ORIENTADORES
                        else if (aux[0].equalsIgnoreCase("Scholar")) {

                            try {
                                aux1 = format.parse(aux[7]);
                                aux2 = format.parse(aux[8]);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                System.out.println("Erro na data do bolseiro.");
                            }

                            if (aux[1].equalsIgnoreCase("Bachelor")) {
                                person = new Bachelor(aux[2], aux[3], aux1, aux2);
                                people.add(person);
                            } else if (aux[1].equalsIgnoreCase("Master")) {
                                person = new Master(aux[2], aux[3], aux1, aux2);
                                people.add(person);
                            } else if (aux[1].equalsIgnoreCase("Doctor")) {
                                person = new Doctor(aux[2], aux[3], aux1, aux2);
                                people.add(person);
                            }
                        }
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("Erro de indexação: " + e.getMessage());
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
    }


   public void readFileProjects(){
        projects= new ArrayList<>();
        Project project;
        String line;
        String[] aux;
        boolean safe;
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       Date date1 = new Date();
       int duration = 0;

       File fProj = new File("Projects.txt");
       File fTask = new File("Tasks.txt");

       if(fProj.exists() && fProj.isFile()) {
           try{
               FileReader fr = new FileReader(fProj);
               BufferedReader br = new BufferedReader(fr);

               while((line = br.readLine()) != null) {
                   System.out.println(line);
                   aux = line.split(";");
                   safe=true;

                   for (Project p: projects){
                       if( aux[0].equalsIgnoreCase(p.getName()) ){
                           System.out.println("Erro: nome do projeto já existe");
                           safe=false; //ignoro este projeto
                           break;
                       }
                   }

                   if(safe==true){
                       try {
                           date1 = format.parse(aux[2]);
                           duration = Integer.parseInt(aux[3]);
                       } catch (ParseException e) {
                           e.printStackTrace();
                           System.out.println("Error in date: " + e.getMessage());
                       } catch (NumberFormatException e){
                           System.out.println("Error in project's duration: " + e.getMessage());
                       }
                       project = new Project(aux[0],aux[1], date1, duration);

                       if(fTask.exists() && fTask.isFile()){
                           FileReader frTask = new FileReader(fTask);
                           BufferedReader brTask = new BufferedReader(frTask);


                       }else{
                           System.out.println("Ficheiro Tasks não existe.");
                       }



                   }
               }


           } catch (FileNotFoundException ex) {
               System.out.println("Erro a abrir ficheiro de texto.");
           } catch (IOException ex) {
               System.out.println("Erro a ler ficheiro de texto.");
           }
       }
       else {
           System.out.println("Ficheiro Projects não existe.");
       }

    }

    public void addProject(String name, String acronym, Date startDate, int duration){
        projects.add(new Project(name, acronym, startDate, duration));
    }

    public void listAllProjects(){
        for(int i = 0; i < projects.size(); i++){
            System.out.println(i + "." + projects.get(i).getName());
        }
    }

    public void listProjectFeatures(Project project, int index){
        project.listProjectFeatures(index);
    }

    public void listPeopleinProject(Project project){
        project.listPeopleinProject();
    }


    public void removeProject(int index){
        projects.remove(index);
    }

    public void addTask(Project project, String type, String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible, float effortRate){
        switch(type){
            case "Design":
                project.addTask(new Design(name, startDate, endDate, duration, conclusionState, responsible, effortRate));
            case "Documentation":
                project.addTask(new Documentation(name, startDate, endDate, duration, conclusionState, responsible, effortRate));
            case "Development":
                project.addTask(new Development(name, startDate, endDate, duration, conclusionState, responsible, effortRate));
        }
    }

    public void listTasks(Project project){
        project.listTasks();
    }

    public void deleteTask(Project project, int index){
        project.deleteTask(index);
    }

    public void taskConclusionState(Project project, int index){
        project.taskConclusionState(index);
    }

    public void listNonInitialized(Project project){
        project.listNonInitialized();
    }

    public Project createProject(){
        Project proj = new Project();
        return proj;
    }

    /*
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
    }*/

    public void listAllPeople(){}
    public void listPersonTasks(){}
    public void listPersonProjects(){}

    public void deleteProjectFromPerson(Person person, int index){
        person.deleteProjectFromPerson(index);
    }

    public void addTask(Person person, Task task){
        person.addTaskToPerson(task);
    }

    public void addTaskToPerson(Person person, Task task){
        person.addTaskToPerson(task);
    }

    public void deleteTaskFromPerson(Person person, int index){
        person.deleteTaskFromPerson(index);
    }

    public void personAssociation(Person person, Project proj){

    }

    public boolean projectState(Project proj){
        return true;
    }

    public void addProjectToPerson(Person person, Project project){
        person.addProjectToPerson(project);
    }

    /*
    public void register(){
        Person newPerson = createPerson();
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert your name:");
        newPerson.name = sc.nextLine();

    }*/
}
