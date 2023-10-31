package Klassen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElementAuswahl {

    private Feuer f = new Feuer();
    private Wasser w = new Wasser();
    private Erde e = new Erde();
    private Luft l = new Luft();

    private JLabel hintergrund;

    private JButton afeuer, awasser, aerde, aluft;
    private JButton bfeuer, bwasser, berde, bluft;
    private JButton comGeg;
    private JButton zurueck;

    private int aelement, belement;

    private boolean ki = false;

    private ImageIcon hintergrundbild = new ImageIcon(getClass().getResource("Bilder/spielstarthintergrund.png"));
    private ImageIcon zurueckbild = new ImageIcon(getClass().getResource("Bilder/zurueckknopf.png"));
    private ImageIcon kibild = new ImageIcon(getClass().getResource("Bilder/kibild.png"));

    public ElementAuswahl(JLabel h) {
        hintergrund = h;
        hintergrund.setIcon(hintergrundbild);

        baueknoepfea();

        hintergrund.add(zurueck);
        hintergrund.add(afeuer);
        hintergrund.add(awasser);
        hintergrund.add(aerde);
        hintergrund.add(aluft);
        hintergrund.add(bfeuer);
        hintergrund.add(bwasser);
        hintergrund.add(berde);
        hintergrund.add(bluft);
        hintergrund.add(comGeg);
    }

    private void baueknoepfea() {
        zurueck = new JButton(zurueckbild);
        zurueck.setBounds(0, 0, 100, 50);
        zurueck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hintergrund.removeAll();
                hintergrund.setIcon(null);
                Spielstart SS = new Spielstart(hintergrund);
            }
        });

        afeuer = new JButton(f.geticonbild());
        afeuer.setBounds(50, 180, 200, 200);
        afeuer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aelement = 1;
                weiter(belement);
            }
        });

        awasser = new JButton(w.geticonbild());
        awasser.setBounds(250, 180, 200, 200);
        awasser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aelement = 2;
                weiter(belement);
            }
        });

        aerde = new JButton(e.geticonbild());
        aerde.setBounds(50, 380, 200, 200);
        aerde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aelement = 3;
                weiter(belement);
            }
        });

        aluft = new JButton(l.geticonbild());
        aluft.setBounds(250, 380, 200, 200);
        aluft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aelement = 4;
                weiter(belement);
            }
        });

        bfeuer = new JButton(f.geticonbild());
        bfeuer.setBounds(550, 180, 200, 200);
        bfeuer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                belement = 1;
                weiter(aelement);
            }
        });

        bwasser = new JButton(w.geticonbild());
        bwasser.setBounds(750, 180, 200, 200);
        bwasser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                belement = 2;
                weiter(aelement);
            }
        });

        berde = new JButton(e.geticonbild());
        berde.setBounds(550, 380, 200, 200);
        berde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                belement = 3;
                weiter(aelement);
            }
        });

        bluft = new JButton(l.geticonbild());
        bluft.setBounds(750, 380, 200, 200);
        bluft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                belement = 4;
                weiter(aelement);
            }
        });

        comGeg = new JButton(kibild);
        comGeg.setBounds(700, 80, 100, 100);
        comGeg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ki = true;
            }
        });
    }

    private void weiter(int e) {
        if (e != 0) {
            hintergrund.removeAll();
            hintergrund.setIcon(null);
            Koordinatenmanager KM = new Koordinatenmanager(hintergrund, aelement, belement, ki);
            KM.start();
        }
    }
}
