package src.networking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class EnterIP extends JPanel {
    protected JTextField text = new JTextField();
    protected JLabel label = new JLabel("ENTER PORT:");

    public EnterIP(int x, int y, int width, int height, String defaultText) {
        this(x,y,width,height);
        text.setText(defaultText);
    }
    public EnterIP(int x, int y, int width, int height) {
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        label.setBounds(0,0,width,height/2);
        text.setBounds(0,height/2,width,height/2);
        this.add(text); this.add(label);
    }

    public String getIP() {
        if(text.getText().length() == 0) {
            showWarn();
            return null;
        } else {
            return text.getText();
        }
    }

    private void showWarn() {
        String original = label.getText();
        label.setText("Enter an IP!");
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