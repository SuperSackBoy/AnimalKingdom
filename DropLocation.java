/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Displays locations where cards can be placed
*/

import javax.swing.*;
import java.awt.*;

public class DropLocation extends JPanel {
    public int x;
    public int y;
    public int width = PanelManager.CardWidth;
    public int height = PanelManager.CardHeight;
    public DropLocation(int x, int y) {
        this.x = x;
        this.y = y;

        this.setBounds(x, y, width, height);
        this.setBorder(BorderFactory.createDashedBorder(Color.blue, 2, 2));
        this.setBackground(Color.green);
    }
}
