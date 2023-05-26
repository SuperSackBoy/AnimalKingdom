import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class AICardPanel extends CardPanel{
    public AICardPanel(Card card) { //Base constructor
        super(card);
    }

    public void destroy() {
        JPanel parent = (JPanel) this.getParent(); //get the parent panel
        parent.remove(this); //remove the card
        parent.revalidate(); //reupdate the frame, technically not needed since its being called in Main, but whatever
        parent.repaint();
        for(int x = 0; x < AICardManager.AIPlayed.length; x++) { //find itself in the AiPlayed array
            if(AICardManager.AIPlayed[x] != null) if(AICardManager.AIPlayed[x].equals(this)) {
                AICardManager.AIPlayed[x] = null; //remove itself
            }
        }
    }

    public void attack() { //Called when the card should attack
        attackAnimation(); //call attack animation
        AICardPanel[] aiCards = AICardManager.AIPlayed; //get an array of the Ai's played cards
        PlayerCardPanel[] plyrCards = PanelManager.player.PlayerPlayedCards; //get an array of the Player's played cards
        for(int x = 0; x < 5; x++) { //loop through them
            if (aiCards[x] != null) if (aiCards[x].card == this.card) { //if it finds itself in the array
                if(plyrCards[x] != null) { //check if the card in front of it is there
                    if(this.card.getATK() < plyrCards[x].card.getHP()) { //if it won't kill the card
                        plyrCards[x].card.removeHP(this.card.getATK()); //reduce the opposing card by this cards damage
                    } else { //if it will kill the card
                        PanelManager.player.removeHP(this.card.getATK() - plyrCards[x].card.getHP());
                        plyrCards[x].destroy(); //remove that card and reduce health from the player
                    }
                } else { //reduce health from the player
                    PanelManager.player.removeHP(this.card.getATK());
                }
                return; //breaks the for loop
            }
        }
    }

    public void attackAnimation() { //moves the card down then back up
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() { //timer called every 10ms
            boolean latch = false; //toggled when it should move the other way
            int lastY = y; //last location it was in
            @Override
            public void run() {
                if(!latch) y = (int) PanelManager.lerp(y,startY+50,0.1); //linear interpolate down
                else y = (int) PanelManager.lerp(y, startY, 0.1); //linear interpolate up
                if(y == lastY) { //if it is in the same position as it was last time it was called
                    if(!latch) latch = true; //toggle the latch
                    else {
                        y = startY; //snap to the original location
                        this.cancel(); //stop the timer
                    }
                }
                lastY = y;
            }
        }, 1, 10);
    }

}
