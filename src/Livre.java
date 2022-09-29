

public class Livre extends Ouvrage{
    private String auteur, titre, editeur ;

    public Livre(int cote,String date,  String auteur, String titre, String editeur) {
        super(cote,date);
        this.auteur = auteur;
        this.titre = titre;
        this.editeur = editeur;
    }

    public Livre() {
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }
    
    @Override
    public String toString() {

        return  super.toString() +  "\t" + super.envollopeMot(auteur,15) + "\t" + super.envollopeMot(titre,15) + "\t" +super.envollopeMot(editeur,15) + "\n";
    }

}
