import javafx.event.EventHandler;
import javafx.scene.Node;
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
        //kaardipakk = new Pakk("blackjack");
        kaardipakk.sega();
    }

    public void alusta(Mängur mangur, Double panus, Label summa, Button v6ta, Button eiV6ta, HBox nupud, Label info,Button uusM2ng,TextField panuseSisestus,HBox rida1, Button lopeta) throws InterruptedException {
        // Eemalda panus rahakotist
        mangur.muudaRahakott(-panus);
        summa.setText(Double.toString(mangur.getRahakott()));

        kaartideJagamine(diiler, mangija, kaardipakk);

        // Kontroll kas mängijal on bläckjack ja diileril ei saa olla BlackJack
        if (mangija.summa() == 21) {
            if (Arrays.asList("Ä", "K", "E", "S", "10").indexOf(diiler.getKaardid().get(1).getSuurus()) == -1) {
                info.setText("Mängija võitis BlackJack-iga, \ndiileril ei olnud BlackJacki!");
                info.setVisible(true);
                uusM2ng.setVisible(true);
                panuseSisestus.setDisable(false);
                panuseSisestus.setStyle("");
                panuseSisestus.clear();
                lopeta.setVisible(true);
                voit(panus, mangur, 2.5, info,summa);
                return;
            }
        }

        // Muuda mängija vooru (võta, ei võta) nupud mängija jaoks nähtavaks
        nupud.setVisible(true);

        v6ta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mangija.lisaKaart(kaardipakk, true);
                // Kontrolli summat
                if (mangija.summa() >= 21) {
                    //kuvaLaud(diiler, mängija,  "Mängija voor:");
                    nupud.setVisible(false);
                    mänguLõpp(mangija, diiler, panus, mangur, info, uusM2ng,panuseSisestus,summa,rida1,lopeta);
                }
            }
        });

        eiV6ta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nupud.setVisible(false);
                try {
                    diileriVoor(mangija, diiler, kaardipakk, panus, mangur, info,uusM2ng,panuseSisestus,summa,rida1,lopeta);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }

            }
        });

    }

    public static void kaartideJagamine(Käsi diiler, Käsi mängija, Pakk pakk) throws InterruptedException {
        // 1 kaart
        mängija.lisaKaart(pakk, true);
        //kuvaLaud(diiler, mängija, "Kaartide jagamine");
        //TimeUnit.SECONDS.sleep(1);
        // 2 kaarti
        mängija.lisaKaart(pakk,true);
        //kuvaLaud(diiler, mängija, "Kaartide jagamine");
        //TimeUnit.SECONDS.sleep(1);
        // 3 kaarti
        diiler.lisaKaart(pakk,false);
        //kuvaLaud(diiler, mängija, "Kaartide jagamine");
        //TimeUnit.SECONDS.sleep(1);
        // 4 kaarti
        diiler.lisaKaart(pakk,true);
    }

    public static void diileriVoor(Käsi mängija, Käsi diiler,  Pakk pakk, Double panusDouble, Mängur mangur,Label info,Button uusM2ng,TextField panuseSisestus, Label summa, HBox rida1,Button lopeta) throws InterruptedException {

        // Pööra diileri esimene kaart ümber
        diiler.getKaardid().get(0).avaKaart();
        Label esimeneKaart = (Label)diiler.getKaardiLaud().getChildren().get(0);
        esimeneKaart.setText(diiler.getKaardid().get(0).toString());

        //kuvaLaud(diiler, mängija,  "Diileri voor:");
        //TimeUnit.SECONDS.sleep(2);
        while (diiler.summa() < 17){
            diiler.lisaKaart(pakk,true);
            //kuvaLaud(diiler, mängija,  "Diileri voor:");
            //TimeUnit.SECONDS.sleep(2);
        }

        mänguLõpp(mängija, diiler, panusDouble, mangur,info, uusM2ng, panuseSisestus, summa, rida1,lopeta);
        //kuvaLaud(diiler, mängija,  "Diileri voor:");
        return;

    }

    public static void mänguLõpp(Käsi mangija, Käsi diiler, Double panusDouble, Mängur mangur,Label info,Button uusM2ng, TextField panuseSisestus, Label summa, HBox rida1,Button lopeta) {
        info.setVisible(true);
        uusM2ng.setVisible(true);
        panuseSisestus.setDisable(false);
        panuseSisestus.setStyle("");
        panuseSisestus.clear();
        lopeta.setVisible(true);
        for(Kaart kaart: diiler.getKaardid())
            kaart.isPööratud();
        FailiLugemine lugemine = new FailiLugemine();
        if ( mangija.summa() > 21){
            info.setText("Diiler võitis! \n\tMängija läks lõhki " + mangija.summa() + " punktiga!");
            try {
                lugemine.uuendaAndmeid(mangur.getRahakott(),mangur.getNimi());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (diiler.summa() > 21) {
            info.setText("Mängija võitis! \n\tDiiler läks lõhki " + diiler.summa() + " punktiga!");
            voit(panusDouble, mangur,  2, info,summa);
        } else if(diiler.summa() > mangija.summa()){
            info.setText("Diiler võitis! \n\tDiiler: " + diiler.summa() + "\n\tMängija: " + mangija.summa());
            try {
                lugemine.uuendaAndmeid(mangur.getRahakott(),mangur.getNimi());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (diiler.summa() < mangija.summa()){
            info.setText("Mängija võitis! \n\tDiiler:" + diiler.summa() + "\n\tMängija: " + mangija.summa());
            voit(panusDouble, mangur,  2, info,summa);
        } else {
            info.setText("Viik!  \n\tDiiler:" + diiler.summa() + "\n\tMängija: " + mangija.summa());
            info.setText(info.getText() + "\nSaid panuse tagasi.");
            mangur.muudaRahakott(panusDouble);
            summa.setText(Double.toString(mangur.getRahakott()));
        }

        // Kontroll, et kui raha otsas siis lõetab mängu ära.
        if(mangur.getRahakott() == 0){
            info.setText(info.getText() + "\nRaha sai otsa ja sellega sai \npraegusele mängijale mäng läbi!");
            info.setText(info.getText() + "\n\nLõpeta mäng või loo/otsi uus kasutaja!");

            // Kasutaja nime sektsioon aktiveerimine
            for (Node element: rida1.getChildren()){
                element.setDisable(false);
            }

            uusM2ng.setDisable(true);
            panuseSisestus.setDisable(true);

            //kasutajanimiSilt.setDisable(true);
            //leiaKasutaja.setDisable(true);
            //kasutajanimiSisestus.setDisable(true);

            //leiaKasutaja.setDisable(false)
            /*
            uusM2ng.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    uusM2ng.setDisable(true);
                }
            });

             */
        }

    }

    public static void voit(double panus, Mängur mangur, double koefitsent, Label info, Label summa){
        info.setText(info.getText() + "\nVõitsid: " + panus*(koefitsent-1));
        mangur.muudaRahakott(panus * koefitsent);
        summa.setText(Double.toString(mangur.getRahakott()));
        FailiLugemine lugemine = new FailiLugemine();
        try {
            lugemine.uuendaAndmeid(mangur.getRahakott(),mangur.getNimi());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}