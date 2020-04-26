import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Käsi {
    private ArrayList<Kaart> kaardid;
    private HBox kaardiLaud;

    public HBox getKaardiLaud() {
        return kaardiLaud;
    }

    private String style ="-fx-font-weight: bold;"+
            "-fx-background-color: #706FF1;"+
            "-fx-font-size: 13px";

    public Käsi(HBox kaardiLaud) {
        kaardid = new ArrayList<>();
        this.kaardiLaud = kaardiLaud;
    }

    public ArrayList<Kaart> getKaardid() {
        return kaardid;
    }

    public void lisaKaart(Pakk pakk, boolean avatud) {
        Kaart uuskaart = pakk.jagaKaart();
        if (avatud)
            uuskaart.avaKaart();
        kaardid.add(uuskaart);

        Label kaardiKuva = new Label(" " + uuskaart + " ");
        kaardiKuva.setStyle(style);


        kaardiLaud.getChildren().add(kaardiKuva);
    }

    public int summa() {
        int summa = 0;
        int ässad = 0;
        for(Kaart kaart: kaardid){
            summa += kaart.getVäärtused()[0];
            String suurus = kaart.getSuurus();
            if(suurus.equals("Ä"))
                ässad += 1;
        }
        if(summa < 12 && ässad > 0)
            summa += 10;
        return summa;
        }


        @Override
        public String toString () {
            List<String> result = new ArrayList<>();
            for (Kaart kaart : kaardid) {
                result.add(String.valueOf(kaart));
            }
            return String.join(" ", result);
        }
    }
