import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class WinPanel extends JPanel {

    /**
     * Create the frame.
     */
    public WinPanel() {
        setBounds(100, 100, 532, 280);
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(64, 64, 64));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.setLayout(null);

        JLabel CongratsLabel = new JLabel("Congratulations,");
        CongratsLabel.setForeground(new Color(0, 153, 0));
        CongratsLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
        CongratsLabel.setBounds(145, 48, 205, 68);
        this.add(CongratsLabel);

        JButton QuitButton = new JButton("Quit Game");
        QuitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(EXIT_ON_CLOSE);//close panel
            }
        });
        QuitButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        QuitButton.setBounds(10, 191, 140, 39);
        this.add(QuitButton);

        JButton Restartbutton = new JButton("Restart");
        Restartbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           AIBase.resetHP();
           Player.resetHP();
           Player.resetVP();

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




           //dispose();

            }
        });
        Restartbutton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        Restartbutton.setBounds(376, 191, 130, 39);
        this.add(Restartbutton);

        JLabel lblNewLabel = new JLabel("You have won!");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
        lblNewLabel.setForeground(new Color(0, 153, 51));
        lblNewLabel.setBounds(155, 98, 242, 39);
        this.add(lblNewLabel);
    }
}
