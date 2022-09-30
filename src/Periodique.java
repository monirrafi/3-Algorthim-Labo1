public class Periodique extends Ouvrage{
    private String nom;
    private int numero,periodicite;

    public Periodique(int cote,String date,String nom, int numero, int periodicite) {
        super(cote,date);
        this.nom = nom;
        this.numero = numero;
        this.periodicite = periodicite;
    }
    public Periodique() { }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getPeriodicite() {
        return periodicite;
    }
    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
    }
    
    public String strAffichage() {

        return  super.strAffichage() +  ";" + nom + ";" + numero + ";" + periodicite + "\n";
    }
    @Override
    public String toString() {

        return  super.toString() +  "\t" + super.envollopeMot(nom,15) + "\t" + numero + "\t" + periodicite + "\n";
    }
    

    
    
}
