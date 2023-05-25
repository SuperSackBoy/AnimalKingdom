import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class AICardPanel extends CardPanel{
    public AICardPanel(Card card) {
        super(card);
    }

    public void destroy() {
        JPanel parent = (JPanel) this.getParent(); //get the parent panel
        parent.remove(this); //remove the card
        parent.revalidate();
        parent.repaint();
        //TODO remove from AI Field array
    }

    public void attack() { //Called when the card should attack
        attackAnimation();
        AICardPanel[] aiCards = AICardManager.AIPlayed;
        PlayerCardPanel[] plyrCards = PanelManager.player.PlayerPlayedCards;
        for(int x = 0; x < 4; x++) {
            if (aiCards[x] != null) if (aiCards[x].card == this.card) {
                if(plyrCards[x] != null) {
                    if(this.card.getATK() <= plyrCards[x].card.getHP()) {
                        plyrCards[x].card.removeHP(this.card.getATK());
                        plyrCards[x].destroy();
                    } else {
                        PanelManager.player.removeHP(this.card.getATK() - plyrCards[x].card.getHP());
                        plyrCards[x].destroy();
                    }
                } else {
                    PanelManager.player.removeHP(this.card.getATK());
                }
                return;
            }
        }
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
