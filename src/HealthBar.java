package src;/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Displays a health bar, percent is a number from 0-1
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HealthBar extends JPanel {

    public JPanel panel = new JPanel();
    private BufferedImage bgImg;
    private BufferedImage hpImg;
    public JLabel label;
    private float percent;
    private Color color = Color.RED;
    private final JPanel hp;
    private final int height;
    private final int width;
    //constructor for changing the color
    public HealthBar(int x, int y, int width, int height, float percent, String labelText, int textAlign, Color color) {
        this(x,y,width,height,percent,labelText,textAlign);
        this.color = color;
        this.hp.setBackground(color);
    }
    //default constructor
    public HealthBar(int x, int y, int width, int height, float percent, String labelText, int textAlign) {
        this.setBounds(x,y,width,height);
        loadBackgroundImage();
        loadHpImage();
        this.setLayout(null);
        this.setOpaque(false);
        this.percent = percent;
        this.width = width;
        this.height = height;

        label = new JLabel(labelText);
        label.setBounds(0,0,width,height/2);
        label.setHorizontalAlignment(textAlign);
        label.setVerticalAlignment(SwingConstants.BOTTOM);
        label.setForeground(Color.white);
        this.add(label);

        panel.setBackground(Color.black);
        panel.setOpaque(false);
        panel.setBounds(0,height/2, width,height/2);

        hp = new JPanel();
        hp.setBackground(color);
        hp.setBounds(0,height/2, (int) (width*this.percent),height);
        hp.setOpaque(false);

        this.add(hp);
        this.add(panel);
    }

    /** sets how much of the bar is coloured red
     *
     * @param percent
     *              value between 0-1
     */
    public void setPercent(float percent) {
        assert percent <= 1 && percent >= 0;
        this.percent = percent;
        hp.setBounds(0+2,(height/2)+2, (int) ((width*this.percent)-4),(height/2)-4);
    }

    public void setColor(Color color) {
        this.color = color;
        this.hp.setBackground(color);
    }

    private void loadBackgroundImage() {
        try {
            bgImg = ImageIO.read(getClass().getResourceAsStream("imageAssets/HpBar.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }
    private void loadHpImage() {
        try {
            hpImg = ImageIO.read(getClass().getResourceAsStream("imageAssets/HpRed.png"));
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
            Image scaledImage = bgImg.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, panel.getX(), panel.getY(), null);
        }
        if (hpImg != null) {
            // Scale the image to fit the panel dimensions
            Image scaledImage = hpImg.getScaledInstance(hp.getWidth(), hp.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, hp.getX(), hp.getY(), null);
        }
    }
}
