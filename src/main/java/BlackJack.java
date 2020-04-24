import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;






public class BlackJack extends Application {

    @Override
    public void start(Stage peaLava) throws Exception {
        FlowPane juur = new FlowPane(); // luuakse juur
        juur.setOrientation(Orientation.VERTICAL);
        juur.setAlignment(Pos.TOP_CENTER);

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



        Scene stseen1 = new Scene(juur, 535, 635, Color.RED);  // luuakse stseen
        peaLava.setTitle("Black Jack");  // lava tiitelribale pannakse tekst
        peaLava.setScene(stseen1);  // lavale lisatakse stseen
        peaLava.show();

        //teine rida
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
                    HBox rida2 = new HBox();
                    rida2.setSpacing(10);
                    Label kasutajanimi = new Label("Kasutaja nimi: " + kasutajanimiSisestus.getText());
                    Label summa = new Label("Summa: ");
                    kasutajanimi.setStyle("-fx-font-weight: bold");
                    summa.setStyle("-fx-font-weight: bold");
                    rida2.getChildren().addAll(kasutajanimi,summa);
                    juur.getChildren().add(rida2);
                    kasutajanimiSisestus.clear();
                    leiaKasutaja.setOnMouseClicked(e -> leiaKasutaja.setDisable(false));
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


    }

    public static void main(String[] args) {
        launch(args);
    }
}
