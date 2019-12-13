package Projeto;

/*
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GraphicalUserInterface extends JFrame{

    private JList jList;
    private JPanel jPanel;
    public CISUC cisuc;
    private JButton jButtonSelected;
    private JButton jButtonAdd;
    DefaultListModel listValues;
    private  int height;
    private int width;

    public GraphicalUserInterface(CISUC cisuc) {
        this.cisuc = cisuc;

        width=400;
        height=300;

        this.setTitle("CISUC - investigation Center");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(width/2,height/2));


        /*
        jList = new JList();
        createList();

        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                jListSelectionListener(evt);
            }
        });

        JScrollPane jListScroller = new JScrollPane(jList);
        jListScroller.setBounds(width/8, height/6, 3/4*width, height/3);

        jButtonSelected = new JButton("Selected");
        jButtonSelected.setBounds(width/8, height/3 + 75, 1/4*width, 25);
        jButtonSelected.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonSelectedActionPerformed(evt);
            }
        });

        jButtonAdd = new JButton("Add person");
        jButtonAdd.setBounds(width/8+75,height/3 + 75, 1/4*width, 25);
        jButtonAdd.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });


        jPanel = new JPanel(null);
        //jPanel.add(jListScroller);
        //jPanel.add(jButtonSelected);

        this.add(jPanel);
    }

    private void jListSelectionListener(ListSelectionEvent evt) {
       String str = jList.getSelectedValue().toString();
       System.out.println("SELECTED: "+str+"!!!");
    }

    private void jButtonAddActionPerformed(ActionEvent evt) {
        Window1 w = new Window1(this);
        w.setVisible(true);
        this.setVisible(false);
//        String value = JOptionPane.showInputDialog(null, "Introduza o nome", "Input", JOptionPane.QUESTION_MESSAGE);
//        System.out.println("NOME: "+value);
//        Person p = new Person((int)(Math.random()*10000), value);
//        program.addPerson(p);
//
////        listValues.addElement(p.getNome());
//        createList();
    }


    private void jButtonSelectedActionPerformed(ActionEvent evt) {
        String str = jList.getSelectedValue().toString();
        System.out.println("SELECTED: "+str+"!!!");

        JOptionPane.showMessageDialog(null, "Selected person '"+str+"'", "Janela de mensagem!", JOptionPane.PLAIN_MESSAGE);
    }

    private void createList() {
        listValues = new DefaultListModel();
        listValues.addElement("Item 1");
        listValues.addElement("Item 2");
        listValues.addElement("Item 3");

    }*/

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
    private int width;
    private int height;
    private SimpleDateFormat format;
    public JFrame registerAndLogin;
    private JFrame loginFrame;
    private JFrame registerFrame;
    private JFrame cisucPage;


    public GraphicalUserInterface(CISUC cisuc) {
        this.cisuc = cisuc;
        this.width = 400;
        this.height = 300;
        this.format= new SimpleDateFormat("dd/MM/yyyy");

        registerAndLogin=registerAndLogin();
        loginFrame = login();
        registerFrame = register();
        cisucPage = cisucPage();



        /*JList list;
        JLabel labelSelectedValue;
        JFrame frame = new JFrame();
        frame.setTitle("JList");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Lista de valores (selecionar 1 ou mais valores)");
        label.setBounds(50, 10, 300, 25);
        DefaultListModel listValues = new DefaultListModel();
        listValues.addElement("Item 1");
        listValues.addElement("Item 2");
        listValues.addElement("Item 3");
        listValues.addElement("Item 4");
        listValues.addElement("Item 5");
        listValues.addElement("Item 6");
        listValues.addElement("Item 7");
        listValues.addElement("Item 8");
        listValues.addElement("Item 9");
        listValues.addElement("Item 10");
        listValues.addElement("Item 11");
        listValues.addElement("Item 12");
        list = new JList(listValues);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(50, 35, 300, 150);
        JButton btnSelected = new JButton("Selecionado ");
        ButtonListener btnActionListener = new ButtonListener();
        btnSelected.addActionListener(btnActionListener);
        btnSelected.setBounds(50, 200, 120, 25);
        labelSelectedValue = new JLabel("-");
        labelSelectedValue.setBounds(180, 200, 170, 25);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(label);
        panel.add(listScroller);
        panel.add(btnSelected);
        panel.add(labelSelectedValue);
        frame.add(panel);
        frame.setVisible(true);*/

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
        frame.setSize(width, height);
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
        emailText.setBounds(50, 75, 200, 25);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 100, 100, 25);
        JTextField nameText = new JTextField(10);
        nameText.setBounds(50, 125, 200, 25);

        JButton button = new JButton("Login");
        button.setBounds(50, 160, 100, 25);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(email);
        panel.add(emailText);
        panel.add(name);
        panel.add(nameText);
        panel.add(button);

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
                            cisuc.createTeacher(nameText.getText(), emailText.getText(), number, researchAreaText.getText());
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
                            cisuc.createScholar(nameText.getText(), emailText.getText(), date1, date2, statute);
                        }
                    }
                    catch (ParseException ex) {
                        System.out.println("Error converting date: " + ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Date have incorrect format", "Attention!", JOptionPane.PLAIN_MESSAGE);
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

    private int closeOperartion(){

        cisuc.writeObjectsFile();
        return JFrame.EXIT_ON_CLOSE;

    }
}
