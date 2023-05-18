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

public class LossPanel extends JFrame {

    private JPanel contentPane;
    private JButton RestartButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LossPanel frame = new LossPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LossPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 530, 280);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Sorry,");
        lblNewLabel.setForeground(new Color(204, 51, 0));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
        lblNewLabel.setBounds(210, 52, 95, 45);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Better luck next time!");
        lblNewLabel_1.setForeground(new Color(204, 51, 51));
        lblNewLabel_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
        lblNewLabel_1.setBounds(123, 89, 250, 47);
        contentPane.add(lblNewLabel_1);

        JButton QuitButton = new JButton("Quit Game");
        QuitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        QuitButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        QuitButton.setBounds(10, 201, 119, 29);
        contentPane.add(QuitButton);

        RestartButton = new JButton("Restart");
        RestartButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        RestartButton.setBounds(409, 201, 95, 29);
        contentPane.add(RestartButton);
    }

}
