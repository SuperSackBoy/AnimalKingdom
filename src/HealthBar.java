package src;/*
Aaron Kimbel & Luca Mazzotta
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
    private final JPanel hp;
    private final int height;
    private final int width;

    //default constructor
    public HealthBar(int x, int y, int width, int height, float percent, String labelText, int textAlign) {
        this.setBounds(x,y,width,height);
        //loadBackgroundImage();
        //loadHpImage();
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
        hp.setBounds(0,height/2, (int) (width*this.percent),height);
        hp.setOpaque(false);

        this.add(hp);
        this.add(panel);
        GraphicsComponents.loadBackgroundImage("src/imageAssets/HpBar.png", panel);
        GraphicsComponents.loadBackgroundImage("src/imageAssets/HpRed.png", hp);
    }

    private final float threshold = 0.035f;

    /** sets how much of the bar is coloured red
     *
     * @param percent
     *              value between 0-1
     */
    public void setPercent(float percent) {
        assert percent <= 1 && percent >= 0;
        this.percent = percent;
        if(this.percent >= threshold) {
            hp.setBounds(2, (height / 2) + 2, (int) ((width * this.percent) - 4), (height / 2) - 4);
        } else {
            hp.setVisible(false);
        }
    }

    /*private void loadBackgroundImage() {
        try {
            bgImg = ImageIO.read(getClass().getResourceAsStream("imageAssets/HpBar.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }
    /*private void loadHpImage() {
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
     *
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImg != null) {
            // Scale the image to fit the panel dimensions
            Image scaledImage = bgImg.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, panel.getX(), panel.getY(), null);
        }
        if (hpImg != null && this.percent >= threshold) {
            // Scale the image to fit the panel dimensions
            Image scaledImage = hpImg.getScaledInstance(hp.getWidth(), hp.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, hp.getX(), hp.getY(), null);
        }
    }*/
}
