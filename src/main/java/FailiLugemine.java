import java.io.*;

public class FailiLugemine {

    public void loeFailist(String kasutajanimi) throws Exception{
        boolean on = false;
        try(BufferedReader lugeja = new BufferedReader(new InputStreamReader(new FileInputStream(new File("Mängijad.txt"))))){
            String rida = lugeja.readLine();
            while(rida != null){
                if(rida.contains(kasutajanimi)){
                    on = true;
                    break;
                }
                rida = lugeja.readLine();
            }
        }
        if(!on){
            try(BufferedWriter kirjutaja = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("Mängijad.txt"))))){
                kirjutaja.write(kasutajanimi);
            }
        }
    }
}
