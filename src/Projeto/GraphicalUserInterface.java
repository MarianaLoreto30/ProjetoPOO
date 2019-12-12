package Projeto;

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
        });*/


        jPanel = new JPanel(null);
        //jPanel.add(jListScroller);
        //jPanel.add(jButtonSelected);

        this.add(jPanel);
    }/*

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
    }*/

    private void createList() {
        listValues = new DefaultListModel();
        listValues.addElement("Item 1");
        listValues.addElement("Item 2");
        listValues.addElement("Item 3");

    }


import javax.swing.*;
import java.awt.*;
import java.util.*;
    public class interfacegrafica extends JFrame{

        public interfacegrafica() {


            /////////////////////reguster and login////////////////////////////

/*        setTitle("Painel e componentes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Welcome!");
        label.setBounds(150, 90, 100, 25);
        JButton registar = new JButton("Registar");
        registar.setBounds(70, 150, 100, 20);
        JButton login = new JButton("Login");
        login.setBounds(180, 150, 100, 20);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(label);
        panel.add(registar);
        panel.add(login);
        add(panel);
        setVisible(true);*//*



        /////////////////////register or login////////////////////////////

      */
/*      JFrame frame = new JFrame();
        frame.setSize(400, 300);
        JLabel email = new JLabel("E-mail");
        email.setBounds(50, 50, 100, 25);
        JTextField emailText = new JTextField(10);
        emailText.setBounds(50, 75, 200, 25);
        JLabel name = new JLabel("Name");
        name.setBounds(50, 100, 100, 25);
        JTextField nameText = new JTextField(10);
        nameText.setBounds(50, 125, 200, 25);
        JButton button = new JButton("Register"); // "Login"
        button.setBounds(50, 160, 100, 25);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(email);
        panel.add(emailText);
        panel.add(name);
        panel.add(nameText);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);*//*


        /////////////////////////////Pagina Inicial//////////////////////////////////

*/
/*        setTitle("CISUC");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("CISUC");
        label.setBounds(160, 90, 100, 25);
        JButton projects = new JButton("Projects");
        projects.setBounds(70, 150, 100, 20);
        JButton people = new JButton("People");
        people.setBounds(180, 150, 100, 20);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(label);
        panel.add(projects);
        panel.add(people);
        add(panel);
        setVisible(true);*/


/////////////////////////////////PROJETOS/////////////////////////////////

            JList list;
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
            frame.setVisible(true);

        }

        private

        public static void main(String[] args) {
            new interfacegrafica();
        }
    }

}
