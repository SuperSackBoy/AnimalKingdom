package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsComponents {


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
     *
     * @param image image path
     * @param panel panel to add to
     */
    public static void loadBackgroundImage(String image, JPanel panel) {
        ImageIcon bgImg = new ImageIcon(image);

        JLabel backGround = new JLabel("", bgImg, JLabel.CENTER);
        backGround.setBounds(0,0, PanelManager.ScreenWidth, PanelManager.ScreenHeight);
        panel.add(backGround);
    }
}
