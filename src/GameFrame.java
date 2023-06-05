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


public class GameFrame extends JFrame /*implements KeyListener*/ {
    //private ArrayList<String> validCodes = new ArrayList<String> (Arrays.asList("Mr.Jone"));
    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        setBounds(0, 0, PanelManager.ScreenWidth, PanelManager.ScreenHeight);
        this.setTitle("Animal Kingdom: Card Arena");
        this.setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //addKeyListener(this);
        this.setResizable(false);

        setContentPane(contentPane);

    }
    /*public void keyPressed(KeyEvent event) {
        int letter = event.getKeyCode();
        if(letter == 67) {
            String cheatCode = (String)JOptionPane.showInputDialog(this,"", "Debug Console",JOptionPane.PLAIN_MESSAGE,null, null, null);
            for(int index=0; index<validCodes.size(); index=index+1) {
                String code = validCodes.get(index);
                if(cheatCode.compareTo(code)==0) {
                    //do something with the cheat code
                }
                else {
                    //nothing changes because the code is invalid
                }

            }
        }

    }
    public void keyReleased(KeyEvent event) {
        //not implemented
    }
    public void keyTyped(KeyEvent event) {
        //not implemented
    }*/


}
