package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class GraphicsComponents {
    public static Font pixelFontLarge;
    public static Font pixelFontMid;
    public static Font pixelFontSmall;

    GraphicsComponents() {
        try{
            // load a custom font in your project folder
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            pixelFontLarge = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")).deriveFont(30f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")));
            pixelFontMid = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")).deriveFont(20f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")));
            pixelFontSmall = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")).deriveFont(13f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")));
        }
        catch(IOException | FontFormatException ignored){

        }
    }

    /**
     * loads the image onto the button inputted
     * @param button
     */
    public static void buttonImageLoader(JButton button) {
        ImageIcon imageIcon = new ImageIcon(Main.class.getResource("imageAssets/ButtonIcon.png")); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(button.getWidth(), button.getHeight(),  Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        button.setIcon(imageIcon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
    }

    /**
     * Makes a button grow when highlighted to make it feel more alive
     * @param button uses the button set in parameters to grow on highlight
     */
    public static MouseListener buttonGrow(JButton button) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBounds(button.getX() - 5, button.getY() - 5, button.getWidth() + 10, button.getHeight() + 10);
                buttonImageLoader(button);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBounds(button.getX() + 5, button.getY() + 5, button.getWidth() - 10, button.getHeight() - 10);
                buttonImageLoader(button);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (button != PanelManager.endTurnButton) {
                    if (button != mainMenu.helpButton) {
                        button.setBounds(button.getX() + 5, button.getY() + 5, button.getWidth() - 10, button.getHeight() - 10);
                        buttonImageLoader(button);
                    }
                }
            }
        };
    }

    /**
     * Loads bg image onto a panel
     * @param image image path
     * @param panel panel to add to
     */
    public static void loadBackgroundImage(String image, JPanel panel) {
        ImageIcon bgImg = new ImageIcon(image);
        Image imgT = bgImg.getImage(); // transform it
        Image newimg = imgT.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_DEFAULT); // scale it the smooth way
        bgImg = new ImageIcon(newimg);  // transform it back

        JLabel backGround = new JLabel("", bgImg, JLabel.CENTER);
        backGround.setBounds(0,0, panel.getWidth(), panel.getHeight());
        panel.add(backGround);
    }
}
