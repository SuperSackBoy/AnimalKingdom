package src;/*
Aaron Kimbel & Luca Mazzotta
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Panel to display a card
*/

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class CardPanel extends JPanel implements CardInterface{
    private BufferedImage bgImg; //image variable
    public int x = 0;
    public int y = 0;
    protected int startX = 0;
    protected int startY = 0;
    public int width = PanelManager.CardWidth;
    public int height = PanelManager.CardHeight;

    public int hp = 10; //health
    public int ap = 10; //attack
    public int vp = 10; //value

    private final JLabel lHP = new JLabel(Integer.toString(hp));
    private final JLabel lAP = new JLabel(Integer.toString(ap));
    private final JLabel lVP = new JLabel(Integer.toString(vp));

    protected Card card;
    private float opacity = 1f;

    public CardPanel(Card card) {
        this.card = new Card(card.getName(),card.cardImg,card.getHP(),card.getATK(), card.getVP());
        this.setLayout(null); //formatting
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLACK);
        lHP.setBounds(29+2,89-12,PanelManager.CardWidth,30);
        lAP.setBounds(36+2,107-12,PanelManager.CardWidth,30);
        lVP.setBounds(30+2,125-12,PanelManager.CardWidth,30);
        this.add(lHP);
        this.add(lAP);
        this.add(lVP);
        GraphicsComponents.loadBackgroundImage(this.card.cardImg, this);
    }

    public Card getCard() {
        return this.card;
    }

    public void smoothTransition(Point p) { //smoothly moves from current location to p
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int lastX = x;
            int lastY = y;
            @Override
            public void run() {
                float xD = x + (p.x - x) * 0.1f; //get a point between current location and dest
                float yD = y + (p.y - y) * 0.1f;
                x = (int)xD; //go to that location
                y = (int)yD;

                Point p2 = new Point(x,y);
                if(p.distance(p2) <= 0.5 || (x == lastX && y == lastY)) { //if card is at destination or has stopped moving (from a rounding error with float -> int)
                    setX(p.x); //snap to the correct location
                    setY(p.y);
                    this.cancel(); //stop the timer
                }
                lastX = x;
                lastY = y;
            }
        }, 1, 10);
    }

    public void update() {
        this.hp = card.getHP();
        this.ap = card.getATK();
        this.vp = card.getVP();

        lHP.setText(String.valueOf(hp));
        lHP.setFont(GraphicsComponents.pixelFontSmall);
        lHP.setForeground(Color.BLACK);
        lAP.setText(String.valueOf(ap));
        lAP.setFont(GraphicsComponents.pixelFontSmall);
        lAP.setForeground(Color.BLACK);
        lVP.setText(String.valueOf(vp));
        lVP.setFont(GraphicsComponents.pixelFontSmall);
        lVP.setForeground(Color.BLACK);
        this.setBounds(x,y,width,height); //update cards x and y
    }

    public void setX(int x) {
        this.x = x;
        this.startX = x;
    }
    public void setY(int y) {
        this.y = y;
        this.startY = y;
    }

    public void destroy() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                opacity -= 0.01f;
                if(opacity <= 0) {
                    opacity = 0;
                    destroyCard();
                    this.cancel();
                }
            }
        },0,5);
    }

    public void attackAnimation(boolean goUp, int delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                attackAnimation(goUp);
            }
        },delay);
    }
    public void attackAnimation(boolean goUp) {
        int distance = 50;
        if(goUp) distance = distance * -1;


        Timer timer = new Timer();
        int finalDistance = distance;
        timer.scheduleAtFixedRate(new TimerTask() { //timer called every 10ms
            boolean latch = false; //toggled when it should move the other way
            int lastY = y; //last location it was in
            @Override
            public void run() {
                if(!latch) y = (int) PanelManager.lerp(y,startY+ finalDistance,0.1); //linear interpolate down
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

    public void destroyCard() {}
}
