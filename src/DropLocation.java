package src;/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Displays locations where cards can be placed
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class DropLocation extends JPanel {
    private BufferedImage bgImg; //image variable
    public int x;
    public int y;
    public int width = PanelManager.CardWidth;
    public int height = PanelManager.CardHeight;
    public DropLocation(int x, int y) {
        loadBackgroundImage();
        this.x = x;
        this.y = y;

        this.setBounds(x, y, width, height); //draws an ugly square where the player can put cards
        this.setOpaque(false);
    }

    private void loadBackgroundImage() {
        try {
            bgImg = ImageIO.read(getClass().getResourceAsStream("imageAssets/DropCard.png"));
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
