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
        this.setLayout(null);
        GraphicsComponents.loadBackgroundImage("src/imageAssets/TitleScreen1.png", this);//sets panel to background image

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
        helpBox.setText(" How to Play:\n 1. To win the game; bring your opponent’s health to 0.\n" +
                "\n 2. You get 5 value points at the start of your turn. They are used to play cards. If you run out of value points, you can't play cards that turn.\n" +
                "\n 3. There are 5 spaces on the field that you can drag your cards to. If you run out of field space, you can't play cards that turn.\n" +
                "\n 4. Once a card is played, another is drawn from the deck to your hand.\n" +
                "\n 5. Cards will attack whatever is in front of them when the turn ends.\n" +
                "\n 6. When a card has nothing in front of it, it attacks the opponent. When a card has a card in front of it, it attacks the card.\n" +
                "\n 7. If a card runs out of health, it dies and is discarded from the table. The remaining damage that would have been dealt to the card gets dealt to the card's owner instead.");
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
        GraphicsComponents.buttonImageLoader(startButton);
        startButton.addMouseListener(GraphicsComponents.buttonGrow(startButton));
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
        GraphicsComponents.buttonImageLoader(quitButton);
        quitButton.addMouseListener(GraphicsComponents.buttonGrow(quitButton));
        //Action listener when quit button is pressed
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setText("QUIT");
        quitButton.setFont(pixelFont);
        quitButton.setBorderPainted(false);
        this.add(quitButton);

        //help button setup
        helpButton.setBounds((PanelManager.ScreenWidth/2) - (250/2) +150,(PanelManager.ScreenHeight/4*3)+30,250,60);
        GraphicsComponents.buttonImageLoader(helpButton);
        helpButton.addMouseListener(GraphicsComponents.buttonGrow(helpButton));
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
        GraphicsComponents.buttonImageLoader(multiplayerButton);
        multiplayerButton.addMouseListener(GraphicsComponents.buttonGrow(multiplayerButton));
        multiplayerButton.setFont(pixelFont);
        multiplayerButton.setBorderPainted(false);
        this.add(multiplayerButton);

    }
}