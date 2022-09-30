import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;

public class BiblioLink extends Bibliotheque{
/*============================================================================================================ */
/*=========================================== Declaration ===================================================== */
/*============================================================================================================ */
    final static String FICHIER_TXT = "src\\Biblio.txt";
    static BufferedReader tmpBiblio;
    static BufferedWriter tmpWriteBibilio;
/*============================================================================================================ */
/*=========================================== Constructeur ===================================================== */
/*============================================================================================================ */
    
    private LinkedList<Ouvrage> linkBiblio = new LinkedList<Ouvrage>();

    public BiblioLink() throws Exception {
        this.linkBiblio = charger();
    }
    public BiblioLink(LinkedList<Ouvrage> linkBiblio) {
        this.setLinkBiblio(linkBiblio);
    }
/*============================================================================================================ */
/*=========================================== Chargement ===================================================== */
/*============================================================================================================ */
    public LinkedList<Ouvrage> charger() {
        int r=0;
        try {
            tmpBiblio = new BufferedReader(new FileReader(FICHIER_TXT));
            String ligne = tmpBiblio.readLine();
            String[] elt = new String[6];
            int i=0;
            while(ligne != null){
                elt = ligne.split(";");
                if(elt[0].equalsIgnoreCase("L") ){
                    linkBiblio.add(new Livre(i+1,elt[1],elt[2],elt[3],elt[4]));

                }else if(elt[0].equalsIgnoreCase("P") ){
                    linkBiblio.add(new Periodique(i+1,elt[1],elt[2],Integer.parseInt(elt[3]),Integer.parseInt(elt[4])));
                    
                }else if(elt[0].equalsIgnoreCase("C") ){
                    linkBiblio.add(new CDisque(i+1,elt[1],elt[2],elt[3]));
                    
                }
                    ligne = tmpBiblio.readLine();
                i++;
                //r=i;
            }
    } catch (IOException e) {
        
        e.printStackTrace();
    }
    
    //taille = r;
    return linkBiblio;

    }
   

/*============================================================================================================ */
/*=========================================== Sauvegarde ===================================================== */
/*============================================================================================================ */

    public void sauvegarder() throws IOException {
        try {

            String content = "";
         
            File file = new File("src\\Biblio.txt");
         
            // créer le fichier s'il n'existe pas
            if (!file.exists()) {
             file.createNewFile();
            }
         
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(Ouvrage ouvrage:linkBiblio){
                    if(ouvrage instanceof Livre){
                        content = "L;"+((Livre) ouvrage).strAffichage();
                    }else if(ouvrage instanceof Periodique){
                        content = "P;"+((Periodique) ouvrage).strAffichage();
                    }else if(ouvrage instanceof CDisque){
                        content = "C;"+((CDisque) ouvrage).strAffichage();
                    }
                        
                bw.write(content);
            }
            bw.close();
         
            System.out.println("Modification terminée!");
         
           } catch (IOException e) {
            e.printStackTrace();
           }
	}

/*============================================================================================================ */
/*=========================================== SAR SAR SAR ===================================================== */
/*============================================================================================================ */
    @Override
    public boolean Rechercher(int cote) {
        boolean cond =false;
            for(int i=0;i<linkBiblio.size();i++){
                if(linkBiblio.get(i).getCote()==cote){
                    cond =true;
                    break;
                }

            }
        return cond;

    }
    public String[] paneString(String typeListe) {
            int cote =0;            
            String date="";
            String  titre="", auteur="",editeur="";
            int numero=0, periodicite=0;
            String[] retour = new String[7];

            Dimension d =new Dimension(150,20);
            ArrayList<JTextField> listeJtxt = new ArrayList<>();
            ArrayList<String> listeChamps= new ArrayList<String>();
           
            if(typeListe.equals("livre")){
                listeChamps = new ArrayList<String>(){{add("Date");add("Auteur");add("Titre");add("Editeur");}};
            }else if(typeListe.equals("cd")){
                listeChamps = new ArrayList<String>(){{add("Date");add("Auteur");add("Titre");}};
            }else if(typeListe.equals("periodique")){
                listeChamps = new ArrayList<String>(){{add("Date");add("Nom");add("Numero");add("Periodicite");}};
            }    
            
            JPanel gPane = new JPanel(new GridLayout(listeChamps.size(),1));
            for(String str:listeChamps){
                JPanel pane = new JPanel();
                JTextField jtxt = new JTextField();
                jtxt.setPreferredSize(d);
                JLabel lbl = new JLabel(str);
                lbl.setPreferredSize(d);
                lbl.setLabelFor(jtxt);
                listeJtxt.add(jtxt);
                pane.add(lbl);
                pane.add(jtxt);
                gPane.add(pane);

            }
            int res = JOptionPane.showConfirmDialog(null,gPane);
            if(res == JOptionPane.YES_OPTION){
                date = listeJtxt.get(0).getText();
                auteur = listeJtxt.get(1).getText();

                if(typeListe.equals("livre")){
                    titre = listeJtxt.get(2).getText();
                    editeur =listeJtxt.get(3).getText();
                }else if(typeListe.equals("cd")){
                    titre = listeJtxt.get(2).getText();
                }else if(typeListe.equals("periodique")){
                    numero = Integer.parseInt(listeJtxt.get(2).getText());
                    periodicite = Integer.parseInt(listeJtxt.get(3).getText());
                }    
            } 
            cote=getLinkBiblio().size()+1;
            retour[0] = date;
            retour[1] = titre;
            retour[2] = auteur;
            retour[3] = editeur;
            retour[4] = String.valueOf(numero);
            retour[5] = String.valueOf(periodicite);
            retour[6] =  String.valueOf(cote);
        return retour;        
        
    }

