import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;


public class BlackJack extends Application {

    @Override
    public void start(Stage peaLava) throws Exception {

        // Label styles
        String labelStyle1 ="-fx-font-weight: bold;"+
                "-fx-background-color: #FCBCAF;"+
                "-fx-font-size: 13px";

        final Mängur[] mängur = new Mängur[1];
        //mängur[0] = new Mängur(0,"");
        final Mäng[] mäng = new Mäng[1];

        // Juur
        FlowPane juur = new FlowPane();
        juur.setOrientation(Orientation.VERTICAL);
        juur.setAlignment(Pos.TOP_CENTER);
        juur.setVgap(20);

        // Taust
        BackgroundImage myBI= new BackgroundImage(new Image("Untitled.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        juur.setBackground(new Background(myBI));

        // Logo
        ImageView iv = new ImageView("logo.png");
        juur.getChildren().add(iv);

        // Esimene rida
        HBox rida1 = new HBox();
        rida1.setSpacing(10);
        Label kasutajanimiSilt = new Label(" Kasutaja nimi: ");
        kasutajanimiSilt.setStyle(labelStyle1);
        TextField kasutajanimiSisestus = new TextField();
        Button leiaKasutaja = new Button("Leia/loo kasutaja");
        Button reeglid = new Button("Reeglid");
        rida1.getChildren().addAll(kasutajanimiSilt, kasutajanimiSisestus, leiaKasutaja, reeglid);
        juur.getChildren().add(rida1);


        Scene stseen1 = new Scene(juur, 600, 660, Color.RED);  // luuakse stseen
        peaLava.setTitle("Black Jack");  // lava tiitelribale pannakse tekst
        peaLava.setScene(stseen1);  // lavale lisatakse stseen
        peaLava.show();


        // Teine rida
        VBox plokk2 = new VBox();
        plokk2.setSpacing(10);
        plokk2.setVisible(false); // Peidab alguses
        VBox.setMargin(plokk2, new Insets(50, 0, 50, 50));

        HBox plokk2rida1 = new HBox();
        plokk2rida1.setSpacing(10);
        Label kasutajanimiTekst = new Label(" Kasutaja nimi: ");
        Label kasutajanimi = new Label();
        kasutajanimiTekst.setStyle(labelStyle1);
        //kasutajanimi.setStyle(labelStyle1);
        plokk2rida1.getChildren().addAll(kasutajanimiTekst,kasutajanimi);

        HBox plokk2rida2 = new HBox();
        plokk2rida2.setSpacing(10);
        Label summaTekst = new Label(" Raha: ");
        Label summa = new Label();
        //summa.textProperty().bind(new SimpleDoubleProperty(mängur[0].getRahakott()).asString());
        summaTekst.setStyle(labelStyle1);
        //summa.setStyle(labelStyle1);
        plokk2rida2.getChildren().addAll(summaTekst,summa);

        plokk2.getChildren().addAll(plokk2rida1,plokk2rida2);
        juur.getChildren().add(plokk2);
        //kasutajanimiSisestus.clear();
        //leiaKasutaja.setOnMouseClicked(e -> leiaKasutaja.setDisable(false));


        // Kolmas rida

        HBox rida3 = new HBox();
        rida3.setSpacing(10);
        rida3.setVisible(false); // Peidab alguses
        Label panus = new Label(" Panus: ");
        panus.setStyle(labelStyle1);

        TextField panuseSisestus = new TextField();

        Button uusM2ng = new Button("Alusta uut mängu");
        rida3.getChildren().addAll(panus,panuseSisestus,uusM2ng);
        juur.getChildren().add(rida3);

        // Neljas plokk

        FlowPane m2nguplokk = new FlowPane();
        m2nguplokk.setOrientation(Orientation.VERTICAL);
        m2nguplokk.setAlignment(Pos.TOP_CENTER);
        m2nguplokk.setVgap(20);
        m2nguplokk.setVisible(false);

        Label diiler = new Label(" Diiler: ");
        diiler.setStyle(labelStyle1);
        HBox diileriKaardid = new HBox();
        Label mangija = new Label(" Mängija: ");
        mangija.setStyle(labelStyle1);
        HBox mangijaKaardid = new HBox();
        HBox nupud = new HBox();
        nupud.setVisible(false);

        Button v6ta = new Button("Võta juurde");
        Button eiV6ta = new Button("Ei võta juurde");
        nupud.getChildren().addAll(v6ta, eiV6ta);

        m2nguplokk.getChildren().addAll(diiler, diileriKaardid, mangija,mangijaKaardid,nupud);
        juur.getChildren().add(m2nguplokk);

        leiaKasutaja.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(kasutajanimiSisestus.getText().isEmpty()){
                    String tekst = "Pead sisestama kasutajanime!";
                    lisaAken(tekst,peaLava,stseen1,200,100);
                }
                else if(!kasutajanimiSisestus.getText().isEmpty()){
                    // SIIN ON VAJA VÄLJA KUTSUDA KLASS MIS LOEB FAILIST KASUTAJA ANDMED (SUMMA) VÕI KUI KASUTAJAT
                    // EI OLE SIIS TEKITAB UUE.

                    mängur[0] = new Mängur(20,kasutajanimiSilt.getText());
                    //mängur[0].setRahakott(20);
                    kasutajanimi.setText(" " + kasutajanimiSisestus.getText() + " ");
                    kasutajanimiSisestus.clear();
                    //summa.textProperty().bind(new SimpleDoubleProperty(mängur[0].getRahakott()).asString());
                    summa.setText(Double.toString(mängur[0].getRahakott()));

                    //leiaKasutaja.setDisable(true);
                    leiaKasutaja.setOnMouseClicked(e -> leiaKasutaja.setDisable(false));

                    // Näita sektsioone
                    plokk2.setVisible(true);
                    rida3.setVisible(true);
                }
            }
        });

