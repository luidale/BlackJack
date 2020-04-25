public class Mängur {
    double rahakott;
    String nimi;

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public double getRahakott() {
        return rahakott;
    }

    public void setRahakott(double rahakott) {
        this.rahakott = rahakott;
    }

    public void muudaRahakott(double raha){
        this.rahakott += raha;
    }

    public Mängur(double rahakott, String nimi) {
        this.rahakott = rahakott;
        this.nimi = nimi;
    }

    public Mängur() {
        this.rahakott = 20;
    }
}
