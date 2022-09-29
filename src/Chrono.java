import java.util.TimerTask;

public class Chrono extends TimerTask{
    private int time;
    static int sTime;

    public Chrono(int time) {
        this.setTime(time);
    }

    @Override
    public void run() {
        int sTtime=0;
        if(sTtime==this.time){
            cancel();
        }
        sTtime++;
        sTime = sTtime;
    }

    public int getTime() {
        //return time;
        return sTime;
    }

    public void setTime(int time) {
        this.time = time;
    }

    
}
