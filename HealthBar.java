/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Displays a health bar, percent is a number from 0-1
*/

import javax.swing.*;
import java.awt.*;

public class HealthBar extends JPanel {

    public JLabel label;
    private float percent;
    private Color color = Color.RED;
    private final JPanel hp;
    private final int height;
    private final int width;
    //constructor for changing the color
    public HealthBar(int x, int y, int width, int height, float percent, String labelText, int textAlign, Color color) {
        this(x,y,width,height,percent,labelText,textAlign);
        this.color = color;
        this.hp.setBackground(color);
    }
    //default constructor
    public HealthBar(int x, int y, int width, int height, float percent, String labelText, int textAlign) {
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setOpaque(false);
        this.percent = percent;
        this.width = width;
        this.height = height;

        label = new JLabel(labelText);
        label.setBounds(0,0,width,height/2);
        label.setHorizontalAlignment(textAlign);
        label.setVerticalAlignment(SwingConstants.BOTTOM);
        label.setForeground(Color.white);
        this.add(label);


        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setBounds(0,height/2, width,height);

        hp = new JPanel();
        hp.setBackground(color);
        hp.setBounds(0,height/2, (int) (width*this.percent),height);

        this.add(hp);
        this.add(panel);
    }

    /** sets how much of the bar is coloured red
     *
     * @param percent
     *              value between 0-1
     */
    public void setPercent(float percent) {
        assert percent <= 1 && percent >= 0;
        this.percent = percent;
        hp.setBounds(0,height/2, (int) (width*this.percent),height);
    }

    public void setColor(Color color) {
        this.color = color;
        this.hp.setBackground(color);
    }
}
