public class Kaart {
    private String mast;
    private String suurus;
    private int[] väärtused;
    private boolean pööratud = false;

    public Kaart(String mast, String suurus, int[] väärtused) {
        this.mast = mast;
        this.suurus = suurus;
        this.väärtused = väärtused;
    }

    public String getMast() {
        return mast;
    }

    public String getSuurus() {
        return suurus;
    }

    public boolean isPööratud() {
        return pööratud;
    }

    public int[] getVäärtused() {
        return väärtused;
    }

    public void avaKaart() {
        // pöörab kaardi ringi
        this.pööratud = true;
    }

    public String lahtiselt(){
        return mast + "\n" + suurus;
    }

    @Override
    public String toString() {
        if (pööratud) {
            return mast + " \n  " + suurus;
        } else {
            return "XXX\n XXX";
        }
    }
}
