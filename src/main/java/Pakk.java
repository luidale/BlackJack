import java.util.ArrayList;
import java.util.List;

public class Pakk {
    private ArrayList<Kaart> kaardid;

    public Pakk() {
        kaardid = new ArrayList<>();
        String[] mastid = {"Ri", "Ru", "Po", "Är"};
        String[] suurused = {"2","3","4","5","6","7","8","9","10","S","E","K","Ä"};
        int[][] väärtused = {{2},{3},{4},{5},{6},{7},{8},{9},{10},{10},{10},{10},{1,11}};

        // Tekita kaardid
        for (String s : mastid) {
            for (int j = 0; j < suurused.length; j++) {
                //System.out.println(i + "-" + j);
                this.kaardid.add(new Kaart(s, suurused[j], väärtused[j]));
            }
        }

    }

    public ArrayList<Kaart> getKaardid() {
        return kaardid;
    }

    public void sega(){
        // Ajab kaardid juhuslikku järjekorda
        for (int i = 0; i<this.kaardid.size()*2; i++){
            // Võta pakist juhuslik kaart
            int juhuslikNumber1 = (int)(Math.random()*this.kaardid.size());
            Kaart segatavKaart = this.eemaldaKaart(juhuslikNumber1);
            // Lisa kaart juhuslikku kohta pakis
            int juhuslikNumber2 = (int)(Math.random()*this.kaardid.size());
            this.kaardid.add(juhuslikNumber2,segatavKaart);
        }
    }

    public Kaart jagaKaart(){
        // eemaldab pakist esimese kaardi
        // kasutatud testimiseks kaardi jagamise simuleerimiseks
        return eemaldaKaart(0);
    }

    public Kaart eemaldaKaart(int indeks){
        // eemaldab kaardi antud positsioonilt
        Kaart eemaldatavKaart = this.kaardid.get(indeks);
        this.kaardid.remove(indeks);
        return eemaldatavKaart;
    }

    @Override
    public String toString() {
        //String result = "";
        List<String> result = new ArrayList<>();
        for (int i = 0; i< kaardid.size();i++){
            result.add(kaardid.get(i).lahtiselt());
        }
        return String.join(", ",result);
    }
}
