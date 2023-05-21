/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Panel to display a card
*/

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import static javax.imageio.ImageIO.read;

public class CardPanel extends JPanel {
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
    private final JLabel lName = new JLabel("name");

    protected Card card;

    public CardPanel(Card card) {
        loadBackgroundImage(card);//sets panel to background image
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLACK);
        this.card = card;
        lName.setText(card.getName());
        lName.setBounds(2,0,PanelManager.CardWidth,30);
        lHP.setBounds(29+2,89-12,PanelManager.CardWidth,30);
        lAP.setBounds(36+2,107-12,PanelManager.CardWidth,30);
        lVP.setBounds(30+2,125-12,PanelManager.CardWidth,30);
        this.add(lName);
        this.add(lHP);
        this.add(lAP);
        this.add(lVP);
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

        lHP.setText(""+hp);
        lAP.setText(""+ap);
        lVP.setText(""+vp);
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
    /**
     * Sets bgImg to the files path
     */
    private void loadBackgroundImage(Card cCard) {
        try {
            File f = new File(cCard.cardImg);
            if(f.exists() && !f.isDirectory()) {
                lName.setVisible(false);
                this.setOpaque(false);
                bgImg = read(getClass().getResourceAsStream(cCard.cardImg));
            } else {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }

    /**
     * Adds background image to panel bg
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImg != null) {
            // Scale the image to fit the panel dimensions
            Image scaledImage = bgImg.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, null);
        }
    }
}
