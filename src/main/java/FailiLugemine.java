import java.io.*;

public class FailiLugemine {

    public void loeFailist(String kasutajanimi) throws Exception{
        boolean on = false;
        try(BufferedReader lugeja = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Mängijad.txt"))))){
            String rida = lugeja.readLine();
            while(rida != null){
                String[] tykid = rida.split(" ");
                if(tykid[0].equals(kasutajanimi)){
                    on = true;
                    break;
                }
                rida = lugeja.readLine();
            }
        }
        if(!on){
            try(BufferedWriter kirjutaja = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("Mängijad.txt"))))){
                double rahakott = 20.0;
                kirjutaja.write(kasutajanimi + " " + rahakott);
            }
        }
    }

    public void uuendaAndmeid(double summa,String kasutajanimi) throws Exception{
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Mangijad.txt"))))) {
            String rida = br.readLine();
            while(rida != null){
                String[] tykid = rida.split(" ");
                if(tykid[0].equals(kasutajanimi)){
                    String summa1 = String.valueOf(summa);
                    rida.replace(tykid[1],summa1);
                }
                rida = br.readLine();
            }
        }
    }
}
