package Projeto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GraphicalUserInterface{

    public CISUC cisuc;
    //VARIABLES
    private SimpleDateFormat format;
    private JList list;
    private  DefaultListModel listValues;
    public JFrame registerAndLogin;
    private JFrame loginFrame;
    private JFrame registerFrame;
    private JFrame cisucPage;
    public JFrame projectsPage;
    private JFrame personPage;
    private Person person;


    public GraphicalUserInterface(CISUC cisuc) {
        this.cisuc = cisuc;
        this.format= new SimpleDateFormat("dd/MM/yyyy");

        registerAndLogin=registerAndLogin();
        loginFrame = login();
        registerFrame = register();
        cisucPage = cisucPage();
        projectsPage = projectPage();
        personPage = personPage();



    }

    /**
     * JPanel for the register or login
     * @return JPanel for the register or login
     */
    public JFrame registerAndLogin(){

        JFrame frame = new JFrame();
        JLabel labelWelcome;
        JButton register;
        JButton login;
        JPanel panelWelcome;

        frame.setTitle("CISUC - investigation center");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(closeOperartion());
        labelWelcome = new JLabel("Welcome!");
        labelWelcome.setBounds(150, 90, 100, 25);

        register = new JButton("Register");
        register.setBounds(70, 150, 100, 20);

        login = new JButton("Login");
        login.setBounds(180, 150, 100, 20);

        panelWelcome = new JPanel();
        panelWelcome.setLayout(null);
        panelWelcome.add(labelWelcome);
        panelWelcome.add(register);
        panelWelcome.add(login);

        frame.add(panelWelcome);

        //actions

        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                registerAndLogin.setVisible(false);
                loginFrame.setVisible(true);
            }
        });
        register.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                registerAndLogin.setVisible(false);
                registerFrame.setVisible(true);
            }
        });

        return frame;
    }

    private JFrame login (){

        JFrame frame = new JFrame();

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(closeOperartion());

        JLabel email = new JLabel("E-mail");
        email.setBounds(50, 50, 100, 25);
        JTextField emailText = new JTextField(10);
        emailText.setBounds(50, 75, 250, 25);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 100, 100, 25);
        JTextField nameText = new JTextField(10);
        nameText.setBounds(50, 125, 250, 25);

        JButton button = new JButton("Login");
        button.setBounds(50, 160, 100, 25);

        JButton goBack = new JButton("Back");
        goBack.setBounds(200, 160, 100, 25);

        button.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                int flag=0;

                if(nameText.getText().isEmpty() || emailText.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "All fields must be complete", "Attention!", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    for(Person p: cisuc.people){
                        if(p.getName().equalsIgnoreCase(nameText.getText()) && p.getEmail().equalsIgnoreCase(emailText.getText())){
                            frame.setVisible(false);
                            cisucPage.setVisible(true);
                            flag = 1;
                            break;
                        }
                        else{
                            flag =0;
                        }
                    }
                    if(flag == 0){
                        JOptionPane.showMessageDialog(null, "Incorrect name or email", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                registerAndLogin.setVisible(true);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(email);
        panel.add(emailText);
        panel.add(name);
        panel.add(nameText);
        panel.add(button);
        panel.add(goBack);

        frame.add(panel);

        return frame;
    }

    private JFrame register(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        int altura = 400;
        int comprimento = 800;

        frame.setSize(comprimento, altura);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(closeOperartion());

        String[] statute = {"Teacher", "Bachelor", "Master", "Doctor"};

        JComboBox statuteBox = new JComboBox(statute);
        statuteBox.setBounds(comprimento/4, altura/8, comprimento/2, altura/8 );
        ComboBoxStatute comboBoxStatute = new ComboBoxStatute();
        statuteBox.addActionListener(comboBoxStatute);

        JLabel choose = new JLabel("Choose a statute:");
        choose.setBounds(comprimento/4, altura/14, comprimento/2, 25);

        panel.setLayout(null);
        panel.add(statuteBox);
        panel.add(choose);

        frame.add(panel);

        return frame;
    }

    private JFrame registerChoice(int statute){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        int altura = 400;
        int comprimento = 800;

        frame.setSize(comprimento, altura);
        frame.setResizable(false);
        frame.setTitle("New register");
        frame.setDefaultCloseOperation(closeOperartion());

        JButton confirm = new JButton("Confirm");
        JButton goBack = new JButton("Back");

        goBack.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                    frame.setVisible(false);
                    registerFrame.setVisible(true);
            }
        });

        JLabel email = new JLabel("   E-mail: ");
        JTextField emailText = new JTextField(10);

        JLabel name = new JLabel("   Name: ");
        JTextField nameText = new JTextField(10);

        if(statute == 0){
            JLabel teacher = new JLabel("   Teacher");

            JLabel mechaNumber = new JLabel("   Mechanographic number: ");
            JTextField mechaNumberText = new JTextField(10);

            JLabel researchArea = new JLabel("   Research Area: ");
            JTextField researchAreaText = new JTextField(10);

            panel.setLayout(new GridLayout(6, 2));
            panel.add(teacher);
            panel.add(new JLabel(""));
            panel.add(name);
            panel.add(nameText);
            panel.add(email);
            panel.add(emailText);
            panel.add(mechaNumber);
            panel.add(mechaNumberText);
            panel.add(researchArea);
            panel.add(researchAreaText);
            panel.add(confirm);
            panel.add(goBack);

            confirm.addActionListener(  new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    try{
                        if(nameText.getText().isEmpty() || emailText.getText().isEmpty() || mechaNumber.getText().isEmpty() || researchAreaText.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "All fields must be complete", "Attention!", JOptionPane.PLAIN_MESSAGE);
                        }
                        else if(mechaNumberText.getText().length() >= 10){
                            JOptionPane.showMessageDialog(null, "Invalid number", "Attention!", JOptionPane.PLAIN_MESSAGE);
                        }
                        else{
                            int number = Integer.parseInt(mechaNumberText.getText());
                            for(int i=0; i<cisuc.people.size(); i++){
                                if(cisuc.people.get(i).calcCost()==0){
                                    Teacher t = (Teacher) cisuc.people.get(i);
                                    if(t.getMechaNumber() == number){
                                        String value = JOptionPane.showInputDialog(null, "That number already exists, introduce a new one: ", "Mecanograph number", JOptionPane.QUESTION_MESSAGE);
                                        number = Integer.parseInt(value);
                                        mechaNumberText.setText(value);
                                        i=0;
                                    }
                                }
                            }
                            person = cisuc.createTeacher(nameText.getText(), emailText.getText(), number, researchAreaText.getText());
                        }
                    }
                    catch (NumberFormatException ex) {
                        System.out.println("Erro ao converter number: " + ex.getMessage());
                        ex.printStackTrace();
                    }

                    frame.setVisible(false);
                    cisucPage.setVisible(true);
                }
            });

        }
        else {
            panel.setLayout(new GridLayout(6, 2));

            JLabel startDate = new JLabel("   Start date: ");
            JTextField startDateText = new JTextField(10);

            JLabel endDate = new JLabel("   End date: ");
            JTextField endDateText = new JTextField(10);

            if (statute == 1){
                JLabel bachelor = new JLabel("   Bachelor");
                panel.add(bachelor);
            }
            else if (statute == 2){
                JLabel master = new JLabel("   Master");
                panel.add(master);
            }
            else if (statute == 3){
                JLabel doctor = new JLabel("   Doctor");
                panel.add(doctor);
            }

            panel.add(new JLabel(""));
            panel.add(name);
            panel.add(nameText);
            panel.add(email);
            panel.add(emailText);
            panel.add(startDate);
            panel.add(startDateText);
            panel.add(endDate);
            panel.add(endDateText);
            panel.add(confirm);
            panel.add(goBack);

            confirm.addActionListener(  new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    try{
                        if(nameText.getText().isEmpty() || emailText.getText().isEmpty() || startDate.getText().isEmpty() || endDate.getText().isEmpty()){
                            JOptionPane.showMessageDialog(null, "All fields must be complete", "Attention!", JOptionPane.PLAIN_MESSAGE);
                        }
                        else{
                            Date date1, date2, currentDate;
                            currentDate = new Date();
                            date1 = format.parse(startDateText.getText());
                            date2 = format.parse(endDateText.getText());

                            while(date2.before(currentDate)){
                                String value = JOptionPane.showInputDialog(null, "The current date cannot exceed the end date, write a new one", "Error in end date!", JOptionPane.QUESTION_MESSAGE);date2 = format.parse(endDateText.getText());
                                date2 = format.parse(value);
                                endDateText.setText(value);

                            }
                            person = cisuc.createScholar(nameText.getText(), emailText.getText(), date1, date2, statute);
                        }
                    }
                    catch (ParseException ex) {
                        System.out.println("Error converting date: " + ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Incorrect date format", "Attention!", JOptionPane.PLAIN_MESSAGE);
                        //ex.printStackTrace();
                    }

                    frame.setVisible(false);
                    cisucPage.setVisible(true);
                }
            });
        }

        frame.add(panel);

        return frame;
    }

    private class ComboBoxStatute implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox)e.getSource();
            String statute = (String) comboBox.getSelectedItem();

            if(statute.equalsIgnoreCase("Teacher") ){
                registerFrame.setVisible(false);
                registerChoice(0).setVisible(true);

            }
            else if(statute.equalsIgnoreCase("Bachelor")){
                registerFrame.setVisible(false);
                registerChoice(1).setVisible(true);
            }
            else if(statute.equalsIgnoreCase("Master")){
                registerFrame.setVisible(false);
                registerChoice(2).setVisible(true);
            }
            else if(statute.equalsIgnoreCase("Doctor")){
                registerFrame.setVisible(false);
                registerChoice(3).setVisible(true);
            }
        }
    }

    private JFrame cisucPage(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setTitle("CISUC - investigation center");
        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(closeOperartion());


        JLabel label = new JLabel("CISUC");
        label.setBounds(325, 100, 100, 25);
        label.setForeground(Color.cyan);
        label.setFont(new Font("Calibri", Font.BOLD,20));

        JButton projects = new JButton("Projects");
        projects.setBounds(150, 150, 150, 20);

        JButton people = new JButton("People");
        people.setBounds(400, 150, 150, 20);

        panel.setLayout(null);
        panel.add(label);
        panel.add(projects);
        panel.add(people);

        frame.add(panel);

        return  frame;
    }

    private JFrame projectPage(){
        JFrame frame = new JFrame();

        frame.setResizable(false);
        frame.setTitle("Projects");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(closeOperartion());

        listValues = new DefaultListModel();

        listValues.addElement("Create project");
        listValues.addElement("List all projects");
        listValues.addElement("List finished projects");
        listValues.addElement("List unfinished projects");

        list = new JList(listValues);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(50, 60, 300, 80);

        JButton btnSelected = new JButton("Select");
        JButton goBack = new JButton("Back");


        btnSelected.setBounds(50, 200, 120, 25);
        goBack.setBounds(200, 200, 120, 25);


        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                cisucPage().setVisible(true);
            }
        });
        btnSelected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = list.getSelectedIndex();
                projectsChoice(index).setVisible(true);
                frame.setVisible(false);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.add(listScroller);
        panel.add(btnSelected);
        panel.add(goBack);
        frame.add(panel);

        return frame;
    }

    private JFrame personPage(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setResizable(false);
        frame.setTitle("People");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(closeOperartion());

        DefaultListModel listOption = new DefaultListModel();

        listOption.addElement("List all people");
        listOption.addElement("List my projects");
        listOption.addElement("List my tasks");

        JList list = new JList(listOption);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(50, 60, 300, 80);

        JButton Selected = new JButton("Select");
        JButton goBack = new JButton("Back");

        Selected.setBounds(50, 200, 120, 25);
        goBack.setBounds(100, 200, 120, 25);

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                cisucPage.setVisible(true);
            }
        });

        Selected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                personPageAction(list.getSelectedIndex()).setVisible(true);
            }
        });

        panel.setLayout(null);

        panel.add(listScroller);
        panel.add(Selected);
        panel.add(goBack);
        frame.add(panel);

        return frame;
    }

    private JFrame personPageAction(int action){
        JFrame frame = new JFrame();

        frame.setResizable(false);
        frame.setTitle("People");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(closeOperartion());

        if(action==1){

        }


        return frame;
    }

    private JFrame projectsPage() {

        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Projects");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listValues = new DefaultListModel();

        listValues.addElement("Create project");
        listValues.addElement("List all projects");
        listValues.addElement("List finished projects");
        listValues.addElement("List unfinished projects");

        list = new JList(listValues);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(50, 60, 300, 80);

        JButton btnSelected = new JButton("Select");
        JButton goBack = new JButton("Back");

        btnSelected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = list.getSelectedIndex();
                projectsChoice(index).setVisible(true);
                frame.setVisible(false);
            }
        });

        btnSelected.setBounds(50, 200, 120, 25);
        goBack.setBounds(100, 200, 120, 25);


        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                cisucPage().setVisible(true);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.add(listScroller);
        panel.add(btnSelected);
        panel.add(goBack);
        frame.add(panel);

        return frame;
    }

    private JFrame projectsChoice(int index) {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Date startData;

        if (index == 0) {

            frame.setTitle("New Project");
            JLabel name = new JLabel("Name:");
            JTextField nametxt = new JTextField(10);
            JLabel acronym = new JLabel("Acronym:");
            JTextField acronymtxt = new JTextField(10);
            JLabel startDate = new JLabel("Start Date:");
            JTextField startDatetxt = new JTextField(10);
            JLabel duration = new JLabel("Duration:");
            JTextField durationtxt = new JTextField(10);
            JButton done = new JButton("Done");
            JButton goBack = new JButton("Back");

            goBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    projectsPage.setVisible(true);
                }
            });

            done.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (nametxt.getText().isEmpty() || acronymtxt.getText().isEmpty() || startDatetxt.getText().isEmpty() || durationtxt.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "All fields must be complete", "Attention!", JOptionPane.PLAIN_MESSAGE);
                        }
                        Date startData = (format.parse(startDatetxt.getText()));
                        int duration = Integer.parseInt(durationtxt.getText());
                        cisuc.createProject(nametxt.getText(), acronymtxt.getText(), startData, duration);
                    }
                    catch(ParseException ex){
                        System.out.println("Error converting date: " + ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Incorrect date format", "Attention!", JOptionPane.PLAIN_MESSAGE);
                    }
                    catch(NumberFormatException ex){
                        System.out.println("Must be an integer: " + ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Duration must be an integer", "Attention!", JOptionPane.PLAIN_MESSAGE);
                    }
                    frame.setVisible(false);
                    projectsPage.setVisible(true);

                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));
            panel.add(name);
            panel.add(nametxt);
            panel.add(acronym);
            panel.add(acronymtxt);
            panel.add(startDate);
            panel.add(startDatetxt);
            panel.add(duration);
            panel.add(durationtxt);
            panel.add(done);
            panel.add(goBack);
            frame.add(panel);
            frame.setVisible(true);



        } else if (index == 1) {
            frame.setTitle("All Projects");
            JLabel label = new JLabel("All Projects");
            label.setBounds(50, 10, 300, 25);
            DefaultListModel allProj = new DefaultListModel();
            JList listAllProj = new JList(allProj);

            for (Project p:cisuc.projects)
                allProj.addElement(p.getName());

            JScrollPane listScroller = new JScrollPane(listAllProj);
            listScroller.setBounds(50, 35, 300, 150);
            JButton goBack = new JButton("Back");
            goBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    projectsPage.setVisible(true);
                }
            });
            goBack.setBounds(50, 200, 120, 25);
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.add(label);
            panel.add(listScroller);
            panel.add(goBack);
            frame.add(panel);
        }


        else if(index == 2) {
            frame.setTitle("Finished Projects");
            JLabel label = new JLabel("Finished Projects");
            label.setBounds(50, 10, 300, 25);
            DefaultListModel fProj = new DefaultListModel();
            JList listfProj = new JList(fProj);

            for (Project p : cisuc.finishedProjects())
                fProj.addElement(p.getName());

            JScrollPane listScroller = new JScrollPane(listfProj);
            listScroller.setBounds(50, 35, 300, 150);
            JButton goBack = new JButton("Back");
            goBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    projectsPage.setVisible(true);
                }
            });
            goBack.setBounds(50, 200, 120, 25);
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.add(label);
            panel.add(listScroller);
            panel.add(goBack);
            frame.add(panel);
        }
        else if(index == 3){
            frame.setTitle("Unfinished Projects");
            JLabel label = new JLabel("Unfinished Projects");
            label.setBounds(50, 10, 300, 25);
            DefaultListModel unfProj = new DefaultListModel();
            JList listunfProj = new JList(unfProj);

            for (Project p : cisuc.unfinishedProjects())
                unfProj.addElement(p.getName());

            JScrollPane listScroller = new JScrollPane(listunfProj);
            listScroller.setBounds(50, 35, 300, 150);
            JButton goBack = new JButton("Back");
            goBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    projectsPage.setVisible(true);
                }
            });
            goBack.setBounds(50, 200, 120, 25);
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.add(label);
            panel.add(listScroller);
            panel.add(goBack);
            frame.add(panel);
        }
        return frame;

    }
    private int closeOperartion(){

        cisuc.writeObjectsFile();

        /*int option = JOptionPane.showConfirmDialog(null, "You sure that you want to leave?", " ", JOptionPane.YES_NO_OPTION);

        if(option == JOptionPane.YES_OPTION){
            return JFrame.EXIT_ON_CLOSE;
        }
        else{
            return JFrame.DO_NOTHING_ON_CLOSE;
        }*/

        return JFrame.EXIT_ON_CLOSE;
    }

}
