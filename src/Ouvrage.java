import java.io.Serializable;

public class Ouvrage implements Serializable{
    
    protected String date;
    protected int cote;
    public Ouvrage( int cote,String date) {
        this.date = date;
        this.cote = cote;
    }
    public Ouvrage() {
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getCote() {
        return cote;
    }
    public void setCote(int cote) {
        this.cote = cote;
    }
    public static String envollopeMot(String mot, int taille){
        String retour;
        if(mot.length()>= taille){
            retour = mot.substring(0,taille);
        }else{
            retour = mot;
            for(int i=0; i<taille-mot.length();i++){
                retour = retour + " ";
            }
        }
        return retour;
    }
    
    public String strAffichage() {
        //return "  " + this.cote + ";" + this.date ;
        return this.date ;
    
    }
    @Override
    public String toString() {
        return "  " + this.cote + "\t" + this.date ;
    }

    
}
