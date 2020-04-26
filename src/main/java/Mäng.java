import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Mäng {

    Käsi mangija;
    Käsi diiler;
    Pakk kaardipakk;
    public Mäng(HBox diileriKaardiVäli,HBox mangijaKaardiVäli){
        diiler = new Käsi(diileriKaardiVäli);
        mangija = new Käsi(mangijaKaardiVäli);
        kaardipakk = new Pakk();
        kaardipakk.sega();
    }

    public void jooksuta(Mängur mangur, Double panus, Label summa, Button v6ta, Button eiV6ta, HBox nupud) throws InterruptedException {
        // Eemalda panus rahakotist
        System.out.println("BBB");
        mangur.muudaRahakott(-panus);
        summa.setText(Double.toString(mangur.getRahakott()));

        //Käsi mangija = new Käsi();
        //Käsi diiler = new Käsi();
        //Pakk kaardipakk = new Pakk();
        kaardipakk.sega();
        kaartideJagamine(diiler, mangija, kaardipakk);
        System.out.println("CCC");
        // Kontroll kas mängijal on bläckjack ja diileril ei saa olla BlackJack
        if (mangija.summa()== 21){
            if(Arrays.asList("Ä","K","E","S","10").indexOf(diiler.getKaardid().get(1).getSuurus()) == -1) {
                System.out.println("Mängija võitis BlackJack-iga, diileril ei olnud BlackJacki!");
                voit(panus, mangur,  2.5);
                return;
            }
        }
        System.out.println("DDDD");
        nupud.setVisible(true);

        v6ta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mangija.lisaKaart(kaardipakk,true);
                // Kontrolli summat
                if (mangija.summa() >= 21){
                    //kuvaLaud(diiler, mängija,  "Mängija voor:");
                    return;
                }
            }
        });

        eiV6ta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nupud.setVisible(false);
                //diileriVoor(mangija,diiler,kaardipakk);
                return;

            }
        });
        //mängijaVoor(mangija,diiler,kaardipakk,v6ta, eiV6ta);
        /*
        if (mangija.summa() > 21){
            System.out.println("Mängija kaotas, kuna läks lõhki " + mangija.summa() + " punktiga!");
            nupud.setVisible(false);
            return;
        }
        nupud.setVisible(false);

        //diileriVoor(mangija,diiler,kaardipakk);
        System.out.println("");
        if (diiler.summa() > 21) {
            System.out.println("Mängija võitis! Diiler läks lõhki " + diiler.summa() + " punktiga!");
            voit(panus, mangur,  2);
        } else if(diiler.summa() > mangija.summa()){
            System.out.println("Diiler võitis! \n\tDiiler: " + diiler.summa() + "\n\tMängija: " + mangija.summa());
        } else if (diiler.summa() < mangija.summa()){
            System.out.println("Mängija võitis! \n\tDiiler:" + diiler.summa() + "\n\tMängija: " + mangija.summa());
            voit(panus, mangur,  2);
        } else {
            System.out.println("Viik!  \n\tDiiler:" + diiler.summa() + "\n\tMängija: " + mangija.summa());
            System.out.println("Said panuse tagasi.");
            mangur.muudaRahakott(panus);
        }*/
    }
/*
    public static void kuvaLaud(Käsi diiler, Käsi mängija, String pealkiri) {
        for (int i = 0; i < 15; i++) {
            System.out.println("");
        }
        System.out.println(pealkiri);
        System.out.println("Diiler: " + diiler);
        System.out.println("Mängija: " + mängija);
    }
 */
    public static void kaartideJagamine(Käsi diiler, Käsi mängija, Pakk pakk) throws InterruptedException {
        // 1 kaart
        mängija.lisaKaart(pakk,true);
        //kuvaLaud(diiler, mängija, "Kaartide jagamine");
        TimeUnit.SECONDS.sleep(1);
        // 2 kaarti
        mängija.lisaKaart(pakk,true);
        //kuvaLaud(diiler, mängija, "Kaartide jagamine");
        TimeUnit.SECONDS.sleep(1);
        // 3 kaarti
        diiler.lisaKaart(pakk,false);
        //kuvaLaud(diiler, mängija, "Kaartide jagamine");
        TimeUnit.SECONDS.sleep(1);
        // 4 kaarti
        diiler.lisaKaart(pakk,true);
    }

    public static void mängijaVoor(Käsi mängija, Käsi diiler,  Pakk pakk, Button v6ta, Button eiV6ta) {
        //char vastus;
        //Scanner scan = new Scanner(System.in);


        v6ta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mängija.lisaKaart(pakk,true);
                // Kontrolli summat
                if (mängija.summa() >= 21){
                    //kuvaLaud(diiler, mängija,  "Mängija voor:");
                    return;
                }
            }
        });

        eiV6ta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                return;

            }
        });

        /*
        do {
            // Saa vastus
            vastus = 's';
            do {
                //kuvaLaud(diiler, mängija,  "Mängija voor:");
                System.out.println("Kas võtad kaardi juurde? (y/n)");
                vastus = scan.next().charAt(0);
                System.out.println(vastus);
            }
            while (vastus != 'y' && vastus != 'n');

            // Lisa kaart
            if (vastus == 'y'){
                mängija.lisaKaart(pakk,true);
            }
            // Kontrolli summat
            if (mängija.summa() >= 21){
                //kuvaLaud(diiler, mängija,  "Mängija voor:");
                return;
            }
        }
        while (vastus == 'y');

        return;
        */
    }

    public static void diileriVoor(Käsi mängija, Käsi diiler,  Pakk pakk) throws InterruptedException {

        // Pööra diileri esimene kaart ümber
        diiler.getKaardid().get(0).avaKaart();

        //kuvaLaud(diiler, mängija,  "Diileri voor:");
        TimeUnit.SECONDS.sleep(2);
        while (diiler.summa() < 17){
            diiler.lisaKaart(pakk,true);
            //kuvaLaud(diiler, mängija,  "Diileri voor:");
            TimeUnit.SECONDS.sleep(2);
        }

        //kuvaLaud(diiler, mängija,  "Diileri voor:");
        return;

    }

    public static void voit(double panus, Mängur mangur, double koefitsent){
        System.out.println("Võitsid: " + panus*(koefitsent-1));
        mangur.muudaRahakott(panus * koefitsent);
    }



}