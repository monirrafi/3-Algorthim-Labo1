import java.io.Serializable;

public class MyListe implements InterfaceListe,Serializable {
    private Noued tete=null;
    private Noued queue=null;
    private int size=0;
    private int index=0;

    public MyListe(Object obj) {
        this.tete = new Noued(obj);
        this.queue = tete;
        size=0;
    }

    public MyListe() {
    }

    @Override
    public void ajouter(Object obj) {
        
        Noued nouveauNoued = new Noued(obj);
        if(queue == null){
            this.tete = nouveauNoued;
            this.queue = nouveauNoued;
        }else{
            Noued tmp = tete;
            while(tmp != null){
                if(tmp.getNext()==null){
                    tmp.setNext(nouveauNoued);
                    this.queue = nouveauNoued;
                    break;
                }else{
                    tmp=tmp.getNext();
                }

            }
        }

    }

    @Override
    public void suprimer(Object obj) {
        Noued nouedSuprimer = new Noued(obj);
        Noued tmp = tete;
        Noued prev = null;
        //if(!recherche(obj).equals("Null")){
            if(tete.getData() == nouedSuprimer.getData()){
                tete=tete.getNext();
                
            
            }else{
                while(tmp != null){
                    if(tmp.getData() == nouedSuprimer.getData()){
                        prev.setNext(tmp.getNext());                        
                        break;
                    }else if(tmp.getData() == nouedSuprimer.getData() && tmp.getNext()==null){
                        queue=prev;
                    }else{
                        prev =tmp ;   
                        tmp=tmp.getNext();
                    }
                    
                }
            }
        }
        
    

    @Override
    public Object recherche(Object obj) {
        Noued nouedCherchee = new Noued(obj);
        boolean retour=false;
        Noued tmp = tete;
       // Noued suivant = this.queue;
       while(tmp != null){
        if(tmp.getData() == nouedCherchee.getData()){
            retour = true;
            break;
            
        }else{
            tmp = tmp.getNext();
        }
        }
        if(retour == false){  
              
			return "Null";
        }else{
            return tmp.getData();
        }
            

	}

    

    @Override
    public void lister() {
        {
            Noued currNode = tete;
            System.out.print("[");
 
            while (currNode != null) {
                System.out.print(currNode.getData() + " ");
                currNode = currNode.getNext();
            }
            System.out.println("]");
 
         }
         
    }



    public Noued getTete() {
        return tete;
    }

    public void setTete(Noued tete) {
        this.tete = tete;
    }

    public Noued getQueue() {
        return queue;
    }

    public void setQueue(Noued queue) {
        this.queue = queue;
    }

    public int size() {
        size=0;
        Noued currNode = tete;
        while (currNode != null) {
            size++;
            currNode = currNode.getNext();
        }
        return size;
    }


    public int getIndex(Object obj) {
        index=0;
        Noued currNode = new Noued(obj);
        Noued tmp =tete;
        while (tmp != null) {
            if(tmp.getData()==currNode.getData() ){
                break;
            }else{
                index++;
                tmp = tmp.getNext();
    
            }
        }

        return index;
    }

    @Override
    public Object get(int index) {
        int i=0;
        boolean ret =false;
        //Noued currNode = new Noued(obj);
        Noued tmp =tete;
        while (tmp != null) {
            if(i==index ){
                ret=true;
                break;
            }
            i++;
            tmp=tmp.getNext();
        }
        if(ret){
            return tmp.getData();
        }else{
            return "Null";
        }
    }


    
}
