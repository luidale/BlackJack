import java.io.*;

public class FailiLugemine {

    public double loeFailist(String kasutajanimi) throws Exception{
        double summa = 20.0;
        if(new File("Mängijad.txt").exists()){
            try(BufferedReader lugeja = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Mängijad.txt"))))){
                String rida = lugeja.readLine();
                while(rida != null){
                    String[] tykid = rida.split(" ");
                    if(tykid[0].equals(kasutajanimi)){
                        summa = Double.parseDouble(tykid[1]);
                        break;
                    }
                    rida = lugeja.readLine();
                }
            }
        }
        else if(!new File("Mängijad.txt").exists()){
            try(BufferedWriter kirjutaja = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("Mängijad.txt"))))){
                kirjutaja.write(kasutajanimi + " " + summa);
            }
        }
        return summa;
    }



    public void uuendaAndmeid(double summa,String kasutajanimi) throws Exception{
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
