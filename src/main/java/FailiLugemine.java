import java.io.*;

public class FailiLugemine {

    public double loeFailist(String kasutajanimi) throws Exception{
        boolean on = false;
        double summa = 20.0;
        try(BufferedReader lugeja = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Mängijad.txt"))))){
            String rida = lugeja.readLine();
            while(rida != null){
                String[] tykid = rida.split(" ");
                if(tykid[0].equals(kasutajanimi)){
                    on = true;
                    summa = Double.parseDouble(tykid[1]);
                    break;
                }
                rida = lugeja.readLine();
            }
        }
        if(!on){
            try(BufferedWriter kirjutaja = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("Mängijad.txt"))))){
                kirjutaja.write(kasutajanimi + " " + summa);
            }
        }
        return summa;
    }

    public void uuendaAndmeid(double summa,String kasutajanimi) throws java.lang.Exception{
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Mängijad.txt"))))) {
            String rida = br.readLine();
            while(rida != null){
                if(rida.contains(kasutajanimi)){
                    String rahakott = String.valueOf(new Mängur().getRahakott());
                    try(BufferedWriter kirjutaja = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("Mängijad.txt"))))){
                        kirjutaja.write(kasutajanimi + " " + rahakott);
                    }
                }
                rida = br.readLine();
            }
        }
    }
}
