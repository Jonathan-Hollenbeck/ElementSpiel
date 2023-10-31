package Klassen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Spielstart {
    private JButton start;
    private JButton ende;

    private JLabel hintergrund;

    private ImageIcon hintergrundbild = new ImageIcon(getClass().getResource("Bilder/spielstarthintergrund.png"));
    private ImageIcon startbild = new ImageIcon(getClass().getResource("Bilder/startknopf.png"));
    private ImageIcon endebild = new ImageIcon(getClass().getResource("Bilder/endeknopf.png"));

    public Spielstart(JLabel h) {
        hintergrund = h;
        hintergrund.setIcon(hintergrundbild);
        baueknoepfe();
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
}