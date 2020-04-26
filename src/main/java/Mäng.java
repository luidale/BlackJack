import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Map;
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

    public void alusta(Mängur mangur, Double panus, Label summa, Button v6ta, Button eiV6ta, HBox nupud, Label info,Button uusM2ng,TextField panuseSisestus) throws InterruptedException {
        // Eemalda panus rahakotist
        System.out.println("BBB");
        mangur.muudaRahakott(-panus);
        summa.setText(Double.toString(mangur.getRahakott()));

        kaardipakk.sega();
        kaartideJagamine(diiler, mangija, kaardipakk);
        System.out.println("CCC");
        // Kontroll kas mängijal on bläckjack ja diileril ei saa olla BlackJack
        if (mangija.summa() == 21) {
            if (Arrays.asList("Ä", "K", "E", "S", "10").indexOf(diiler.getKaardid().get(1).getSuurus()) == -1) {
                info.setText("Mängija võitis BlackJack-iga, diileril ei olnud BlackJacki!");
                voit(panus, mangur, 2.5, info,summa);
                return;
            }
        }
        System.out.println("DDDD");
        nupud.setVisible(true);

        v6ta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mangija.lisaKaart(kaardipakk, true);
                // Kontrolli summat
                if (mangija.summa() >= 21) {
                    //kuvaLaud(diiler, mängija,  "Mängija voor:");
                    nupud.setVisible(false);
                    mänguLõpp(mangija, diiler, panus, mangur, info, uusM2ng,panuseSisestus,summa);
                }
            }
        });

        eiV6ta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nupud.setVisible(false);
                try {
                    diileriVoor(mangija, diiler, kaardipakk, panus, mangur, info,uusM2ng,panuseSisestus,summa);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

            }
        });

    }

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

    public static void diileriVoor(Käsi mängija, Käsi diiler,  Pakk pakk, Double panus, Mängur mangur,Label info,Button uusM2ng,TextField panuseSisestus, Label summa) throws InterruptedException {

        // Pööra diileri esimene kaart ümber
        diiler.getKaardid().get(0).avaKaart();
        Label esimeneKaart = (Label)diiler.getKaardiLaud().getChildren().get(0);
        esimeneKaart.setText(diiler.getKaardid().get(0).toString());

        //kuvaLaud(diiler, mängija,  "Diileri voor:");
        TimeUnit.SECONDS.sleep(2);
        while (diiler.summa() < 17){
            diiler.lisaKaart(pakk,true);
            //kuvaLaud(diiler, mängija,  "Diileri voor:");
            TimeUnit.SECONDS.sleep(2);
        }

        mänguLõpp(mängija, diiler, panus, mangur,info, uusM2ng, panuseSisestus, summa);
        //kuvaLaud(diiler, mängija,  "Diileri voor:");
        return;

    }

    public static void mänguLõpp(Käsi mangija, Käsi diiler, Double panus, Mängur mangur,Label info,Button uusM2ng, TextField panuseSisestus, Label summa) {
        info.setVisible(true);
        uusM2ng.setVisible(true);
        panuseSisestus.setDisable(false);
        panuseSisestus.setStyle("");
        panuseSisestus.clear();
        if ( mangija.summa() > 21){
            info.setText("Diiler võitis! \n\tMängija läks lõhki " + mangija.summa() + " punktiga!");
        } else if (diiler.summa() > 21) {
            info.setText("Mängija võitis! \n\tDiiler läks lõhki " + diiler.summa() + " punktiga!");
            voit(panus, mangur,  2, info,summa);
        } else if(diiler.summa() > mangija.summa()){
            info.setText("Diiler võitis! \n\tDiiler: " + diiler.summa() + "\n\tMängija: " + mangija.summa());
        } else if (diiler.summa() < mangija.summa()){
            info.setText("Mängija võitis! \n\tDiiler:" + diiler.summa() + "\n\tMängija: " + mangija.summa());
            voit(panus, mangur,  2, info,summa);
        } else {
            info.setText("Viik!  \n\tDiiler:" + diiler.summa() + "\n\tMängija: " + mangija.summa());
            info.setText(info.getText() + "\nSaid panuse tagasi.");
            mangur.muudaRahakott(panus);
            summa.setText(Double.toString(mangur.getRahakott()));
        }

        // Kontroll, et kui raha otsas siis lõetab mängu ära.

        
    }

    public static void voit(double panus, Mängur mangur, double koefitsent, Label info, Label summa){
        info.setText(info.getText() + "\nVõitsid: " + panus*(koefitsent-1));
        mangur.muudaRahakott(panus * koefitsent);
        summa.setText(Double.toString(mangur.getRahakott()));
    }



}