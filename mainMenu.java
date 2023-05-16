import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class mainMenu extends JPanel {
    private BufferedImage bgImg;
    private JButton startButton = new JButton();
    private JButton quitButton = new JButton();
    private JTextField debugBox = new JTextField();
    private Font newFont = new Font(Font.DIALOG, Font.BOLD, 25);

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
                if (debugBox.isVisible()) {
                    debugBox.setVisible(false);
                } else {
                    debugBox.setVisible(true);
                }
            }
        });

        loadBackgroundImage();
        setLayout(null);

        debugBox.setBounds(this.getWidth()/2+200, 190, 200, 40);
        debugBox.setText("Debug Box");
        debugBox.setVisible(false);
        this.add(debugBox);

        startButton.setBounds(this.getWidth()/2+200-120, 250, 200, 110);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO open game panel
            }
        });
        startButton.setText("Start");
        startButton.setFont(newFont);
        this.add(startButton);

        quitButton.setBounds(this.getWidth()/2+200+120, 250, 200, 110);
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

    private void loadBackgroundImage() {
        try {
            bgImg = ImageIO.read(getClass().getResourceAsStream("imageAssets/TitleScreen1.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }

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