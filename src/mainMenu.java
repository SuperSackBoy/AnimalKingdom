package src;/*
Luca Mazzotta
ICS4U0-C
main Menu panel
Main menu panel that opens on program launch
May 15, 2023
*/
import src.networking.NetworkMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class mainMenu extends JPanel {

    private BufferedImage bgImg; //image variable
    public static JButton startButton = new JButton(); //start button initializer
    public static JButton quitButton = new JButton(); //Quit button initializer
    public static JButton helpButton = new JButton(); //Quit button initializer
    public static JButton xHelp = new JButton(); //Quit button initializer
    public static JTextField debugBox = new JTextField(); //debug box initializer
    private JButton debugAccept = new JButton();
    private JTextPane helpBox = new JTextPane();
    public Font pixelFont;
    public Font minecraft;
    public static String debugCode = "";

    /**
     * Main Menu Constructor
     */
    public mainMenu() {
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
        this.setFocusable(true); // Ensure the panel can receive focus
        this.requestFocusInWindow(); // Request focus for the panel
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = this.getInputMap(condition);
        ActionMap actionMap = this.getActionMap();

        // Create a key stroke for the "C" key
        KeyStroke cKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, 0);

        // Bind the key stroke to an action
        inputMap.put(cKeyStroke, "debugAction");
        actionMap.put("debugAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debugBox.setVisible(!debugBox.isVisible());//sets debug box visible
                debugAccept.setVisible(!debugAccept.isVisible());//sets debug box visible
            }
        });

        loadBackgroundImage();//sets panel to background image
        this.setLayout(null);

        //x button for the help box text
        xHelp.setBounds( PanelManager.ScreenWidth-155,105,50, 40);
        xHelp.setVerticalTextPosition(JButton.CENTER);
        xHelp.setText("X");
        xHelp.setFont(minecraft);
        xHelp.setVisible(false);
        xHelp.setBackground(Color.white);
        xHelp.setFocusPainted(false);
        xHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpBox.setVisible(false);//closes textbox
                xHelp.setVisible(false);
            }
        });
        this.add(xHelp);

        //help text box for instructions
        helpBox.setBounds(100 ,100,PanelManager.ScreenWidth-200, PanelManager.ScreenHeight-200);
        helpBox.setFont(minecraft);
        helpBox.setVisible(false);
        helpBox.setEditable(false);
        helpBox.setRequestFocusEnabled(false);
        Border border = BorderFactory.createLineBorder(Color.black, 5);
        helpBox.setBorder(border);
        helpBox.setText(" How to Play:");
        this.add(helpBox);

        //Debug box setup
        debugBox.setBounds((PanelManager.ScreenWidth/2) - (125+55), 320, 250, 50);
        debugBox.setText("Debug Box");
        debugBox.setFont(minecraft);
        debugBox.setVisible(false);
        this.add(debugBox);

        //Debug Button setup
        debugAccept.setBounds(debugBox.getX()+252, debugBox.getY(), 105, 50);
        debugAccept.setText("Enter");
        debugAccept.setFont(minecraft);
        debugAccept.setBackground(Color.white);
        debugAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debugCode = debugBox.getText();//sets the debug code to user entered text
                debugBox.setText("");//reset box
                debugCodes.useCodes(debugCode);//opens debug codes method using entered code
            }
        });
        debugAccept.setVisible(false);
        this.add(debugAccept);

        //Start button setup
        startButton.setBounds((PanelManager.ScreenWidth/2) - (125) -150, 400, 250, 120);
        buttonImageLoader(startButton);
        startButton.addMouseListener(buttonGrow(startButton));
        //Action listener when button is pressed
        startButton.addActionListener(e -> {
            Random r = new Random();
            PanelManager.start(r.nextBoolean(),true);
        });
        startButton.setText("START");
        startButton.setBorderPainted(false);
        startButton.setFont(pixelFont);
        this.add(startButton);

        //quit button setup
        quitButton.setBounds((PanelManager.ScreenWidth/2) - (125) +150, 400, 250, 120);
        buttonImageLoader(quitButton);
        quitButton.addMouseListener(buttonGrow(quitButton));
        //Action listener when quit button is pressed
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setText("QUIT");
        quitButton.setFont(pixelFont);
        quitButton.setBorderPainted(false);
        this.add(quitButton);

        //help button setup
        helpButton.setBounds((PanelManager.ScreenWidth/2) - (250/2) +150,(PanelManager.ScreenHeight/4*3)+30,250,60);
        buttonImageLoader(helpButton);
        helpButton.addMouseListener(buttonGrow(helpButton));
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpBox.setVisible(true);//opens help box
                xHelp.setVisible(true);
            }
        });
        helpButton.setText("Help");
        helpButton.setFont(pixelFont);
        helpButton.setBorderPainted(false);
        this.add(helpButton);

        //multiplayer button setup
        JButton multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.setBounds((PanelManager.ScreenWidth/2) - (250/2) -150,(PanelManager.ScreenHeight/4*3)+30,250,60);
        multiplayerButton.addActionListener(e -> Main.networkMenu.init());
        buttonImageLoader(multiplayerButton);
        multiplayerButton.addMouseListener(buttonGrow(multiplayerButton));
        multiplayerButton.setFont(pixelFont);
        multiplayerButton.setBorderPainted(false);
        this.add(multiplayerButton);

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
     * Sets bgImg to the files path
     */
    private void loadBackgroundImage() {
        try {
            bgImg = ImageIO.read(getClass().getResourceAsStream("imageAssets/TitleScreen1.png"));
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
                    if (button != helpButton) {
                        button.setBounds(button.getX() + 5, button.getY() + 5, button.getWidth() - 10, button.getHeight() - 10);
                        buttonImageLoader(button);
                    }
                }
            }
        };
    }
}