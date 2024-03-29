package src;
/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
A Version of the card object that can be moved by the player
*/

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class PlayerCardPanel extends CardPanel {
    private boolean pressed = false;
    public PlayerCardPanel(Card card) {
        super(card);
        this.addMouseListener(new MouseListener() { //To drag cards around
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == 1) { //left mouse
                    PanelManager.selectLbl.setVisible(false);
                    PlayerDropLocation d = onDropLocation(); //if the card is on a drop zone, or exceeds the players VP, dont activate it
                    if(d == null && card.getVP() <= PanelManager.player.getVP()) {
                        pressed = true;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == 1) {
                    PanelManager.selectLbl.setVisible(false);
                    pressed = false;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!PanelManager.board.showHand) {//only shows when hand is visible
                    PanelManager.selectLbl.setVisible(true);
                    PanelManager.selectLbl.setBounds(PlayerCardPanel.super.getX() + 30, 450, 16 * 2, 20 * 2);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                PanelManager.selectLbl.setVisible(false);
            }

            public void mouseClicked(MouseEvent e) {}
        });
    }
    private boolean latch;
    private Point desiredLocation;
    public void update() {
        super.update();
        if(PanelManager.mouse != null) { //mouse is null if off screen
            if(desiredLocation == null) { //initialize point
                desiredLocation = new Point(x,y);
            }
            if(pressed) { //if the card is clicked
                PanelManager.board.setComponentZOrder(this,0);
                if(!latch) { //called only once as soon as the card is clicked
                    latch = true;
                    PanelManager.board.moveUp(); //show the play area
                } else {
                    this.x = PanelManager.mouse.x - width/2;
                    this.y = PanelManager.mouse.y - height/2;
                }
            } else { //if the card is not clicked
                if(latch) { //called once when the card is dropped
                    latch = false;
                    Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        public void run() {
                            PanelManager.board.moveDown(); //show the hand after short delay
                        }
                    }, 150);

                    PlayerDropLocation d = getClosestDrop();
                    if(d != null) {
                        if(d.active) {
                            d = null;
                        }
                    }

                    if(d != null) { //set desired location to the drop point
                        desiredLocation.x = d.x;
                        desiredLocation.y = d.y;
                        PanelManager.board.setComponentZOrder(this,6);
                        d.active = true; //activate the drop location
                        playCard();
                        for(int x = 0; x < PanelManager.player.PlayerHand.length; x++) {
                            if (PanelManager.player.PlayerHand[x].equals(this)) {
                                PanelManager.player.PlayerHand[x] = this;
                                PanelManager.board.drawNewCard(x, CardDeck.drawCard());
                                PanelManager.player.PlayerPlayedCards[d.index] = this;
                            }
                        }
                    } else { //return to start location
                        desiredLocation.x = startX;
                        desiredLocation.y = startY;
                    }
                    smoothTransition(desiredLocation); //smoothly go from current location to desired location
                }
            }
        }
    }

    public void playCard() { //Called when the card is moved to a drop zone
        PanelManager.player.removeVP(card.getVP());
    }
    @Override
    public void destroyCard() {
        JPanel parent = (JPanel) this.getParent(); //get the parent panel
        PlayerDropLocation d = onDropLocation(); //get the cards drop location
        if(d != null) d.active = false; //if the card is on a drop location set it to be open
        parent.remove(this); //remove the card
        parent.revalidate();
        parent.repaint();
        for(int x = 0; x < PanelManager.player.PlayerPlayedCards.length; x++) {
            if(PanelManager.player.PlayerPlayedCards[x] != null) if (PanelManager.player.PlayerPlayedCards[x].equals(this)) {
                PanelManager.player.PlayerPlayedCards[x] = null;
            }
        }
    }

    public void attack() { //Called when the card should attack, see AICardPanel
        attackAnimation(true);
        AICardPanel[] cards = AICardManager.AIPlayed;
        PlayerCardPanel[] plyrCards = PanelManager.player.PlayerPlayedCards;
        for(int x = 0; x < 5; x++) {
            if (plyrCards[x] != null) if (plyrCards[x].card == this.card) {
                if(cards[x] != null) {
                    if(this.card.getATK() < cards[x].card.getHP()) {
                        cards[x].card.removeHP(this.card.getATK());
                        cards[x].attackAnimation(true,100);
                    } else {
                        PanelManager.ai.AiHP -= this.card.getATK() - cards[x].card.getHP();
                        cards[x].attackAnimation(true,100);
                        cards[x].destroy();
                    }
                } else {
                    PanelManager.ai.AiHP -= this.card.getATK();
                }
                return;
            }
        }
    }


    public PlayerDropLocation onDropLocation() { //returns the DropLocation the card is hovering over, null if none
        Rectangle card = new Rectangle(x,y,width,height);
        for(PlayerDropLocation d : PanelManager.dropLocations) {
            Rectangle drop = new Rectangle(d.x,d.y,d.width,d.height);
            if(card.intersects(drop)) {
                return d;
            }
        }
        return null;
    }

    public PlayerDropLocation getClosestDrop() { //gets the closest drop location the card is over
        ArrayList<PlayerDropLocation> drops = getDrops();
        PlayerDropLocation closest = new PlayerDropLocation(-99,-99,99);
        if(drops != null) {
            for(PlayerDropLocation d : drops) {
                if(new Point(closest.x,closest.y).distance(this.x,this.y) > new Point(d.x,d.y).distance(this.x,this.y)) {
                    closest = d;
                }
            }
            return closest;
        } else {
            return null;
        }
    }

    public ArrayList<PlayerDropLocation> getDrops() { //returns the DropLocation the card is hovering over, null if none
        ArrayList<PlayerDropLocation> drops = new ArrayList<>();
        Rectangle card = new Rectangle(x,y,width,height);
        for(PlayerDropLocation d : PanelManager.dropLocations) {
            Rectangle drop = new Rectangle(d.x,d.y,d.width,d.height);
            if(card.intersects(drop)) {
                drops.add(d);
            }
        }
        if(drops.isEmpty()) {
            return null;
        } else {
            return drops;
        }
    }

}
