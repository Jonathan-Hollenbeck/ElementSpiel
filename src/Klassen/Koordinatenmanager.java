package Klassen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Koordinatenmanager extends Thread implements Runnable {

    private JLabel hintergrund, gewinner;

    private int aelm, belm, bba, aba, vlcda, vlcdb, kibox = 20;

    private boolean komako = true, ki = false;

    private JButton zurueck;

    private CharakterA cha;
    private CharakterB chb;

    private Basisangriff[] baarraya = new Basisangriff[0];
    private Basisangriff[] baarrayb = new Basisangriff[0];

    private ImageIcon hintergrundbild = new ImageIcon(getClass().getResource("Bilder/kampfhintergrund.png"));
    private ImageIcon zurueckbild = new ImageIcon(getClass().getResource("Bilder/zurueckknopf.png"));
    private ImageIcon charbilda1, charbilda2, charbilda3, charbilda4, charbilda5, charbilda6, charbilda7, charbilda8;
    private ImageIcon baba1, baba2, baba3, baba4, baba5, baba6, baba7, baba8;
    private ImageIcon charbildb1, charbildb2, charbildb3, charbildb4, charbildb5, charbildb6, charbildb7, charbildb8;
    private ImageIcon babb1, babb2, babb3, babb4, babb5, babb6, babb7, babb8;

    public Koordinatenmanager(JLabel h, int ae, int be, boolean kip) {
        hintergrund = h;
        hintergrund.setIcon(hintergrundbild);

        aelm = ae;
        belm = be;

        ki = kip;

        gewinner = new JLabel();
        gewinner.setBounds(400, 100, 400, 400);

        bauecharaktera();
        bauecharakterb();
        baueknopf();

        hintergrund.add(gewinner);
        hintergrund.add(zurueck);
    }

    @Override
    public void run() {
        while (komako == true) {
            basisangriffa();
            basisangriffb();
            agetroffen();
            bgetroffen();
            Tod();
            CD();
            KI();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void Tod() {
        if (cha.hp <= 0) {
            cha.lebenspunkte.setText("Lebenspunkte: 0");
            gewinner.setText("<html><body>Spieler B hat gewonnen<br><br>Statistik:<br>Spieler B Basisangriffe: " + bba + "<br>Spieler A Basisangriffe: " + aba + "</body></html>");
        }
        if (chb.hp <= 0) {
            chb.lebenspunkte.setText("Lebenspunkte: 0");
            gewinner.setText("<html><body>Spieler A hat gewonnen<br><br>Statistik:<br>Spieler A Basisangriffe: " + aba + "<br>Spieler B Basisangriffe: " + bba + "</body></html>");
        }

    }

    private void CD() {
        if (vlcda > 0) {
            vlcda--;
        }
        if (vlcda == 100) {
            chb.tempo = chb.tempo * 2;
        }
        if (vlcdb > 0) {
            vlcdb--;
        }
        if (vlcdb == 100) {
            cha.tempo = cha.tempo * 2;
        }
    }

    private void KIBA() {
        if (chb.bacd == 0 && chb.hp > 0 && cha.hp > 0) {
            chb.ba = new Basisangriff(hintergrund, chb.richtung, chb.x + 15, chb.y + 15, chb.bas, babb1, babb2, babb3, babb4, babb5, babb6, babb7, babb8);
            chb.ba.start();
            chb.bacd = 15;
            chb.basa = true;
        }
    }

    private void KIBewegung() {
        if (cha.x > chb.x - kibox && cha.x < chb.x + kibox || cha.y > chb.y - kibox && cha.y < chb.y + kibox) {
            if (cha.y < chb.y && cha.x > chb.x - kibox && cha.x < chb.x + kibox) {
                chb.beweg(true, 0, -chb.tempo / 2, 1);
                chb.charkleber.setIcon(charbildb1);
            }
            if (cha.x > chb.x && cha.y > chb.y - kibox && cha.y < chb.y + kibox) {
                chb.beweg(true, +chb.tempo / 2, 0, 3);
                chb.charkleber.setIcon(charbildb3);
            }
            if (cha.y > chb.y && cha.x > chb.x - kibox && cha.x < chb.x + kibox) {
                chb.beweg(true, 0, +chb.tempo / 2, 5);
                chb.charkleber.setIcon(charbildb5);
            }
            if (cha.x < chb.x && cha.y > chb.y - kibox && cha.y < chb.y + kibox) {
                chb.beweg(true, -chb.tempo / 2, 0, 7);
                chb.charkleber.setIcon(charbildb7);
            }
        } else {
            if (chb.x < cha.x && chb.y > cha.y) {
                chb.beweg(true, +chb.tempo / 2, -chb.tempo / 2, 2);
                chb.charkleber.setIcon(charbildb2);
            }
            if (chb.x < cha.x && chb.y < cha.y) {
                chb.beweg(true, +chb.tempo / 2, +chb.tempo / 2, 4);
                chb.charkleber.setIcon(charbildb4);
            }
            if (chb.x > cha.x && chb.y < cha.y) {
                chb.beweg(true, -chb.tempo / 2, +chb.tempo / 2, 6);
                chb.charkleber.setIcon(charbildb6);
            }
            if (chb.x > cha.x && chb.y > cha.y) {
                chb.beweg(true, -chb.tempo / 2, -chb.tempo / 2, 8);
                chb.charkleber.setIcon(charbildb8);
            }
        }
    }

    private void KI() {
        if (ki == true && cha.hp > 0 && chb.hp > 0) {
            KIBewegung();
            KIBA();
        }
    }

    private void bauecharaktera() {
        if (aelm == 1) {
            Feuer f = new Feuer();
            charbilda1 = f.getcharbild1();
            charbilda2 = f.getcharbild2();
            charbilda3 = f.getcharbild3();
            charbilda4 = f.getcharbild4();
            charbilda5 = f.getcharbild5();
            charbilda6 = f.getcharbild6();
            charbilda7 = f.getcharbild7();
            charbilda8 = f.getcharbild8();
            baba1 = f.getbab1();
            baba2 = f.getbab2();
            baba3 = f.getbab3();
            baba4 = f.getbab4();
            baba5 = f.getbab5();
            baba6 = f.getbab6();
            baba7 = f.getbab7();
            baba8 = f.getbab8();
            cha = new CharakterA(hintergrund, charbilda1, charbilda2, charbilda3, charbilda4, charbilda5, charbilda6, charbilda7, charbilda8, baba1, baba2, baba3, baba4, baba5, baba6, baba7, baba8);
            cha.start();
            cha.bas = 12;
        }
        if (aelm == 2) {
            Wasser w = new Wasser();
            charbilda1 = w.getcharbild1();
            charbilda2 = w.getcharbild2();
            charbilda3 = w.getcharbild3();
            charbilda4 = w.getcharbild4();
            charbilda5 = w.getcharbild5();
            charbilda6 = w.getcharbild6();
            charbilda7 = w.getcharbild7();
            charbilda8 = w.getcharbild8();
            baba1 = w.getbab1();
            baba1 = w.getbab1();
            baba2 = w.getbab2();
            baba3 = w.getbab3();
            baba4 = w.getbab4();
            baba5 = w.getbab5();
            baba6 = w.getbab6();
            baba7 = w.getbab7();
            baba8 = w.getbab8();
            cha = new CharakterA(hintergrund, charbilda1, charbilda2, charbilda3, charbilda4, charbilda5, charbilda6, charbilda7, charbilda8, baba1, baba2, baba3, baba4, baba5, baba6, baba7, baba8);
            cha.start();
        }
        if (aelm == 3) {
            Erde e = new Erde();
            charbilda1 = e.getcharbild1();
            charbilda2 = e.getcharbild2();
            charbilda3 = e.getcharbild3();
            charbilda4 = e.getcharbild4();
            charbilda5 = e.getcharbild5();
            charbilda6 = e.getcharbild6();
            charbilda7 = e.getcharbild7();
            charbilda8 = e.getcharbild8();
            baba1 = e.getbab1();
            baba1 = e.getbab1();
            baba2 = e.getbab2();
            baba3 = e.getbab3();
            baba4 = e.getbab4();
            baba5 = e.getbab5();
            baba6 = e.getbab6();
            baba7 = e.getbab7();
            baba8 = e.getbab8();
            cha = new CharakterA(hintergrund, charbilda1, charbilda2, charbilda3, charbilda4, charbilda5, charbilda6, charbilda7, charbilda8, baba1, baba2, baba3, baba4, baba5, baba6, baba7, baba8);
            cha.start();
            cha.hp = 120;
        }
        if (aelm == 4) {
            Luft l = new Luft();
            charbilda1 = l.getcharbild1();
            charbilda2 = l.getcharbild2();
            charbilda3 = l.getcharbild3();
            charbilda4 = l.getcharbild4();
            charbilda5 = l.getcharbild5();
            charbilda6 = l.getcharbild6();
            charbilda7 = l.getcharbild7();
            charbilda8 = l.getcharbild8();
            baba1 = l.getbab1();
            baba1 = l.getbab1();
            baba2 = l.getbab2();
            baba3 = l.getbab3();
            baba4 = l.getbab4();
            baba5 = l.getbab5();
            baba6 = l.getbab6();
            baba7 = l.getbab7();
            baba8 = l.getbab8();
            cha = new CharakterA(hintergrund, charbilda1, charbilda2, charbilda3, charbilda4, charbilda5, charbilda6, charbilda7, charbilda8, baba1, baba2, baba3, baba4, baba5, baba6, baba7, baba8);
            cha.start();
            cha.tempo = 14;
        }
    }

    private void bauecharakterb() {
        if (belm == 1) {
            Feuer f = new Feuer();
            charbildb1 = f.getcharbild1();
            charbildb2 = f.getcharbild2();
            charbildb3 = f.getcharbild3();
            charbildb4 = f.getcharbild4();
            charbildb5 = f.getcharbild5();
            charbildb6 = f.getcharbild6();
            charbildb7 = f.getcharbild7();
            charbildb8 = f.getcharbild8();
            babb1 = f.getbab1();
            babb2 = f.getbab2();
            babb3 = f.getbab3();
            babb4 = f.getbab4();
            babb5 = f.getbab5();
            babb6 = f.getbab6();
            babb7 = f.getbab7();
            babb8 = f.getbab8();
            chb = new CharakterB(hintergrund, charbildb1, charbildb2, charbildb3, charbildb4, charbildb5, charbildb6, charbildb7, charbildb8, babb1, babb2, babb3, babb4, babb5, babb6, babb7, babb8);
            chb.start();
            chb.bas = 12;
        }
        if (belm == 2) {
            Wasser w = new Wasser();
            charbildb1 = w.getcharbild1();
            charbildb2 = w.getcharbild2();
            charbildb3 = w.getcharbild3();
            charbildb4 = w.getcharbild4();
            charbildb5 = w.getcharbild5();
            charbildb6 = w.getcharbild6();
            charbildb7 = w.getcharbild7();
            charbildb8 = w.getcharbild8();
            babb1 = w.getbab1();
            babb2 = w.getbab2();
            babb3 = w.getbab3();
            babb4 = w.getbab4();
            babb5 = w.getbab5();
            babb6 = w.getbab6();
            babb7 = w.getbab7();
            babb8 = w.getbab8();
            chb = new CharakterB(hintergrund, charbildb1, charbildb2, charbildb3, charbildb4, charbildb5, charbildb6, charbildb7, charbildb8, babb1, babb2, babb3, babb4, babb5, babb6, babb7, babb8);
            chb.start();
        }
        if (belm == 3) {
            Erde e = new Erde();
            charbildb1 = e.getcharbild1();
            charbildb2 = e.getcharbild2();
            charbildb3 = e.getcharbild3();
            charbildb4 = e.getcharbild4();
            charbildb5 = e.getcharbild5();
            charbildb6 = e.getcharbild6();
            charbildb7 = e.getcharbild7();
            charbildb8 = e.getcharbild8();
            babb1 = e.getbab1();
            babb2 = e.getbab2();
            babb3 = e.getbab3();
            babb4 = e.getbab4();
            babb5 = e.getbab5();
            babb6 = e.getbab6();
            babb7 = e.getbab7();
            babb8 = e.getbab8();
            chb = new CharakterB(hintergrund, charbildb1, charbildb2, charbildb3, charbildb4, charbildb5, charbildb6, charbildb7, charbildb8, babb1, babb2, babb3, babb4, babb5, babb6, babb7, babb8);
            chb.start();
            chb.hp = 120;
        }
        if (belm == 4) {
            Luft l = new Luft();
            charbildb1 = l.getcharbild1();
            charbildb2 = l.getcharbild2();
            charbildb3 = l.getcharbild3();
            charbildb4 = l.getcharbild4();
            charbildb5 = l.getcharbild5();
            charbildb6 = l.getcharbild6();
            charbildb7 = l.getcharbild7();
            charbildb8 = l.getcharbild8();
            babb1 = l.getbab1();
            babb2 = l.getbab2();
            babb3 = l.getbab3();
            babb4 = l.getbab4();
            babb5 = l.getbab5();
            babb6 = l.getbab6();
            babb7 = l.getbab7();
            babb8 = l.getbab8();
            chb = new CharakterB(hintergrund, charbildb1, charbildb2, charbildb3, charbildb4, charbildb5, charbildb6, charbildb7, charbildb8, babb1, babb2, babb3, babb4, babb5, babb6, babb7, babb8);
            chb.start();
            chb.tempo = 14;
        }
        if (ki == true) {
            chb.kib = true;
        }
    }

    private void baueknopf() {
        zurueck = new JButton(zurueckbild);
        zurueck.setBounds(0, 0, 100, 50);
        zurueck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hintergrund.removeAll();
                hintergrund.setIcon(null);
                komako = false;
                cha.hp = 0;
                chb.hp = 0;
                Spielstart SS = new Spielstart(hintergrund);
            }
        });
    }

    private void basisangriffa() {
        if (cha.basa == true) {
            cha.basa = false;
            Basisangriff[] hba = new Basisangriff[baarraya.length + 1];
            for (int i = 0; i <= baarraya.length - 1; i++) {
                hba[i] = baarraya[i];
            }
            baarraya = new Basisangriff[hba.length];
            for (int j = 0; j <= hba.length - 1; j++) {
                baarraya[j] = hba[j];
            }
            baarraya[baarraya.length - 1] = cha.ba;
            aba++;
        }
    }

    private void basisangriffb() {
        if (chb.basa == true) {
            chb.basa = false;
            Basisangriff[] hba = new Basisangriff[baarrayb.length + 1];
            for (int i = 0; i <= baarrayb.length - 1; i++) {
                hba[i] = baarrayb[i];
            }
            baarrayb = new Basisangriff[hba.length];
            for (int j = 0; j <= hba.length - 1; j++) {
                baarrayb[j] = hba[j];
            }
            baarrayb[baarrayb.length - 1] = chb.ba;
            bba++;
        }
    }

    private void agetroffen() {
        for (int t = 0; t < baarrayb.length; t++) {
            if (baarrayb[t].x + 10 < cha.x + 50 && baarrayb[t].x + 10 > cha.x && baarrayb[t].y + 10 < cha.y + 50 && baarrayb[t].y + 10 > cha.y) {
                cha.hp = cha.hp - baarrayb[t].schaden;
                baarrayb[t].basiskleber.setIcon(null);
                baarrayb[t] = baarrayb[baarrayb.length - 1];
                Basisangriff[] hba = new Basisangriff[baarrayb.length - 1];
                for (int i = 0; i <= hba.length - 1; i++) {
                    hba[i] = baarrayb[i];
                }
                baarrayb = new Basisangriff[hba.length];
                for (int j = 0; j <= hba.length - 1; j++) {
                    baarrayb[j] = hba[j];
                }
                if (belm == 2 && vlcdb == 0) {
                    cha.tempo = cha.tempo / 2;
                    vlcdb = 200;
                }
            }
        }
    }

    private void bgetroffen() {
        for (int t = 0; t < baarraya.length; t++) {
            if (baarraya[t].x + 10 < chb.x + 50 && baarraya[t].x + 10 > chb.x && baarraya[t].y + 10 < chb.y + 50 && baarraya[t].y + 10 > chb.y) {
                chb.hp = chb.hp - baarraya[t].schaden;
                baarraya[t].basiskleber.setIcon(null);
                baarraya[t] = baarraya[baarraya.length - 1];
                Basisangriff[] hba = new Basisangriff[baarraya.length - 1];
                for (int i = 0; i <= hba.length - 1; i++) {
                    hba[i] = baarraya[i];
                }
                baarraya = new Basisangriff[hba.length];
                for (int j = 0; j <= hba.length - 1; j++) {
                    baarraya[j] = hba[j];
                }
                if (aelm == 2 && vlcda == 0) {
                    chb.tempo = chb.tempo / 2;
                    vlcda = 200;
                }
            }
        }
    }
}
