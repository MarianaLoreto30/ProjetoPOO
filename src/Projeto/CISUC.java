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
    private GraphicalUserInterface gui;

    public CISUC(){
        readFilePeople();
        listAllPeople();


        gui = new GraphicalUserInterface(this);
        gui.setVisible(true);

    }

    /**
     * read the people's file and create the object Person adding in the arrayList (this method does not associate people and projects)
     */
    public void readFilePeople(){ //Falta associar bolseiros a docente
        Person person;
        String[] aux, associates;
        String line;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date aux1 = new Date(), aux2 = new Date();
        int mechaNumber= -1, index =-1;
        people = new ArrayList<>();

        File f = new File("People.txt");
        if(f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                while((line = br.readLine()) != null) {
                    aux = line.split(";");

                    try {
                        if (aux[0].equalsIgnoreCase("Teacher")) {
                            try {
                                mechaNumber = Integer.parseInt(aux[6]);
                                for(Person p: people){
                                    if(p.calcCost()==0){ //Verifica se é teacher
                                        Teacher t = (Teacher) p;
                                        if(mechaNumber == t.getMechaNumber()){
                                            System.out.println("MechaNumber already exists!");
                                            mechaNumber=-1;
                                            break;
                                        }
                                    }
                                }
                            }catch (NumberFormatException e) {
                                System.out.println("Erro ao converter mechaNumber: " + e.getMessage());
                            }

                            person = new Teacher(aux[2], aux[3], mechaNumber, aux[6]);

                            try{
                                people.add(person);
                            }catch (NullPointerException e){
                                System.out.println("Erro ao adicionar na lista: " + e.getMessage());
                            }
                        }
                        //FALTA ASSOCIAR ORIENTADORES
                        else if (aux[0].equalsIgnoreCase("Scholar")) {

                            try {
                                aux1 = format.parse(aux[4]);
                                aux2 = format.parse(aux[5]);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                System.out.println("Erro na data do bolseiro.");
                            }

                            try {
                                index = Integer.parseInt(aux[6]);
                                for(Person p: people){
                                    if(p.calcCost()>0){ //Verifica se é bolseiro
                                        Scholar s = (Scholar) p;
                                        if(index == s.getIndex()){
                                            System.out.println("Index Number already exists!");
                                            index=-1;
                                            break;
                                        }
                                    }
                                }
                            }catch (NumberFormatException e) {
                                System.out.println("Erro ao converter Index Number: " + e.getMessage());
                            }

                            if (aux[1].equalsIgnoreCase("Bachelor")) {
                                person = new Bachelor(aux[2], aux[3], aux1, aux2, index);
                                people.add(person);
                            } else if (aux[1].equalsIgnoreCase("Master")) {
                                person = new Master(aux[2], aux[3], aux1, aux2, index);
                                people.add(person);
                            } else if (aux[1].equalsIgnoreCase("Doctor")) {
                                person = new Doctor(aux[2], aux[3], aux1, aux2, index);
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

    /**
     * read and create projects and tasks from the file "Projects.txt"
     */
   public void readFileProjects(){
        Project project;
        Task task;
        String line;
        String[] aux;
        boolean safe;
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       Date date1, date2;
       int duration = 0, indexP = 0, indexT=0, indexPessoa =0, indexPessoaF = 0, conclusionState = 0;
       double effortRate = 0.0 ;

       File fProj = new File("Projects.txt");

       if(fProj.exists() && fProj.isFile()) {
           try{
               FileReader fr = new FileReader(fProj);
               BufferedReader br = new BufferedReader(fr);

               while((line = br.readLine()) != null) {
                   date1 = new Date();
                   date2 = new Date();

                   System.out.println(line);
                   aux = line.split(";");
                   safe=true;

                   if(aux[0].equalsIgnoreCase("PROJECT")){

                       for (Project p: projects){
                           if( aux[1].equalsIgnoreCase(p.getName()) ){
                               System.out.println("Erro: nome do projeto já existe");
                               safe=false; //ignoro este projeto
                               break;
                           }
                       }

                       if(safe==true){
                           try {
                               date1 = format.parse(aux[4]);
                               indexP=Integer.parseInt(aux[1]);
                               duration = Integer.parseInt(aux[5]);
                           } catch (ParseException e) {
                               e.printStackTrace();
                               System.out.println("Error in date: " + e.getMessage());
                           } catch (NumberFormatException e){
                               System.out.println("Error in conversion: " + e.getMessage());
                           }
                           project = new Project(indexT, aux[2],aux[3], date1, duration);

                           //addProject(index, aux[1], aux[2], date1, duration);
                       }
                   }
                   else if(aux[0].equalsIgnoreCase("TASK")){
                       try{
                           indexP=Integer.parseInt(aux[1]);
                           indexPessoaF=Integer.parseInt(aux[2]);
                       } catch (NumberFormatException e) {
                           e.printStackTrace();
                       }
                       for(Project p: projects){
                           for(Person personAux: people){
                               if(personAux.calcCost()==0){
                                   Teacher t = (Teacher) personAux;
                                   indexPessoa = t.getMechaNumber();
                               }
                               else{
                                   Scholar s = (Scholar) personAux;
                                   indexPessoa = s.getIndex();
                               }
                               if(p.getIndex() == indexP &&  indexPessoaF == indexPessoa){

                                   try{
                                       date1 = format.parse(aux[4]);
                                       date2 = format.parse(aux[5]);
                                       duration = Integer.parseInt(aux[6]);
                                       conclusionState= Integer.parseInt(aux[7]);
                                       effortRate=Double.parseDouble(aux[8]);
                                       indexT=Integer.parseInt(aux[9]);
                                   } catch (NumberFormatException e) {
                                       System.out.println("Erro in conversion " + e.getMessage());
                                   } catch (ParseException e) {
                                       e.printStackTrace();
                                       System.out.println("Error in date: " + e.getMessage());
                                   }

                                   if(effortRate == 0.25){ //Documentation
                                       task = new Documentation(aux[3], date1, date2, duration, conclusionState, personAux, effortRate, indexT);
                                   }
                                   else if(effortRate == 0.5){ //Design
                                       task = new Design(aux[3], date1, date2, duration, conclusionState, personAux, effortRate, indexT);
                                   }
                                   else if(effortRate == 1.00){ //Design
                                       task = new Development(aux[3], date1, date2, duration, conclusionState, personAux, effortRate, indexT);
                                   }
                               }
                           }

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

    public void addProject(int index, String name, String acronym, Date startDate, int duration){
        projects.add(new Project(index, name, acronym, startDate, duration));
    }

    public void listAllProjects(){
        for(int i = 0; i < projects.size(); i++){
            System.out.println(i + '.' + projects.get(i).getName());
        }
    }

    public void listProjectFeatures(Project project, int index){
        project.listProjectFeatures(index);
    }

    public void listPeopleInProject(Project project){
        project.listPeopleInProject();
    }


    public void removeProject(int index){
        if (index > projects.size()){
            System.out.println("Invalid number!\n");
        }
        else {
            projects.remove(index);
        }
    }

    public void addTask(int index, Project project, String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible, double effortRate){
        boolean a = true;
        for (int i = 0; i < project.getTasksLen(); i++){
            if (project.getTask(i).getIndex() == index){
                System.out.println("Already existing task!\n");
                a = false;
            }
        }
        if (a) {
            if (effortRate == 0.5) {
                project.addTask(new Design(name, startDate, endDate, duration, conclusionState, responsible, effortRate, index));
            }
            else if (effortRate == 0.25) {
                project.addTask(new Documentation(name, startDate, endDate, duration, conclusionState, responsible, effortRate, index));
            }
            else if (effortRate == 1) {
                project.addTask(new Development(name, startDate, endDate, duration, conclusionState, responsible, effortRate, index));
            }
        }
    }

    public void listTasks(Project project){
        project.listTasks();
    }

    public void deleteTask(Project project, int index){
        if (index > project.getTasksLen()){
            System.out.println("Invalid number!\n");
        }
        else {
            project.deleteTask(index);
        }
    }

    public void taskConclusionState(Project project, int index){
        if (index > project.getTasksLen()){
            System.out.println("Invalid number!\n");
        }
        else {
            project.taskConclusionState(index);
        }
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

    public void listAllPeople(){
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).name +"\n" + people.get(i).email);
        }
    }

    public void listPersonTasks(){}

    public void listPersonProjects(){}

    public void deleteProjectFromPerson(Person person, int index){
        if (index > person.getProjectsLen()){
            System.out.println("Invalid number!\n");
        }
        else {
            person.deleteProjectFromPerson(index);
        }
    }

    public void addTask(Person person, Task task){
        person.addTaskToPerson(task);
    }

    public void addTaskToPerson(Person person, Task task){
        person.addTaskToPerson(task);
    }

    public void deleteTaskFromPerson(Person person, int index){
        if (index > person.getTasksLen()){
            System.out.println("Invalid number!\n");
        }
        else {
            person.deleteTaskFromPerson(index);
        }
    }

    public void personAssociation(Person person, Project proj){
        //proj.
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
