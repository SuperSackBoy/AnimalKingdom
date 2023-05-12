/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
*/

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DropLocation extends JPanel{
    public int index;
    public int x;
    public int y;
    public int width = PanelManager.CardWidth;
    public int height = PanelManager.CardHeight;
    public boolean active;
    public DropLocation(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;

        this.setBounds(x, y, width, height);
        this.setBorder(BorderFactory.createDashedBorder(Color.blue, 2, 2));
        this.setBackground(Color.green);
    }
}
