import java.io.Serializable;

public class Noued implements Serializable{
    private Object data;
    private Noued next;
    private Noued previous; 
   
        // Constructor
        Noued(Object d)
        {
            data = d;
            next = null;
            previous = null;
        }

        public Noued() {
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Noued getNext() {
            return next;
        }

        public void setNext(Noued next) {
            this.next = next;
        }

        public Noued getPrevious() {
            return previous;
        }

        public void setPrevious(Noued previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
                return data.toString() ;
        }
        


}
