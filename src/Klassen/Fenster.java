package Klassen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenster {
    private JButton start;
    private JButton ende;

    private ImageIcon startbild = new ImageIcon(getClass().getResource("Bilder/startknopf.png"));
    private ImageIcon endebild = new ImageIcon(getClass().getResource("Bilder/endeknopf.png"));

    private JLabel hintergrund;

    public Fenster() {
        JFrame gui = new JFrame();
        gui.setSize(1000, 800);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLocationRelativeTo(null);
        gui.setResizable(false);

        hintergrund = new JLabel();
        hintergrund.setSize(1000, 800);

        gui.add(hintergrund);

        baueknoepfe();

        gui.setVisible(true);

        Spielstart SS = new Spielstart(hintergrund);
    }

    private void baueknoepfe() {
        start = new JButton(startbild);
        start.setBounds(100, 100, 800, 200);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hintergrund.removeAll();
                hintergrund.setIcon(null);
                ElementAuswahl EA = new ElementAuswahl(hintergrund);
            }
        });

        ende = new JButton(endebild);
        ende.setBounds(100, 400, 800, 200);
        ende.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        hintergrund.add(start);
        hintergrund.add(ende);
    }

    public static void main(String[] args) {
        new Fenster();
    }
}
