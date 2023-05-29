/*
Luca Mazzotta
ICS4U0-C
main Menu panel
Main menu panel that opens on program launch
May 15, 2023
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class mainMenu extends JPanel {

    private BufferedImage bgImg; //image variable
    ImageIcon icon = new ImageIcon("imageAssets/ButtonIcon.png");
    private JButton startButton = new JButton(icon); //start button initializer
    private JButton quitButton = new JButton(icon); //Quit button initializer
    private JTextField debugBox = new JTextField(); //debug box initializer
    private JButton debugAccept = new JButton();
    private Font pixelFont;
    private Font minecraft;
    public static String debugCode = "";

    /**
     * Main Menu Constructor
     */
    public mainMenu() {
        try{
            // load a custom font in your project folder
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("imageAssets/BigPixelFont.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("imageAssets/BigPixelFont.ttf")));
            minecraft = Font.createFont(Font.TRUETYPE_FONT, new File("imageAssets/Minecraft.ttf")).deriveFont(20f);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("imageAssets/Minecraft.ttf")));
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
                debugCode = debugBox.getText();
                debugBox.setText("");
                debugCodes.useCodes(debugCode);
            }
        });
        debugAccept.setVisible(false);
        this.add(debugAccept);

        //Start button setup
        startButton.setBounds((PanelManager.ScreenWidth/2) - (125) -180, 400, 250, 120);
        startButton.addMouseListener(buttonGrow(startButton));
        startButton.setBackground(Color.white);
        //Action listener when button is pressed
        startButton.addActionListener(e -> PanelManager.start());
        startButton.setText("START");
        startButton.setFont(pixelFont);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        this.add(startButton);

        //quit button setup
        quitButton.setBounds((PanelManager.ScreenWidth/2) - (125) +180, 400, 250, 120);
        quitButton.addMouseListener(buttonGrow(quitButton));
        quitButton.setBackground(Color.white);
        //Action listener when quit button is pressed
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setText("QUIT");
        quitButton.setFont(pixelFont);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.setFocusPainted(false);
        this.add(quitButton);
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
    public MouseListener buttonGrow(JButton button) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBounds(button.getX() - 5, button.getY() - 5, button.getWidth() + 10, button.getHeight() + 10);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBounds(button.getX() + 5, button.getY() + 5, button.getWidth() - 10, button.getHeight() - 10);
            }
        };
    }
}