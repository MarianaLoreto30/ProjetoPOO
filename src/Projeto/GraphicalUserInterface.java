package Projeto;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
        this.setSize( width, height);
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


}
