package Klassen;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharakterB extends Thread implements Runnable {

    public JLabel hintergrund, charkleber;
    public JLabel lebenspunkte;

    private ImageIcon charbild1, charbild2, charbild3, charbild4, charbild5, charbild6, charbild7, charbild8;
    private ImageIcon bab1, bab2, bab3, bab4, bab5, bab6, bab7, bab8;

    public Basisangriff ba;

    private KeyListener taszu;

    private boolean hoch = false, rechts = false, runter = false, links = false;
    public boolean basa = false;
    public boolean kib = false;

    public int x = 900, y = 375, hp = 100, tempo = 10, bas = 10, bacd = 0, richtung = 7;

    public CharakterB(JLabel h, ImageIcon chb1, ImageIcon chb2, ImageIcon chb3, ImageIcon chb4, ImageIcon chb5, ImageIcon chb6, ImageIcon chb7, ImageIcon chb8, ImageIcon basab1, ImageIcon basab2, ImageIcon basab3, ImageIcon basab4, ImageIcon basab5, ImageIcon basab6, ImageIcon basab7, ImageIcon basab8) {
        charbild1 = chb1;
        charbild2 = chb2;
        charbild3 = chb3;
        charbild4 = chb4;
        charbild5 = chb5;
        charbild6 = chb6;
        charbild7 = chb7;
        charbild8 = chb8;
        bab1 = basab1;
        bab2 = basab2;
        bab3 = basab3;
        bab4 = basab4;
        bab5 = basab5;
        bab6 = basab6;
        bab7 = basab7;
        bab8 = basab8;
        hintergrund = h;

        charkleber = new JLabel(charbild1);
        charkleber.setBounds(x, y, 50, 50);

        lebenspunkte = new JLabel();
        lebenspunkte.setBounds(850, 50, 150, 50);
        lebenspunkte.setText("Lebelspunkte: " + hp);

        tasorg();

        hintergrund.add(charkleber);
        hintergrund.add(lebenspunkte);
        hintergrund.addKeyListener(taszu);
        hintergrund.requestFocusInWindow();
    }

    @Override
    public void run() {
        while (hp > 0) {
            bewegen();
            if (bacd != 0) {
                bacd--;
            }
            lebenspunkte.setText("Lebenspunkte: " + hp);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void bewegen() {
        if (kib == false) {
            if (y > 0) {
                beweg(hoch, 0, -tempo, 1);
            }
            if (x < 950) {
                beweg(rechts, +tempo, 0, 3);
            }
            if (y < 750) {
                beweg(runter, 0, +tempo, 5);
            }
            if (x > 0) {
                beweg(links, -tempo, 0, 7);
            }
            if (hoch == true) {
                charkleber.setIcon(charbild1);
            }
            if (rechts == true) {
                charkleber.setIcon(charbild3);
            }
            if (runter == true) {
                charkleber.setIcon(charbild5);
            }
            if (links == true) {
                charkleber.setIcon(charbild7);
            }
            if (hoch == true && rechts == true) {
                richtung = 2;
                charkleber.setIcon(charbild2);
            }
            if (runter == true && rechts == true) {
                richtung = 4;
                charkleber.setIcon(charbild4);
            }
            if (runter == true && links == true) {
                richtung = 6;
                charkleber.setIcon(charbild6);
            }
            if (hoch == true && links == true) {
                richtung = 8;
                charkleber.setIcon(charbild8);
            }
        }
    }

    public void beweg(boolean weg, int tempox, int tempoy, int r) {
        if (weg == true) {
            x = x + tempox;
            y = y + tempoy;
            charkleber.setLocation(x, y);
            richtung = r;
        }
    }

    private void tasorg() {
        taszu = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    hoch = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rechts = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    runter = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    links = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_COMMA) {
                    if (bacd == 0 && hp > 0) {
                        ba = new Basisangriff(hintergrund, richtung, x + 15, y + 15, bas, bab1, bab2, bab3, bab4, bab5, bab6, bab7, bab8);
                        ba.start();
                        bacd = 15;
                        basa = true;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    hoch = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rechts = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    runter = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    links = false;
                }
            }
        };
    }
}
