/*
Luca Mazzotta
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Main menu panel that opens on program launch
*/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class mainMenu extends JPanel {
    private BufferedImage bgImg; //image variable
    private JButton startButton = new JButton(); //start button initializer
    private JButton quitButton = new JButton(); //Quit button initializer
    private JTextField debugBox = new JTextField(); //debug box initializer
    private Font newFont = new Font(Font.DIALOG, Font.BOLD, 30); //button font

    /**
     * Main Menu Constructor
     */
    public mainMenu() {
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
            }
        });

        loadBackgroundImage();//sets panel to background image
        this.setLayout(null);

        //Debug box setup
        debugBox.setBounds((PanelManager.ScreenWidth/2) - (125), 320, 250, 50);
        debugBox.setText("Debug Box");
        debugBox.setVisible(false);
        this.add(debugBox);

        //Start button setup
        startButton.setBounds((PanelManager.ScreenWidth/2) - (125) -180, 400, 250, 120);
        startButton.addMouseListener(buttonGrow(startButton));
        startButton.setBackground(Color.white);
        //Action listener when button is pressed
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager.start();
            }
        });
        startButton.setText("Start");
        startButton.setFont(newFont);
        this.add(startButton);

        //quit button setup
        quitButton.setBounds((PanelManager.ScreenWidth/2) - (125) +180, 400, 250, 120);
        quitButton.addMouseListener(buttonGrow(quitButton));
        quitButton.setBackground(Color.white);
        //Action listener when quit button is pressed
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        quitButton.setText("Quit");
        quitButton.setFont(newFont);
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