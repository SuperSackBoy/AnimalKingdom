/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
*/

import java.awt.Color;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
    public int x = 0;
    public int y = 0;
    protected int startX = 0;
    protected int startY = 0;
    public int width = PanelManager.CardWidth;
    public int height = PanelManager.CardHeight;

    public int hp = 10; //health
    public int ap = 10; //attack
    public int vp = 10; //value

    private JLabel lHP = new JLabel(Integer.toString(hp));
    private JLabel lAP = new JLabel(Integer.toString(ap));
    private JLabel lVP = new JLabel(Integer.toString(vp));

    public CardPanel() {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(Color.black);

        lHP.setBounds(2,0,PanelManager.CardWidth,15);
        lAP.setBounds(2,15,PanelManager.CardWidth,15);
        lVP.setBounds(2,30,PanelManager.CardWidth,15);
        this.add(lHP);
        this.add(lAP);
        this.add(lVP);
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
        lHP.setText("HP: " + Integer.toString(hp));
        lAP.setText("AP: " + Integer.toString(ap));
        lVP.setText("VP: " + Integer.toString(vp));
    }

    public void setX(int x) {
        this.x = x;
        this.startX = x;
    }
    public void setY(int y) {
        this.y = y;
        this.startY = y;
    }
}
