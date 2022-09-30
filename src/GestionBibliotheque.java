import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public class GestionBibliotheque{
    static GestionBibliotheque gBibliotheque;
    static Bibliotheque biblio;

    
    GestionBibliotheque(Bibliotheque biblio) throws IOException{
        
        if(biblio instanceof BiblioTab){
            this.biblio = (BiblioTab) biblio;
        }else if(biblio instanceof BiblioLink){
            this.biblio = (BiblioLink) biblio;
        }else if(biblio instanceof BiblioPerso){
            this.biblio = (BiblioPerso) biblio;
            
        }


    }

    public static void main(String[] args) throws Exception {
        biblio = new BiblioLink();
        //gBibliotheque = new GestionBibliotheque(biblio);
        //biblio.Ajouter("livre");
        biblio.Suprimer(15);
        biblio.sauvegarder();
        JTextArea sortie = new JTextArea(20,120);
        sortie.append(biblio.toString());
        JOptionPane.showMessageDialog(null, sortie);

    }

}