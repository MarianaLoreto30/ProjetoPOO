package Projeto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window1 extends JFrame {
    private final GraphicalUserInterface gui;

    public Window1(GraphicalUserInterface gui) {
        this.gui = gui;

        this.setTitle("Window 1");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btn = new JButton("Return "/*+gui.cisuc.listPerson.size()*/);
        btn.setBounds(50,50,100,25);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnActionPerformed();
            }
        });

        JPanel panel = new JPanel(null);
        panel.add(btn);

        this.add(panel);
    }

    private void btnActionPerformed() {
        gui.setVisible(true);
        this.setVisible(false);
    }
}
