import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public class GestionBibliotheque extends JFrame implements actionEcouteur{
    static GestionBibliotheque gBibliotheque;
    private Bibliotheque biblio;
    private String txtSortie="";
    private JTextPane sortie = new JTextPane();

    static JButton btnSuprimer= new JButton("Suprimer");
    static JButton btnQuitter = new JButton("Quitter");
    static JButton btnMenu= new JButton("Retour au menu");
    static JButton btnBiblioTab = new JButton("Biblio tableau");
    static JButton btnBiblioLinked = new JButton("Biblio linked");
    static JButton btnBiblioPer = new JButton("Biblio Personnel");
    static JButton btnMAJ = new JButton("Statistiques");
    static JButton btnRechercher = new JButton("Rechercher");
    static JTextPane paneStatistiques;
    
    
    static JPanel paneAffichage = new JPanel();
    static JPanel panePrincipal = new JPanel();
    /*
     * Creer un radio bouton
     * 
     */
    static JRadioButton vide = new JRadioButton();
    static JRadioButton livre = new JRadioButton();
    static JRadioButton cd=new JRadioButton();
    static JRadioButton periodique = new JRadioButton();
    JLabel lblChoix = new JLabel("      Choisir un ouvrage pour ajouter");
    
    
    GestionBibliotheque() {
        menu();
        cliquer();
    }
    private void changerEntete(JTextPane tp, String msg, Color c)
        {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Background, c);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
        }
    public void changerSortie(){
        txtSortie = "";
        txtSortie = biblio.toString();
        sortie = new JTextPane();

        sortie.setSize(new Dimension(5,120));
        changerEntete(sortie, txtSortie.substring(0,txtSortie.indexOf("\n")), Color.YELLOW);
        changerEntete(sortie, txtSortie.substring(txtSortie.indexOf("\n")), Color.WHITE);

    }
    GestionBibliotheque(Bibliotheque biblio){
        
        if(biblio instanceof BiblioTab){
            this.biblio = (BiblioTab) biblio;
        }else if(biblio instanceof BiblioLink){
            this.biblio = (BiblioLink) biblio;
        }else if(biblio instanceof BiblioPerso){
            this.biblio = (BiblioPerso) biblio;
            
        }
        
        changerSortie();
        sortie.setText(txtSortie);
        afficher();
        cliquer();
    }
    public void menu(){
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panePrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER,50,150));
        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.LEFT,15,35));
        paneButton.setBackground(new Color(51,104,255));
        panePrincipal.setBackground(new Color(51,104,255));
        btnBiblioTab = new JButton("Biblio tableau");
        btnBiblioLinked = new JButton("Biblio linked");
        btnBiblioPer = new JButton("Biblio Personnel");
        btnMAJ = new JButton("Statistiques");

        paneButton.add(btnMAJ);
        paneButton.add(btnBiblioTab);
        paneButton.add(btnBiblioLinked);
        paneButton.add(btnBiblioPer);
        paneButton.add(btnQuitter);
        panePrincipal.add(paneButton);
    
        add(panePrincipal);
        pack();
        setVisible(true);
        
    }
    public void afficher(){
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        
        panePrincipal = new JPanel(new GridBagLayout());
        paneAffichage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paneAffichage.setBackground(Color.white);
        changerSortie();
        paneAffichage.add(sortie);


        JPanel paneRadio = new JPanel(new GridLayout(2,1));
        paneRadio.setBackground(Color.lightGray);
        JPanel paneElementradio = new JPanel();
        paneElementradio.setBackground(Color.lightGray);
        vide = new JRadioButton("");
        livre = new JRadioButton("Livre");
        cd = new JRadioButton("CD");
        periodique = new JRadioButton("Periodique");
    
        vide.setBackground(Color.lightGray);
        livre.setBackground(Color.lightGray);
        cd.setBackground(Color.lightGray);
        periodique.setBackground(Color.lightGray);
        

        ButtonGroup groupeWeb = new ButtonGroup();
        groupeWeb.add(vide);
        groupeWeb.add(livre);
        groupeWeb.add(cd);
        groupeWeb.add(periodique);
        vide.setSelected(true);
        paneElementradio.add(vide);
        paneElementradio.add(livre);
        paneElementradio.add(cd);
        paneElementradio.add(periodique);

        paneRadio.add(lblChoix);
        paneRadio.add(paneElementradio);

        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.LEFT,40,20));
        paneButton.setBackground(new Color(51,204,255));

        btnSuprimer = new JButton("Suprimer");
        btnMenu = new JButton("Retour au Menu"); 
        btnRechercher = new JButton("Rechercher"); 

        paneButton.add(btnRechercher);
        paneButton.add(paneRadio);
        paneButton.add(btnSuprimer);
        paneButton.add(btnMenu);

        //dispaly des panels
        c.ipadx = 1500;      
        c.ipady = 150;      
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth=1;
        panePrincipal.add(paneAffichage,c);

        //c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 800;      
        c.ipady = 50;      
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth=2;
        panePrincipal.add(paneButton,c);
        add(panePrincipal);
        pack();
        setVisible(true);

    }
    public void suprimerBilio(){
        int cote=0,cond = 0;
        
        String strCote = JOptionPane.showInputDialog(null, 
        "Entrez le numero a suprimer ");
 
        if(strCote==null || strCote.equals(" ")){
            cote=0;
        }else{
            cote = Integer.parseInt(strCote);
        }
        while(cond==0){
                if(cote==0){
                    cond=1;
                }else if(biblio.Rechercher(cote)){
                    biblio.Suprimer(cote);
                    txtSortie = biblio.toString();
                    //changerSortie();
                    sortie.setText(txtSortie);
                    panePrincipal.repaint();
                    cond=1;
        
                }else{
                    strCote = JOptionPane.showInputDialog(null, 
                    "Le numero " + cote + " n'existe pas \n Entrez un autre numero a suprimer ");
            
                    if(strCote==null || strCote.equals(" ")){
                        cond=1;
                    }else{
                        cote = Integer.parseInt(strCote);
                    }
        
                }
            }
     }
     public void rechercherBilio(){
        String strCote = JOptionPane.showInputDialog(null,"Entrez le numero a chercher");
        int cote = Integer.parseInt(strCote);
        if(biblio.Rechercher(cote)){
            JOptionPane.showMessageDialog(null,"Le numero " + cote + " existe");
        }else{
            JOptionPane.showMessageDialog(null,"Le numero " + cote + " n'existe pas");
            
        }
     }

    public void ajouterBiblio(String typeOuvrage) {
        
            biblio.Ajouter(typeOuvrage);
            txtSortie = biblio.toString();
            sortie.setText(txtSortie);
            panePrincipal.repaint();
            vide.setSelected(true);
    }
        
    
    public void actionBouton(ActionEvent e) {
        if(e.getSource() == btnQuitter){
                System.exit(0);
        }else if(e.getSource() == btnSuprimer){   
            suprimerBilio();
           
        }else if(e.getSource() == livre){
            ajouterBiblio("livre");
        }else if(e.getSource() == cd){
            ajouterBiblio("cd");
        }else if(e.getSource() == periodique){
            ajouterBiblio("periodique");
        }else if(e.getSource() == btnBiblioLinked){
            try {
                biblio = new BiblioLink();
                gBibliotheque = new GestionBibliotheque(biblio);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(e.getSource() == btnBiblioPer){
            try {
                biblio = new BiblioPerso();
                gBibliotheque = new GestionBibliotheque(biblio);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else if(e.getSource() == btnBiblioTab){
            try {
                biblio = new BiblioTab();
                gBibliotheque = new GestionBibliotheque(biblio);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(e.getSource() == btnMenu){
            try {
                biblio.sauvegarder();
                gBibliotheque = new GestionBibliotheque();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }else if(e.getSource() == btnMAJ){
            Bibliotheque.afficherStatistique();
        }else if(e.getSource() == btnRechercher){
            rechercherBilio();
        }    
        
    }

    public static void main(String[] args) throws Exception {
        gBibliotheque = new GestionBibliotheque();

    }
    public Bibliotheque getBiblio() {
        return biblio;
    }
    public void setBiblio(Bibliotheque biblio) {
        this.biblio = biblio;
    }
    public JTextPane getSortie() {
        return sortie;
    }
    public void setSortie(JTextPane sortie) {
        this.sortie = sortie;
    }

    @Override
    public void cliquer() {
        btnMAJ.addActionListener(this::actionBouton);
        btnRechercher.addActionListener(this::actionBouton);
        btnBiblioTab.addActionListener(this::actionBouton);
        btnBiblioLinked.addActionListener(this::actionBouton);
        btnBiblioPer.addActionListener(this::actionBouton);
        btnQuitter.addActionListener(this::actionBouton);
        livre.addActionListener(this::actionBouton);
        cd.addActionListener(this::actionBouton);
        periodique.addActionListener(this::actionBouton);
        btnMenu.addActionListener(this::actionBouton);
        btnSuprimer.addActionListener(this::actionBouton);
        
        
    }

}