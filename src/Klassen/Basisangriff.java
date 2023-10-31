package Klassen;

import javax.swing.*;

public class Basisangriff extends Thread implements Runnable {

    private JLabel hintergrund;
    public JLabel basiskleber;

    private boolean ko = true;

    private ImageIcon bab1, bab2, bab3, bab4, bab5, bab6, bab7, bab8;

    private int richtung;
    public int x, y, tempo = 20, schaden;

    public Basisangriff(JLabel h, int r, int px, int py, int ps, ImageIcon ba1, ImageIcon ba2, ImageIcon ba3, ImageIcon ba4, ImageIcon ba5, ImageIcon ba6, ImageIcon ba7, ImageIcon ba8) {
        hintergrund = h;
        richtung = r;
        x = px;
        y = py;
        schaden = ps;
        bab1 = ba1;
        bab2 = ba2;
        bab3 = ba3;
        bab4 = ba4;
        bab5 = ba5;
        bab6 = ba6;
        bab7 = ba7;
        bab8 = ba8;

        basiskleber = new JLabel(bab1);
        basiskleber.setBounds(x, y, 20, 20);

        hintergrund.add(basiskleber);
    }

    @Override
    public void run() {
        while (true) {
            bewegen();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void bewegen() {
        if (richtung == 1) {
            y = y - tempo;
            if (ko == true) {
                basiskleber.setIcon(bab1);
                ko = false;
            }
        }
        if (richtung == 3) {
            x = x + tempo;
            if (ko == true) {
                basiskleber.setIcon(bab3);
                ko = false;
            }
        }
        if (richtung == 5) {
            y = y + tempo;
            if (ko == true) {
                basiskleber.setIcon(bab5);
                ko = false;
            }
        }
        if (richtung == 7) {
            x = x - tempo;
            if (ko == true) {
                basiskleber.setIcon(bab7);
                ko = false;
            }
        }
        if (richtung == 2) {
            y = y - tempo;
            x = x + tempo;
            if (ko == true) {
                basiskleber.setIcon(bab2);
                ko = false;
            }
        }
        if (richtung == 4) {
            y = y + tempo;
            x = x + tempo;
            if (ko == true) {
                basiskleber.setIcon(bab4);
                ko = false;
            }
        }
        if (richtung == 6) {
            y = y + tempo;
            x = x - tempo;
            if (ko == true) {
                basiskleber.setIcon(bab6);
                ko = false;
            }
        }
        if (richtung == 8) {
            y = y - tempo;
            x = x - tempo;
            if (ko == true) {
                basiskleber.setIcon(bab8);
                ko = false;
            }
        }
        basiskleber.setLocation(x, y);
    }
}
