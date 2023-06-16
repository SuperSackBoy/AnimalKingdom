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


public class DropLocation extends JPanel {
    private BufferedImage bgImg; //image variable
    public int x;
    public int y;
    public int width = PanelManager.CardWidth;
    public int height = PanelManager.CardHeight;
    public DropLocation(int x, int y) {
        this.x = x;
        this.y = y;

        this.setBounds(x, y, width, height);
        this.setOpaque(false);
        GraphicsComponents.loadBackgroundImage("src/imageAssets/DropCard.png", this);
    }
}
