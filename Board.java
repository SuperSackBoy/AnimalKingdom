/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
*/

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class Board extends JPanel{
    public int x = 0;
    public int y = -PanelManager.ScreenHeight;
    public int width = PanelManager.ScreenWidth;
    public int height = PanelManager.ScreenHeight*2;

    public Board() {
        this.setLayout(null);
        this.setBounds(x,y,width,height);

        drawNewCard(0);
        drawNewCard(1);
        drawNewCard(2);
        drawNewCard(3);
        drawNewCard(4);

        createDrop(PanelManager.center-PanelManager.CardWidth*2-PanelManager.spacing*2,PanelManager.cardY+PanelManager.ScreenHeight/3,0);
        createDrop(PanelManager.center-PanelManager.CardWidth-PanelManager.spacing,PanelManager.cardY+PanelManager.ScreenHeight/3,1);
        createDrop(PanelManager.center,PanelManager.cardY+PanelManager.ScreenHeight/3,2);
        createDrop(PanelManager.center+PanelManager.CardWidth+PanelManager.spacing,PanelManager.cardY+PanelManager.ScreenHeight/3,3);
        createDrop(PanelManager.center+PanelManager.CardWidth*2+PanelManager.spacing*2,PanelManager.cardY+PanelManager.ScreenHeight/3,4);

		/*
		//TODO these should be fake drop points, not real ones
		this.add(createDrop(PanelManager.center-PanelManager.CardWidth*2-PanelManager.spacing*2,PanelManager.cardY-PanelManager.cardY/16));
		this.add(createDrop(PanelManager.center-PanelManager.CardWidth-PanelManager.spacing,PanelManager.cardY-PanelManager.cardY/16));
		this.add(createDrop(PanelManager.center,PanelManager.cardY-PanelManager.cardY/16));
		this.add(createDrop(PanelManager.center+PanelManager.CardWidth+PanelManager.spacing,PanelManager.cardY-PanelManager.cardY/16));
		this.add(createDrop(PanelManager.center+PanelManager.CardWidth*2+PanelManager.spacing*2,PanelManager.cardY-PanelManager.cardY/16));
		*/
    }
    public PlayerCardPanel createCard(int x, int y, int pos) {
        PlayerCardPanel c = new PlayerCardPanel();
        c.setX(x);
        c.setY(y);
        PanelManager.PlayerHand[pos] = c;
        return c;
    }
    public DropLocation createDrop(int x, int y, int pos) {
        DropLocation d = new DropLocation(x,y,pos);
        PanelManager.dropLocations[pos] = d;
        this.add(d);
        this.setComponentZOrder(d, 5);
        return d;
    }

    public PlayerCardPanel drawNewCard(int pos) {
        assert pos >= 0 && pos <=4;
        int x = 0;
        switch(pos) {
            case 0:
                x = PanelManager.center-PanelManager.CardWidth*2-PanelManager.spacing*2;
                break;
            case 1:
                x = PanelManager.center-PanelManager.CardWidth-PanelManager.spacing;
                break;
            case 2:
                x = PanelManager.center;
                break;
            case 3:
                x = PanelManager.center+PanelManager.CardWidth+PanelManager.spacing;
                break;
            case 4:
                x = PanelManager.center+PanelManager.CardWidth*2+PanelManager.spacing*2;
                break;
        }
        PlayerCardPanel c = createCard(x,PanelManager.cardY+PanelManager.ScreenHeight+PanelManager.CardHeight, pos);
        this.add(c);
        this.setComponentZOrder(c,0);
        c.smoothTransition(new Point(c.x,c.y-PanelManager.CardHeight));
        return c;
    }



    public boolean showHand = false;
    int destination = 0;
    public void move() { //the board to show either the players hand or the play area
        Timer timer = new Timer();

        if(showHand) destination = -PanelManager.ScreenHeight; //set destination to hand
        else destination = -PanelManager.ScreenHeight/2; //set destination to board

        timer.scheduleAtFixedRate(new TimerTask() {
            int lastY = y;
            @Override
            public void run() {
                y = (int) PanelManager.lerp(y,destination,0.1);
                if(y == lastY) { //if lastY is the same lerp is stuck due to rounding errors between int and double
                    y = destination;
                    setBounds(x,y,width,height);
                    this.cancel();
                }
                lastY = y;
                setBounds(x,y,width,height);
            }
        }, 1, 10);

        showHand = !showHand; //inverts showHand
    }

    public void moveUp() {
        if(!showHand) {
            move();
        }
    }
    public void moveDown() {
        if(showHand) {
            move();
        }
    }
}
