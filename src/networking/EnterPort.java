/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
A text field that can only have 4 numbers maximum
*/

package src.networking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class EnterPort extends JPanel {
    protected JTextField text = new JTextField();
    protected JLabel label = new JLabel("ENTER PORT:");

    public EnterPort(int x, int y, int width, int height , int defaultPort) {
        this(x,y,width,height);
        this.text.setText(String.valueOf(defaultPort));
    }
    public EnterPort(int x,int y,int width, int height) {
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        label.setBounds(0,0,width,height/2);
        text.setBounds(0,height/2,width,height/2);
        this.add(text); this.add(label);

        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String str = text.getText();

                if(str.length() >= 4) {
                    str = str.substring(0,3);
                    text.setText(str);
                }
                str = str + e.getKeyChar();
                try {
                    int a = Integer.parseInt(str);
                } catch (NumberFormatException exception) {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            String t = EnterPort.removeLetters(text.getText());
                            text.setText(t);
                            text.setCaretPosition(t.length());

                        }
                    },10);
                }
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
    }

    protected static String removeLetters(String in) {
        char[] numbers = new char[]{'1','2','3','4','5','6','7','8','9','0'};
        char[] chars = in.toCharArray();
        StringBuilder out = new StringBuilder();
        for (char aChar : chars) {
            for (int num : numbers) {
                if (aChar == num) {
                    out.append(aChar);
                    break;
                }
            }
        }
        return out.toString();
    }

    public int getPort() {
        if(this.text.getText().length() == 4) {
            return Integer.parseInt(this.text.getText());
        } else {
            this.showWarn();
            return -1;
        }
    }

    private void showWarn() {
        String original = label.getText();
        label.setText("Port should be a 4 digit number!");
        label.setForeground(Color.red);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                label.setText(original);
                label.setForeground(Color.black);
            }
        },1000);
    }
}
