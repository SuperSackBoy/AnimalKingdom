import java.util.Timer;
import java.util.TimerTask;

public class AICardPanel extends CardPanel{
    public AICardPanel(Card card) {
        super(card);
    }

    public void attack() { //Called when the card should attack
        attackAnimation();
    }

    public void attackAnimation() { //moves the card down then back up
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean latch = false;
            int lastY = y;
            @Override
            public void run() {
                if(!latch) y = (int) PanelManager.lerp(y,startY+50,0.1);
                else y = (int) PanelManager.lerp(y, startY, 0.1);
                if(y == lastY) {
                    if(!latch) latch = true;
                    else {
                        y = startY;
                        this.cancel();
                    }
                }
                lastY = y;
            }
        }, 1, 10);
    }

}
