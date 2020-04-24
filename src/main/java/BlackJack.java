import javafx.application.Application;
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
import javafx.stage.Stage;

import java.io.BufferedWriter;


public class BlackJack extends Application {

    @Override
    public void start(Stage peaLava) throws Exception {
        FlowPane juur = new FlowPane(); // luuakse juur
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

        Label kasutajanimiSilt = new Label("Kasutaja nimi:");
        kasutajanimiSilt.setStyle("-fx-font-weight: bold");

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
        VBox rida2 = new VBox();
        rida2.setSpacing(10);
        rida2.setVisible(false); // Peidab alguses
        VBox.setMargin(rida2, new Insets(50, 0, 50, 50));
        Label kasutajanimi = new Label("Kasutaja nimi: ");
        Label summa = new Label("Raha: ");
        kasutajanimi.setStyle("-fx-font-weight: bold");
        summa.setStyle("-fx-font-weight: bold");
        rida2.getChildren().addAll(kasutajanimi,summa);
        juur.getChildren().add(rida2);
        //kasutajanimiSisestus.clear();
        //leiaKasutaja.setOnMouseClicked(e -> leiaKasutaja.setDisable(false));


        // Kolmas rida

        HBox rida3 = new HBox();
        rida3.setSpacing(10);
        rida3.setVisible(false); // Peidab alguses
        Label panus = new Label("Panus:");
        panus.setStyle("-fx-font-weight: bold");

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

        Label diiler = new Label("Diiler:");
        HBox diileriKaardid = new HBox();
        Label mangija = new Label("Mängija:");
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
                    FlowPane seletus = new FlowPane();
                    Label seletus1 = new Label("Pead sisestama kasutajanimi!");
                    Button tagasi = new Button("Tagasi");
                    seletus.getChildren().addAll(seletus1,tagasi);
                    Scene error = new Scene(seletus,200,200,Color.SNOW);
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
                else if(!kasutajanimiSisestus.getText().isEmpty()){

                    // SIIN ON VAJA VÄLJA KUTSUDA KLASS MIS LOEB FAILIST KASUTAJA ANDMED (SUMMA) VÕI KUI KASUTAJAT
                    // EI OLE SIIS TEKITAB UUE.

                    kasutajanimi.setText("Kasutaja nimi: " + kasutajanimiSisestus.getText());
                    kasutajanimiSisestus.clear();
                    summa.setText("Raha: 20");

                    //leiaKasutaja.setDisable(true);
                    leiaKasutaja.setOnMouseClicked(e -> leiaKasutaja.setDisable(false));

                    // Näita sektsioone
                    rida2.setVisible(true);
                    rida3.setVisible(true);
                }
            }
        });

        reeglid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FlowPane reeglid = new FlowPane();
                Label r = new Label( "EESMÄRK: Saada diileriga võrdne\nvõi rohkem arv punkte.\n" +
                        "Kaartide väärtused:\n" +
                        "\tÄ - 1 või 11\n" +
                        "\tPildid - 10\n" +
                        "\tNumbeid - number\n");
                Button tagasi = new Button("Tagasi");
                reeglid.getChildren().addAll(r,tagasi);
                peaLava.setScene(new Scene(reeglid,200,200,Color.SNOW));
                peaLava.show();

                tagasi.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        peaLava.setScene(stseen1);
                        peaLava.show();
                    }
                });
            }
        });

        uusM2ng.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(panuseSisestus.getText().isEmpty()){
                    FlowPane seletus = new FlowPane();
                    Label seletus1 = new Label("Pead sisestama panuse!");
                    Button tagasi = new Button("Tagasi");
                    seletus.getChildren().addAll(seletus1,tagasi);
                    Scene error = new Scene(seletus,200,200,Color.SNOW);
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
                else if(!panuseSisestus.getText().isEmpty()){

                    // SIIN ON VAJA VÄLJA KUTSUDA KLASS MIS ALUSTAB MÄNGU

                    panuseSisestus.setDisable(true);
                    panuseSisestus.setStyle("-fx-opacity: 1;");
                    //peida nupp ise
                    Button source = (Button) mouseEvent.getSource();
                    source.setVisible(false);

                    // Näita sektsioone
                    m2nguplokk.setVisible(true);

                }
            }
        });


    }

    public static void main(String[] args) {
        launch(args);
    }
}
