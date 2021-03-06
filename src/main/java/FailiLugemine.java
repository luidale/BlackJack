import java.io.*;
import java.util.List;

public class FailiLugemine {

    public double loeFailist(String kasutajanimi) throws Exception{
        double summa;
        // Kõik Hannesed saavad alguses palju raha
        if(kasutajanimi.toLowerCase().contains("hannes")){
            summa = 2000.0;
        } else {
            summa = 20.0;
        }
        boolean sisaldab_Kasutajat = false;
        File fail = new File("Mängijad.txt");
        if(fail.exists()){
            try(BufferedReader lugeja = new BufferedReader(new InputStreamReader(new FileInputStream(fail)))){
                String rida = lugeja.readLine();
                while(rida != null){
                    String[] tykid = rida.split(";");
                    if(tykid[0].equals(kasutajanimi)){
                        sisaldab_Kasutajat = true;
                        summa = Double.parseDouble(tykid[1]);
                        break;
                    }
                    rida = lugeja.readLine();
                }
            }
        }
        if(!sisaldab_Kasutajat){
            try(PrintWriter kirjutaja = new PrintWriter(new BufferedWriter(new FileWriter(fail,true)))){
                kirjutaja.println(kasutajanimi + ";" + summa);
            }
        }
        return summa;
    }

    public void uuendaAndmeid(double summa,String kasutajanimi) throws Exception{
        try{
            BufferedReader fail = new BufferedReader(new FileReader("Mängijad.txt"));
            StringBuffer sisend = new StringBuffer();
            String rida;

            while((rida = fail.readLine()) != null){
                String[] osad = rida.split(";");
                if(osad[0].equals(kasutajanimi)){
                    String uusrida = kasutajanimi + ";" + summa;
                    sisend.append(uusrida);
                    sisend.append("\n");
                }
                if (!osad[0].equals(kasutajanimi)){
                    sisend.append(rida);
                    sisend.append("\n");
                }
            }
            fail.close();

            FileOutputStream kirjuta = new FileOutputStream("Mängijad.txt");
            kirjuta.write(sisend.toString().getBytes());
            kirjuta.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}