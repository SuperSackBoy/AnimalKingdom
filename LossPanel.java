import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LossPanel extends JPanel {


    private JButton RestartButton;

    /**
     * Create the frame.
     */
    public LossPanel() {
        setBounds(10, 10, 865, 615);
        this.setBackground(new Color(255, 255, 255));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.setLayout(null);

        JLabel lblNewLabel = new JLabel("Sorry,");
        lblNewLabel.setForeground(new Color(204, 51, 0));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel.setBounds(425, 200, 100, 100);
        this.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("better luck next time!");
        lblNewLabel_1.setForeground(new Color(204, 51, 51));
        lblNewLabel_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblNewLabel_1.setBounds(335, 255, 300, 47);
        this.add(lblNewLabel_1);

        JButton QuitButton = new JButton("Quit Game");
        QuitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        QuitButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        QuitButton.setBounds(10, 550, 120, 29);
        this.add(QuitButton);

        RestartButton = new JButton("Restart");
        RestartButton.addActionListener(new ActionListener() {
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






                }
            });

        RestartButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        RestartButton.setBounds(750, 550, 95, 29);
        this.add(RestartButton);
    }

}
