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

public class WinPanel extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        //if (enemyHP == 0);
        //{
            EventQueue.invokeLater(new Runnable() {
                public void run() {


                    try {
                        WinPanel frame = new WinPanel();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        //}
    }

    /**
     * Create the frame.
     */
    public WinPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 532, 280);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setForeground(new Color(64, 64, 64));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel CongratsLabel = new JLabel("Congratulations,");
        CongratsLabel.setForeground(new Color(0, 153, 0));
        CongratsLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
        CongratsLabel.setBounds(145, 48, 205, 68);
        contentPane.add(CongratsLabel);

        JButton QuitButton = new JButton("Quit Game");
        QuitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(EXIT_ON_CLOSE);
            }
        });
        QuitButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        QuitButton.setBounds(10, 191, 130, 39);
        contentPane.add(QuitButton);

        JButton Restartbutton = new JButton("Restart");
        Restartbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


           dispose();

            }
        });
        Restartbutton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        Restartbutton.setBounds(376, 191, 130, 39);
        contentPane.add(Restartbutton);

        JLabel lblNewLabel = new JLabel("You have won!");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
        lblNewLabel.setForeground(new Color(0, 153, 51));
        lblNewLabel.setBounds(155, 98, 242, 39);
        contentPane.add(lblNewLabel);
    }
}
