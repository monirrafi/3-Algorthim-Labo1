import java.util.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class Affichage {
  
 /* 
    static DefaultTableModel tableModel = new DefaultTableModel();
    static JTable table =  new JTable(tableModel);

    public static void creaTable(){
        
        tableModel.addColumn("Languages");
        tableModel.addColumn("temps");
        tableModel.addColumn("action");
            ArrayList<String> list = new ArrayList<>(){{add("");add("Column 2");add("Column 3");}};
        
          Vector row = new Vector();
        for(String str:list){
        row.add(str);
        }
        tableModel.addRow(row);
    }


//    public static void ajouterRow(ArrayList<String> rowList) {
   // }
    public static void yourAddRow(String str1, String str2, String str3){

        //DefaultTableModel yourModel = (DefaultTableModel) table.getModel();
        tableModel.addRow(new Object[]{str1, str2, str3});
      }*/
   public static void main(String[] argv) throws Exception {
    MyListe liste = new MyListe();
    liste.ajouter(new Livre(15,"date","auteur","titre","editeur"));
    liste.ajouter(2);
    liste.ajouter(3);
    liste.ajouter(4);
    liste.lister();/*
    liste.suprimer(14);
    liste.printList(liste);
    liste.suprimer(4);
    liste.printList(liste);
    liste.ajouter(5);
    liste.printList(liste);
    liste.ajouter(1);
    liste.printList(liste);
    liste.suprimer(3);
    liste.printList(liste);
    liste.ajouter(6);
    liste.printList(liste);
    liste.suprimer(2);
    liste.printList(liste);*/
    System.out.println(liste.size());
    System.out.println(liste.get(14));
    System.out.println(liste.get(3));
    System.out.println(liste.get(0));
  
    /*
        creaTable();
       // ajouterRow(list);
        yourAddRow("","c2","c3");
        yourAddRow("c1","c2","c3");
        yourAddRow("","c2","");
        
      JFrame f = new JFrame();
      f.setSize(550, 350);
      f.add(new JScrollPane(table));
      f.setVisible(true);
      //System.exit(0);*/
   }

}