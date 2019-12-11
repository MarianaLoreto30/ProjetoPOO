package Projeto;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CISUC implements Serializable{

    public static void main(String[] args) {
        new CISUC();
    }
    private ArrayList<Person> people;
    private ArrayList<Project> projects;
    private GraphicalUserInterface gui;

    public CISUC(){
        readFilePeople();
        readFileProjects();
        writeObjectsFile();

        /*for(Project p: projects){
            System.out.println(p.toString());
            listPeopleInProject(p);
        }*/

        /*for(Person p: people){
            listAllAssociates(p);
        }*/

        /*
        gui = new GraphicalUserInterface(this);
        gui.setVisible(true);*/
    }

    /**
     * read the people's file and create the object Person adding in the arrayList (this method does not associate people and projects)
     */
    public void readFilePeople(){ //Falta associar bolseiros a docente
        Person person;
        String[] aux, associates;
        String line;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date aux1, aux2;
        int mechaNumber, index;
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

                                person = new Teacher(aux[2], aux[3], mechaNumber, aux[6]);
                                if(!people.contains(person)){
                                    people.add(person);
                                }

                                Teacher t = (Teacher) person;

                                associates = aux[4].split(",");
                                for(int i=0; i<associates.length; i++){
                                    index = Integer.parseInt(associates[i]);
                                    for(Person p: people){
                                        if(p.calcCost()!=0){
                                            Scholar s = (Scholar) p;
                                            if(s.getIndex() == index){
                                                t.addScholarsFile(s);
                                            }
                                        }
                                    }
                                }

                            }catch (NumberFormatException e) {
                                System.out.println("Erro ao converter number: " + e.getMessage());
                            }catch (NullPointerException e){
                                System.out.println("Erro ao adicionar na lista: " + e.getMessage());
                            }

                        }
                        else if (aux[0].equalsIgnoreCase("Scholar")) {

                            try {
                                aux1 = format.parse(aux[4]);
                                aux2 = format.parse(aux[5]);

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

                                if (aux[1].equalsIgnoreCase("Bachelor")) {
                                    person = new Bachelor(aux[2], aux[3], aux1, aux2, index);
                                    if(!people.contains(person)){
                                        people.add(person);
                                    }
                                } else if (aux[1].equalsIgnoreCase("Master")) {
                                    person = new Master(aux[2], aux[3], aux1, aux2, index);
                                    if(!people.contains(person)){
                                        people.add(person);
                                    }
                                } else if (aux[1].equalsIgnoreCase("Doctor")) {
                                    person = new Doctor(aux[2], aux[3], aux1, aux2, index);
                                    if(!people.contains(person)){
                                        people.add(person);
                                    }
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                                System.out.println("Erro na data do bolseiro.");
                            } catch (NumberFormatException e) {
                                System.out.println("Erro ao converter Index Number: " + e.getMessage());
                            }catch (NullPointerException e){
                                System.out.println("Erro ao adicionar na lista: " + e.getMessage());
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
        String[] aux, peopleProject;
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       Date date1, date2;
       int duration = 0, indexP = 0, indexT=0, indexPerson, indexR, conclusionState = 0;
       double effortRate = 0.0 ;
       boolean safe;
       projects=new ArrayList<>();

       File fProj = new File("Projects.txt");

       if(fProj.exists() && fProj.isFile()) {
           try{
               FileReader fr = new FileReader(fProj);
               BufferedReader br = new BufferedReader(fr);

               while((line = br.readLine()) != null) {
                   date1 = new Date();
                   date2 = new Date();

                   aux = line.split(";");
                   safe=false;
                   if(aux[0].equalsIgnoreCase("PROJECT") && aux.length == 8){

                       try {
                           indexP=Integer.parseInt(aux[1]);
                           if(projects.isEmpty()){
                               safe=true;
                           }
                           for (Project p: projects){
                               if( p.getIndex() != indexP && indexP !=0/*|| aux[2].equalsIgnoreCase(p.getName())*/){
                                   safe=true;
                                   break;
                               }
                           }
                       } catch (NumberFormatException e){
                           System.out.println("Error in conversion project's index: " + e.getMessage());
                       }

                       if(safe){
                           try {
                               date1 = format.parse(aux[4]);
                               duration = Integer.parseInt(aux[5]);
                               indexR = Integer.parseInt(aux[7]);

                               project = new Project(indexP, aux[2],aux[3], date1, duration);

                               peopleProject = aux[6].split(",");
                               for (int i = 0; i < peopleProject.length; i++) {
                                   try{
                                       indexPerson = Integer.parseInt(peopleProject[i]);
                                       for(Person personAux: people){
                                           if(personAux.calcCost()==0){
                                               Teacher t = (Teacher) personAux;
                                               if(indexPerson == t.getMechaNumber()){
                                                   addProjectToPerson(personAux, project);
                                                   addPersonToProject(personAux, project);
                                               }
                                               else if(indexR == t.getMechaNumber()){
                                                   if(!project.getPeople().contains(t)){
                                                       addProjectToPerson(personAux, project);
                                                       addPersonToProject(personAux, project);
                                                   }
                                                   project.setPrincipal(t);
                                               }
                                           }
                                           else{
                                               Scholar s =(Scholar) personAux;
                                               if(indexPerson == s.getIndex()){
                                                   addProjectToPerson(personAux, project);
                                                   addPersonToProject(personAux, project);
                                               }
                                           }
                                       }

                                       for(Person person: project.getPeople()){
                                           if(person.calcCost()==0){
                                               Teacher t = (Teacher) person;
                                               ArrayList<Scholar> exemplo = new ArrayList<>();
                                               for(Scholar s: t.getScholars()){
                                                   exemplo.add(s);
                                               }
                                               for(Scholar s: exemplo){
                                                   t.addScholars(s);
                                               }
                                           }
                                       }

                                   }catch (NumberFormatException e){
                                       System.out.println("Error in conversion person's index: " + e.getMessage());
                                   }
                               }
                               projects.add(project);

                           } catch (ParseException e) {
                               e.printStackTrace();
                               System.out.println("Error in date: " + e.getMessage());
                           } catch (NumberFormatException e){
                               System.out.println("Error in conversion: " + e.getMessage());
                           } catch (NullPointerException e ){
                               System.out.println("Error in adding to the ArrayList: " + e.getMessage());
                           }
                       }
                   }
                   else if(aux[0].equalsIgnoreCase("TASK") && aux.length == 10){
                       try{
                           indexP=Integer.parseInt(aux[1]); //index do projeto
                           indexR=Integer.parseInt(aux[2]); //index da pessoa responsável

                           for(Project p: projects){
                               for(Person personAux: p.getPeople()){
                                   if(personAux.calcCost()==0){
                                       Teacher t = (Teacher) personAux;
                                       indexPerson = t.getMechaNumber();
                                   }
                                   else{
                                       Scholar s = (Scholar) personAux;
                                       indexPerson = s.getIndex();
                                   }
                                   if(p.getIndex() == indexP &&  indexR == indexPerson){

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
                                           p.addTask(task);
                                           personAux.addTaskToPerson(task);
                                           task.addPersonToTask(personAux);
                                       }
                                       else if(effortRate == 0.5){ //Design
                                           task = new Design(aux[3], date1, date2, duration, conclusionState, personAux, effortRate, indexT);
                                           p.addTask(task);
                                           personAux.addTaskToPerson(task);
                                           task.addPersonToTask(personAux);
                                       }
                                       else if(effortRate == 1.00){ //Design
                                           task = new Development(aux[3], date1, date2, duration, conclusionState, personAux, effortRate, indexT);
                                           p.addTask(task);
                                           personAux.addTaskToPerson(task);
                                           task.addPersonToTask(personAux);
                                       }
                                   }
                               }
                           }
                       } catch (NumberFormatException e) {
                           System.out.println("Error in conversion:  "+ e.getMessage());
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


    public void writeObjectsFile(){
        File fproject = new File("Project.obj");
        File fperson = new File("Person.obj");

        try {
            FileOutputStream fos = new FileOutputStream(fproject);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Project p: projects){
                oos.writeObject(p);
            }
            oos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error creating project's file: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error writing in project's file:" + e.getMessage());
        }

        try {
            FileOutputStream fos = new FileOutputStream(fperson);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Person p: people){
                oos.writeObject(p);
            }
            oos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error creating person's file: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error writing in person's file:" + e.getMessage());
        }


    }

    public void addProject(int index, String name, String acronym, Date startDate, int duration){
        projects.add(new Project(index, name, acronym, startDate, duration));
    }

    public void listAllProjects(){
        for(int i = 0; i < projects.size(); i++){
            System.out.println(projects.get(i).getIndex() + ". " + projects.get(i).getName());
        }
    }

    public void listProjectFeatures(Project project, int index){
        project.listProjectFeatures(index);
    }

    public void listPeopleInProject(Project project){
        project.listPeopleInProject();
    }

    //NAO DEVEMOS REMOVER PROJETO!!
    public boolean removeProject(int index){
        for(Project p: projects){
            if(index == p.getIndex()){
                projects.remove(p);
                return true;
            }
        }
        return false;
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
            if(people.get(i).calcCost() == 0){
                System.out.println("Teacher: " + people.get(i).getName() +" - " + people.get(i).getEmail());
            }
            else{
                System.out.println("Scholar: " + people.get(i).getName() +" - " + people.get(i).getEmail());
            }
        }
    }

    public void listAllAssociates(Person person){
        if(person.calcCost()==0){
            System.out.println("\n" + person.getName() + " - scholars in charge of:");
            Teacher t =(Teacher) person;
            for(Scholar s: t.getScholars()){
                System.out.println(s.getName());
            }
        }
        else if(person.calcCost()<=800){
            System.out.println("\n" + person.getName() + " - advisers:");
            Scholar s= (Scholar) person;
            for(Teacher t: s.getAdviser()){
                System.out.println(t.getName());
            }
        }
        else{
            System.out.println("\n"+ person.getName() +"- doctors don't have advisers");
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

    public void addPersonToTask(Person person, Task task){
        for (int i = 0; i < person.getTasksLen(); i++) {
            if(person.getTask(i).getIndex()==task.getIndex()){
                task.addPersonToTask(person);
            }
        }
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

    public void addPersonToProject(Person person, Project project){
        project.addPersonToProject(person);
    }

    /*
    public void register(){
        Person newPerson = createPerson();
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert your name:");
        newPerson.getName() = sc.nextLine();

    }*/
}
