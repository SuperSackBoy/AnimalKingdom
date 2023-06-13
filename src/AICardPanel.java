/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Displays the AI's cards
*/

package src;

import javax.swing.*;
import java.awt.*;

public class AICardPanel extends CardPanel{
    public AICardPanel(Card card) { //Base constructor
        super(card);
    }

    @Override
    public void destroyCard() {
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
        attackAnimation(false); //call attack animation
        AICardPanel[] aiCards = AICardManager.AIPlayed; //get an array of the Ai's played cards
        PlayerCardPanel[] plyrCards = PanelManager.player.PlayerPlayedCards; //get an array of the Player's played cards
        for(int x = 0; x < 5; x++) { //loop through them
            if (aiCards[x] != null) if (aiCards[x].card == this.card) { //if it finds itself in the array
                if(plyrCards[x] != null) { //check if the card in front of it is there
                    if(this.card.getATK() < plyrCards[x].card.getHP()) { //if it won't kill the card
                        plyrCards[x].card.removeHP(this.card.getATK()); //reduce the opposing card by this cards damage
                        plyrCards[x].attackAnimation(false,100);
                    } else { //if it will kill the card
                        PanelManager.player.removeHP(this.card.getATK() - plyrCards[x].card.getHP());
                        plyrCards[x].attackAnimation(false,100);
                        plyrCards[x].destroy(); //remove that card and reduce health from the player
                    }
                } else { //reduce health from the player
                    PanelManager.player.removeHP(this.card.getATK());
                }
                if (PanelManager.player.getHP() <= 0) //check loss condition
                {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                Main.frame.getContentPane().removeAll();
                                Main.frame.add(Main.Wframe);
                                Main.Wframe.loadBackgroundImage(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                return; //breaks the for loop
            }
        }
    }

}
