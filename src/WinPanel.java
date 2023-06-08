package src;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class WinPanel extends JPanel {
    private BufferedImage bgImg; //image variable
    private Font pixelFont;
    private Font minecraft;

    /**
     * Create the frame.
     */
    public WinPanel() {
        try{
            // load a custom font in your project folder
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")));
            minecraft = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")).deriveFont(20f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("imageAssets/Minecraft.ttf")));
        }
        catch(IOException | FontFormatException ignored){
        }
        this.setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(64, 64, 64));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.setLayout(null);

        //quit button setup
        JButton quitButton = new JButton();
        quitButton.setBounds((PanelManager.ScreenWidth/2) - (125) +180, 400, 250, 120);
        buttonImageLoader(quitButton);
        quitButton.addMouseListener(mainMenu.buttonGrow(quitButton));
        //Action listener when quit button is pressed
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setText("QUIT");
        quitButton.setFont(pixelFont);
        quitButton.setBorderPainted(false);
        this.add(quitButton);


        //Start button setup
        JButton Restartbutton = new JButton("RESTART");
        Restartbutton.setBounds((PanelManager.ScreenWidth/2) - (125) -180, 400, 250, 120);
        buttonImageLoader(Restartbutton);
        Restartbutton.addMouseListener(mainMenu.buttonGrow(Restartbutton));
        Restartbutton.setBorderPainted(false);
        Restartbutton.setFont(pixelFont);
        Restartbutton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           AIBase.resetHP();
           PanelManager.player.resetHP();
           PanelManager.player.resetVP();
           EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            Main.frame.getContentPane().removeAll();
                            Main.frame.add(Main.mainMenuPanel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        this.add(Restartbutton);

    }
    public static void buttonImageLoader(JButton button) {
        ImageIcon imageIcon = new ImageIcon("src/imageAssets/ButtonIcon.png"); // load the image to a imageIcon
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
     * Sets bgImg to the files path
     */
    public void loadBackgroundImage(boolean win) {
        if (win) {
            try {
                bgImg = ImageIO.read(getClass().getResourceAsStream("imageAssets/WinScreen.png"));
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception gracefully
            }
        } else {
            try {
                bgImg = ImageIO.read(getClass().getResourceAsStream("imageAssets/LossScreen.png"));
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception gracefully
            }
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
