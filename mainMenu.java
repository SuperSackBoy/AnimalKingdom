import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class mainMenu extends JPanel{
    public int x = 0;
    public int y = -PanelManager.ScreenHeight;
    public int width = PanelManager.ScreenWidth;
    public int height = PanelManager.ScreenHeight*2;
    public JButton startButton = new JButton();
    ImageIcon bgImg = new ImageIcon("imageAssets/TitleScreen1.png");
    private JPanel mainMenuPanel = new JPanel()
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(bgImg.getImage(), 0, 0, null);
        }
    };
    private JButton button1;

    public mainMenu() {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(ColorUIResource.red);

        startButton.setBounds(10, 10, 30, 50);
        this.add(startButton);
    }
}
