package Projeto;

import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CISUC implements Serializable{

    public static void main(String[] args) {
        new CISUC();
    }
    public ArrayList<Person> people;
    public ArrayList<Project> projects;
    private GraphicalUserInterface gui;

    public CISUC(){
        readFilePeople();
        readFileProjects();
        //readObjectFile();
        writeObjectsFile();

        //listAllPeople();

        /*System.out.println("\n------------");
        for(Project p: projects){
            System.out.println("\n" + p.toString());
            //listPeopleInProject(p);
            listTasks(p);
        }

        System.out.println("\n------------\n");
        for(Person p: people){
            System.out.println("\n" + p.toString());
            //listAllAssociates(p);
            listPersonTasks(p);
        }*/

        gui = new GraphicalUserInterface(this);
        gui.registerAndLogin.setVisible(true);

    }

    //FICHEIROS

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

                                person = new Teacher(aux[2], aux[3], mechaNumber, aux[5]);
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
                                e.printStackTrace();
                            }catch (NullPointerException e){
                                System.out.println("Erro ao adicionar na lista: " + e.getMessage());
                                e.printStackTrace();
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
       int duration, indexP = 0, indexT, indexPerson, indexR, conclusionState;
       double effortRate;
       boolean safe;
       projects=new ArrayList<>();

       File fProj = new File("Projects.txt");

       if(fProj.exists() && fProj.isFile()) {
           try{
               FileReader fr = new FileReader(fProj);
               BufferedReader br = new BufferedReader(fr);

               while((line = br.readLine()) != null) {

                   aux = line.split(";");
                   safe=true;
                   if(aux[0].equalsIgnoreCase("PROJECT") && aux.length == 9){

                       try {
                           indexP=Integer.parseInt(aux[1]);
                           for (Project p: projects){
                               if( p.getIndex() == indexP && indexP !=0){
                                   safe=true;
                               }
                           }
                       } catch (NumberFormatException e){
                           System.out.println("Error in conversion project's index: " + e.getMessage());
                           e.printStackTrace();
                       }

                       if(safe){
                           try {
                               date1 = format.parse(aux[4]);
                               date2 = format.parse(aux[8]);
                               duration = Integer.parseInt(aux[5]);
                               indexR = Integer.parseInt(aux[7]);

                               project = new Project(indexP, aux[2],aux[3], date1, duration);
                               project.setEndDate(date2);

                               peopleProject = aux[6].split(",");
                               for (int i = 0; i < peopleProject.length; i++) {
                                   try{
                                       indexPerson = Integer.parseInt(peopleProject[i]);
                                       for(Person personAux: people){
                                           if(personAux.calcCost()==0){
                                               Teacher t = (Teacher) personAux;
                                               if(indexPerson == t.getMechaNumber()){
                                                   addPersonToProject(personAux, project);
                                               }
                                               else if(indexR == t.getMechaNumber()){
                                                   if(!project.getPeople().contains(t)){
                                                       addPersonToProject(personAux, project);
                                                   }
                                                   project.setPrincipal(t);
                                               }
                                           }
                                           else{
                                               Scholar s =(Scholar) personAux;
                                               if(indexPerson == s.getIndex()){
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
                                       e.printStackTrace();
                                   }
                               }
                               projects.add(project);

                           } catch (ParseException e) {
                               e.printStackTrace();
                               System.out.println("Error in date: " + e.getMessage());
                               e.printStackTrace();
                           } catch (NumberFormatException e){
                               System.out.println("Error in conversion: " + e.getMessage());
                               e.printStackTrace();
                           } catch (NullPointerException e ){
                               System.out.println("Error in adding to the ArrayList: " + e.getMessage());
                               e.printStackTrace();
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

                                           if(effortRate == 0.25){ //Documentation
                                               task = new Documentation(aux[3], date1, date2, duration, conclusionState, personAux, indexT);
                                               p.addTask(task);
                                               personAux.addTaskToPerson(task);
                                           }
                                           else if(effortRate == 0.5){ //Design
                                               task = new Design(aux[3], date1, date2, duration, conclusionState, personAux, indexT);
                                               p.addTask(task);
                                               personAux.addTaskToPerson(task);
                                           }
                                           else if(effortRate == 1.00){ //Design
                                               task = new Development(aux[3], date1, date2, duration, conclusionState, personAux, indexT);
                                               p.addTask(task);
                                               personAux.addTaskToPerson(task);
                                           }

                                       } catch (NumberFormatException e) {
                                           System.out.println("Error in conversion " + e.getMessage());
                                           e.printStackTrace();
                                       } catch (ParseException e) {
                                           e.printStackTrace();
                                           System.out.println("Error in date: " + e.getMessage());
                                           e.printStackTrace();
                                       }
                                   }
                               }
                           }
                       } catch (NumberFormatException e) {
                           System.out.println("Error in conversion:  "+ e.getMessage());
                           e.printStackTrace();
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

    /**
     * write back in object's files all data
     */
    public void writeObjectsFile(){
        File fproject = new File("Project.obj");
        File fperson = new File("Person.obj");

        try {
            FileOutputStream fos = new FileOutputStream(fproject);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(projects);

            oos.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error creating project's file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error writing in project's file:" + e.getMessage());
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(fperson);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(people);

            oos.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error creating person's file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error writing in person's file:" + e.getMessage());
            e.printStackTrace();
        }


    }

    /**
     * read from the object's files (if it doesn't exist will read from the normal files)
     */
    public void readObjectFile(){

        File fProject = new File("Project.obj");
        File fPerson = new File("Person.obj");

        if(fPerson.exists() && fPerson.isFile()){
            try {
                FileInputStream fis = new FileInputStream(fPerson);
                ObjectInputStream ois = new ObjectInputStream(fis);

                people = (ArrayList<Person>) ois.readObject();

                ois.close();
                fis.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Error opening the people's file");
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println("Error reading the people's file");
                ex.printStackTrace();
            }catch (ClassNotFoundException ex) {
                System.out.println("Error converting the object");
                ex.printStackTrace();
            }
        }
        else{
            readFilePeople();
        }

        if(fProject.exists() && fProject.isFile()){
            try {
                FileInputStream fis = new FileInputStream(fProject);
                ObjectInputStream ois = new ObjectInputStream(fis);

                projects = (ArrayList<Project>) ois.readObject();

                ois.close();
                fis.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Error opening the project's file");
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println("Error reading the project's file");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                System.out.println("Error converting the object");
                ex.printStackTrace();
            }
        }
        else{
            readFileProjects();
        }

        writeObjectsFile();
    }


    //PROJETOS


    /**
     * create a new project and add to the ArrayList of projects
     * @param name name of the project
     * @param acronym acronym
     * @param startDate start date of the project
     * @param duration estimated duration
     * @return the project created
     */
    public Project createProject(String name, String acronym, Date startDate, int duration){
        int index = projects.size()+1;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Project proj = new Project( index, name, acronym, startDate, duration);
        try {
            proj.setEndDate(format.parse("00/00/0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        projects.add(proj);
        return proj;
    }

    public void listAllProjects(){
        for(int i = 0; i < projects.size(); i++){
            System.out.println(projects.get(i).getIndex() + ". " + projects.get(i).getName());
        }
    }

    /**
     * list all people in the project
     * @param project Project
     * @return ArrayList with all people in the project
     */
    public ArrayList<Person> listPeopleInProject(Project project){
        return project.listPeopleInProject();
    }

    //TASKS

    public void addTask(Project project, String name, Date startDate, Date endDate, int duration, int conclusionState, Person responsible, double effortRate){
        boolean a = true;
        int index = project.getTasks().size()+1;
        for (int i = 0; i < project.getTasksLen(); i++){
            if (project.getTask(i).getIndex() == index){
                System.out.println("Already existing task!\n");
                a = false;
            }
        }
        if (a) {
            if (effortRate == 0.5) {
                project.addTask(new Design(name, startDate, endDate, duration, conclusionState, responsible, index));
            }
            else if (effortRate == 0.25) {
                project.addTask(new Documentation(name, startDate, endDate, duration, conclusionState, responsible, index));
            }
            else if (effortRate == 1) {
                project.addTask(new Development(name, startDate, endDate, duration, conclusionState, responsible, index));
            }
        }
    }

    /**
     * list all task from one project
     * @param project Project
     * @return ArrayList of project's tasks
     */
    public ArrayList<Task> listTasks(Project project){
        return project.listTasks();
    }

    public void taskConclusionState(Task task){
        System.out.println("Conclusion state: " + task.getConclusionState());
    }

    /**
     * list all non initialized tasks in the project
     * @param project Project
     * @return ArrayList of tasks non initialized in the project
     */
    public ArrayList<Task> listNonInitialized(Project project){
        return project.listNonInitialized();
    }


    //PERSON

    /**
     * create a teacher and add to the list
     * @param name name
     * @param email e-mail
     * @param mechaNumber mecanographic number
     * @param researchArea research area
     * @return person
     */
    public Person createTeacher(String name, String email, int mechaNumber, String researchArea){
        Person person;
        person = new Teacher(name, email, mechaNumber, researchArea);

        people.add(person);
        return person;
    }

    /**
     * create a scholar
     * @param name name
     * @param email e-mail
     * @param startDate start date of the scholarship
     * @param finalDate final date of the scholarship
     * @param statute  statute identify if is a bachelor, a master or a doctor
     * @return person
     */
    public Person createScholar(String name, String email, Date startDate, Date finalDate, int statute){
        Person person;
        int index = people.size() + 1;
        if(statute==1){
            person = new Bachelor(name, email, startDate, finalDate, index);
            people.add(person);
        }
        else if(statute==2){
            person = new Master(name, email, startDate, finalDate, index);
            people.add(person);
        }
        else{
            person = new Doctor(name, email, startDate, finalDate, index);
            people.add(person);
        }
        return person;
    }

    /**
     * print all people in CISUC
     */
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

    /**
     * print all associates (teacher in charge of scholars and vice-versa)
     * @param person teacher or scholar
     */
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

    /**
     * print all person's tasks
     * @param person person
     */
    public void listPersonTasks(Person person){
        for(int i=0; i<person.getTasksLen(); i++){
            System.out.println(person.getTask(i));
        }
    }

    public void listPersonProjects(){}

    public void deleteProjectFromPerson(Person person, Project proj){
        if (projects.contains(proj)){
            person.deleteProjectFromPerson(proj);
        }
        else {
            System.out.println("This project doesn't belong to CISUC");
        }
    }

    /**
     * add person to task (and vise-versa)
     * @param person person
     * @param task task
     */
    public void addPersonToTask(Person person, Task task){
        for (int i = 0; i < person.getTasksLen(); i++) {
            if(person.getTask(i).getIndex()==task.getIndex()){
                task.addPersonToTask(person);
            }
        }
    }

    /**
     * add task to person
     * @param person person
     * @param task task
     */
    public void addTaskToPerson(Person person, Task task){
        person.addTaskToPerson(task);
    }

    /**
     * delete task from person
     * @param person person
     * @param t task that we intend to disassociate
     */
    public void deleteTaskFromPerson(Person person, Task t){
        person.deleteTaskFromPerson(t);
    }

    /**
     * Evaluate the state of a project
     * @param proj project
     * @return true if the project is complete and false otherwise (endDate equals 00/00/0000)
     */
    public boolean projectState(Project proj){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if(proj.getEndDate().compareTo(format.parse("00/00/0000"))==1){
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * associate project to person
     * @param person person
     * @param project project
     */
    public void addProjectToPerson(Person person, Project project){
        person.addProjectToPerson(project);
    }

    /**
     * associate person to a project
     * @param person person
     * @param project project
     */
    public void addPersonToProject(Person person, Project project){
        project.addPersonToProject(person);
    }

}
