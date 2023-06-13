package src;/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Just a frame to put everything on, nothing special
*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameFrame extends JFrame {
    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        setBounds(0, 0, PanelManager.ScreenWidth, PanelManager.ScreenHeight);
        this.setTitle("Animal Kingdom: Card Arena");
        this.setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setResizable(false);

        setContentPane(contentPane);

        this.addKeyListener(new KeyListener() { //code for moving the board when space is pressed
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 32) {//space
                    if(PanelManager.board != null) PanelManager.board.move();
                }
            }
            public void keyTyped(KeyEvent e) {} public void keyReleased(KeyEvent e) {}
        });
    }

}