    @Override
    public void Ajouter(String typeListe) {
        String[] res = paneString(typeListe);
        String date=res[0];
        String  titre=res[1], auteur=res[2],editeur=res[3];
        int numero=Integer.parseInt(res[4]), periodicite=Integer.parseInt(res[5]),cote=Integer.parseInt(res[6]);
            if(typeListe.equals("livre")){
                linkBiblio.add(new Livre(cote,date,auteur,titre,editeur));
            }else if(typeListe.equals("cd")){
                linkBiblio.add(new CDisque(cote,date,auteur,titre));
            }else if(typeListe.equals("periodique")){
                linkBiblio.add(new Periodique(cote,date,auteur,numero,periodicite));
            }    
    }
    @Override
    public void Suprimer(int cote) {
        for(int i=0;i<linkBiblio.size();i++){
            if(linkBiblio.get(i).getCote() == cote){
                linkBiblio.remove(i);
                break;

            }
        }
    }
    @Override
    public String toString() {
        String strLivre="";
        String strPeriodique="";
        String strCD="";
             
        String retour= "";
        retour= "  Le nombre total des ouvrages "+ linkBiblio.size() +" par linkedList\n";
        for(Ouvrage ouvrage:linkBiblio){
            if(ouvrage instanceof Livre){
                strLivre += ((Livre) ouvrage).toString();
            }else if(ouvrage instanceof Periodique){
                strPeriodique += ((Periodique) ouvrage).toString();
            }else if(ouvrage instanceof CDisque){
                strCD += ((CDisque) ouvrage).toString();
            }
        }
        retour+= "\n  Les Livres\n  Cote\tDate\t"+ Ouvrage.envollopeMot("Auteur",15)+ Ouvrage.envollopeMot("\ttitre",15) + Ouvrage.envollopeMot("\tEditeur",15)+"\n"+ strLivre;
        retour+= "\n  Les periodiques\n  Cote\tDate\t"+ Ouvrage.envollopeMot("Nom",15)+"\tNumero\tPeriodicite\n"+ strPeriodique;
        retour+= "\n  Les CD\n  Cote\tDate\t"+ Ouvrage.envollopeMot("Titre",15)+ Ouvrage.envollopeMot("\tAuteur",15)+"\n"+ strCD;
        return retour;
    }
    public LinkedList<Ouvrage> getLinkBiblio() {
        return linkBiblio;
    }
    public void setLinkBiblio(LinkedList<Ouvrage> linkBiblio) {
        this.linkBiblio = linkBiblio;
    }

}
