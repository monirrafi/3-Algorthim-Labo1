
import java.io.IOException;
import java.text.DecimalFormat;

import java.io.*;
import java.util.*;
import javax.swing.*;



public abstract class Bibliotheque implements Serializable{
    static ObjectOutputStream tmpWriteObj;
    static ObjectInputStream tmpReadObj;
    final static String FICHIER_STATISTIQUE_OBJ = "src\\statistiques.obj";
    //static ArrayList<Long> initialListe = new ArrayList<>(){{add((long) 0);}};
    private static HashMap<Integer,Long[]> statistiqueMap = new HashMap<>();
    
    
    //static HashMap<Integer,ArrayList<Double>> statistiqueAjoutMap = new HashMap<>();
    //static HashMap<Integer,ArrayList<Double>> statistiqueRechercheMap = new HashMap<>();
    static JTextArea pane = new JTextArea();
    
    public abstract void Ajouter(String typeListe);
    public abstract void Suprimer(int cote);
    public abstract boolean Rechercher(int cote);
    public abstract String toString();
    public abstract void sauvegarder() throws IOException;
    
    public static Long supMoyenne(ArrayList<Long> liste){
        long moy=0;
        int taille=liste.size();
        if(taille !=0){
            for(Long temps:liste){
                moy +=temps;
            }
            moy = (moy/taille)/1000;
       }
        return moy;
    }

    public static void afficherStatistique() {
        try {
            chargerMap();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JTextArea pane = new JTextArea(5,70);
        pane.append("Essai\tSup_Tab\tSup_Link\tSup_Perso\tAjt_Tab\tAjt_Link\tAjt_Perso\tRec_Tab\tRec_Link\tRec_Perso\n");
        for(Integer i:getStatistiqueMap().keySet()){
            pane.append(i+"\t");
            for(Long l:getStatistiqueMap().get(i)){
                pane.append(String.valueOf(l)+"\t");
            }
            pane.append("\n");
        }
        JOptionPane.showMessageDialog(null, pane);
     }
    public static String cadrerMot(String mot,int max) {
        String retour;
        int lng = mot.length();
        if(lng>=max){
            retour = mot.substring(0, max);
        }else{
            retour = mot;
            for(int i=0;i<max-lng;i++){
                retour += " ";
            }
        }
        return retour;        
    }
    public void chargerStatistiqueMap(int ind,long res) {

        int size = getStatistiqueMap().size();
        if(size!=0){
        getStatistiqueMap().get(size)[ind]=res;
        }
    }
    
    public static void chargerMap() throws Exception {
/*          if(statistiqueMap.size()==0){
            Long[] lst1 = new Long[9];
            lst1[0]=(long) 0;
            statistiqueMap.put(0,lst1) ;           
  
            pane.setSize(new Dimension(5,300));
            pane.append(cadrerMot("suprime_Tableau",20)+cadrerMot("ajout_Tableau",20)
            +cadrerMot("recherche_Tableau",20)+cadrerMot("suprime_Linked",20)+
            cadrerMot("ajout_Linked",20)+cadrerMot("recherche_Linked",20) + "\n");
        }else{        */
            File file = new File(FICHIER_STATISTIQUE_OBJ);
            if(file.exists()){
    
		try {
			tmpReadObj = new ObjectInputStream (new FileInputStream (FICHIER_STATISTIQUE_OBJ));
			statistiqueMap = (HashMap<Integer,Long[]>) tmpReadObj.readObject();
 		}catch(FileNotFoundException e)
		{
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		}catch(IOException e)
		{
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
		}catch(Exception e)
		{
			System.out.println("Un probléme est arrivé lors du chargement du fichier Bibiotheque. Contactez l'administrateur.");
		}finally
		{// Exécuté si erreur ou pas
			tmpReadObj.close();
		}
    }
    }
        //return statistiquesMap;
	

    public static HashMap<Integer,Long[]> getStatistiqueMap() {
        return statistiqueMap;
    }
    public void setStatistiqueMap(HashMap<Integer,Long[]> statistiquesMap) {
       statistiqueMap = statistiquesMap;
    }
    
    
    
}