        reeglid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String tekst = "EESMÄRK: Saada diileriga võrdne\nvõi rohkem arv punkte.\n" +
                        "Kaartide väärtused:\n" +
                        "\tÄ - 1 või 11\n" +
                        "\tPildid - 10\n" +
                        "\tNumbeid - number\n";
                lisaAken(tekst,peaLava,stseen1,200,200);
            }
        });

        uusM2ng.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if(panuseSisestus.getText().isEmpty()){
                        String tekst = "Pead sisestama panuse!";
                        lisaAken(tekst,peaLava,stseen1,200,100);
                    }
                    else if(Double.parseDouble(panuseSisestus.getText()) > mängur[0].getRahakott()){
                        String tekst = "Eelnev panus oli \nsuurem kui sul on raha. \nVali uus panus!";
                        panuseSisestus.clear();
                        lisaAken(tekst,peaLava,stseen1,200,100);
                    }
                    else if(Double.parseDouble(panuseSisestus.getText()) < 0.0) {
                        String tekst = "Panus ei saa olla \nväiksem kui null. \nVali uus panus!";
                        panuseSisestus.clear();
                        lisaAken(tekst,peaLava,stseen1,200,100);
                    }
                    else if(!panuseSisestus.getText().isEmpty()){

                        // SIIN ON VAJA VÄLJA KUTSUDA KLASS MIS ALUSTAB MÄNGU
                        mäng[0] = new Mäng();
                        mäng[0].jooksuta(mängur[0],Double.parseDouble(panuseSisestus.getText()),summa);
                        panuseSisestus.setDisable(true);
                        panuseSisestus.setStyle("-fx-opacity: 1;");
                        //peida nupp ise
                        Button source = (Button) mouseEvent.getSource();
                        source.setVisible(false);

                        // Näita sektsioone
                        m2nguplokk.setVisible(true);

                    }

                }
                catch (NumberFormatException | InterruptedException e) {
                    String tekst = "Sisestud panus ei olnud number. \nSisesta panus numbrina!";
                    panuseSisestus.clear();
                    lisaAken(tekst,peaLava,stseen1,200,100);
                }
             }
        });


    }

    public void lisaAken(String tekst, Stage peaLava, Scene stseen1, int laius, int kõrgus) {
        FlowPane seletus = new FlowPane();
        seletus.setAlignment(Pos.CENTER);
        seletus.setOrientation(Orientation.VERTICAL);

        Label seletus1 = new Label();
        seletus1.setText(tekst);

        Button tagasi = new Button("Tagasi");
        seletus.getChildren().addAll(seletus1, tagasi);

        Scene error = new Scene(seletus, laius, kõrgus, Color.SNOW);
        peaLava.setScene(error);
        peaLava.show();

        tagasi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                peaLava.setScene(stseen1);
                peaLava.show();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
