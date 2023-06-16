package src;/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Displays all of the cards, can be panned up or down
*/

import src.networking.NetworkMenu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Board extends JPanel{ //manages the card locations and drop locations on screen, can be panned
    private BufferedImage bgImg;
    public int x = 0;
    public float y = -PanelManager.ScreenHeight;
    public int width = PanelManager.ScreenWidth;
    public int height = PanelManager.ScreenHeight*2;

    public Board() {
        this.setLayout(null);
        this.setBounds(x,(int) y,width,height);


        drawNewCard(0, CardDeck.drawCard()); //draw cards for the player
        drawNewCard(1, CardDeck.drawCard());
        drawNewCard(2, CardDeck.drawCard());
        drawNewCard(3, CardDeck.drawCard());
        drawNewCard(4, CardDeck.drawCard());
        //creates drop locations
        createDrop(PanelManager.center-PanelManager.CardWidth*2-PanelManager.spacing*2,PanelManager.cardY+PanelManager.ScreenHeight/3,0);
        createDrop(PanelManager.center-PanelManager.CardWidth-PanelManager.spacing,PanelManager.cardY+PanelManager.ScreenHeight/3,1);
        createDrop(PanelManager.center,PanelManager.cardY+PanelManager.ScreenHeight/3,2);
        createDrop(PanelManager.center+PanelManager.CardWidth+PanelManager.spacing,PanelManager.cardY+PanelManager.ScreenHeight/3,3);
        createDrop(PanelManager.center+PanelManager.CardWidth*2+PanelManager.spacing*2,PanelManager.cardY+PanelManager.ScreenHeight/3,4);

        //creates drop locations for the AI to use
		createAIDrop(PanelManager.center-PanelManager.CardWidth*2-PanelManager.spacing*2,PanelManager.cardY-PanelManager.cardY/16,0);
		createAIDrop(PanelManager.center-PanelManager.CardWidth-PanelManager.spacing,PanelManager.cardY-PanelManager.cardY/16,1);
		createAIDrop(PanelManager.center,PanelManager.cardY-PanelManager.cardY/16,2);
		createAIDrop(PanelManager.center+PanelManager.CardWidth+PanelManager.spacing,PanelManager.cardY-PanelManager.cardY/16,3);
		createAIDrop(PanelManager.center+PanelManager.CardWidth*2+PanelManager.spacing*2,PanelManager.cardY-PanelManager.cardY/16,4);

        GraphicsComponents.loadBackgroundImage("src/imageAssets/BackgroundImageLong.png", this);//sets panel to background image
    }
    public PlayerCardPanel createCard(int x, int y, int pos, Card card) {
        PlayerCardPanel c = new PlayerCardPanel(card); //creates a new card panel
        c.setX(x); //set the x and y
        c.setY(y);
        PanelManager.player.PlayerHand[pos] = c; //add it to the players hand array
        return c;
    }

    public AICardPanel createAICard(int x, int y, Card card) {
        AICardPanel c = new AICardPanel(card); //creates a new AI card panel
        c.setX(x); //set the x and y
        c.setY(y);
        this.add(c); //add it to the panel
        this.setComponentZOrder(c,0); //set it to the front
        return c;
    }
    public PlayerDropLocation createDrop(int x, int y, int pos) {
        PlayerDropLocation d = new PlayerDropLocation(x,y,pos); //create a drop location
        PanelManager.dropLocations[pos] = d; //add it to the array
        this.add(d);
        this.setComponentZOrder(d, 5); //set it to the back
        return d;
    }

    public AIDropLocation createAIDrop(int x, int y, int pos) {
        AIDropLocation d = new AIDropLocation(x,y); //literally the same as the previous
        PanelManager.aiDropLocations[pos] = d;
        this.add(d);
        this.setComponentZOrder(d, 5);
        return d;
    }

    public PlayerCardPanel drawNewCard(int pos, Card card) {
        assert pos >= 0 && pos <=4; //forces a crash if pos is out of this range
        //get the desired x position
        int x;
        switch (pos) {
            case 0: x = PanelManager.center - PanelManager.CardWidth * 2 - PanelManager.spacing * 2; break;
            case 1: x = PanelManager.center - PanelManager.CardWidth - PanelManager.spacing; break;
            case 2: x = PanelManager.center; break;
            case 3: x = PanelManager.center + PanelManager.CardWidth + PanelManager.spacing; break;
            case 4: x = PanelManager.center + PanelManager.CardWidth * 2 + PanelManager.spacing * 2; break;
            default: x = 0; break;
        }
        //create a card
        PlayerCardPanel c = createCard(x,PanelManager.cardY+PanelManager.ScreenHeight+PanelManager.CardHeight, pos, card);
        this.add(c); //add the card to the screen
        this.setComponentZOrder(c,0); //set it to the top
        c.smoothTransition(new Point(c.x,c.y-PanelManager.CardHeight)); //smooth animations even if its never seen
        return c;
    }



    public boolean showHand = false;
    int destination = 0;
    public void move() { //the board to show either the players hand or the play area
        Timer timer = new Timer();
        PanelManager.arrowImgUpdater(false);

        if(showHand) {
            destination = -PanelManager.ScreenHeight; //set destination to hand
        }
        else {
            destination = -PanelManager.ScreenHeight/2; //set destination to board
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                y = (float) PanelManager.lerp(y,destination,0.1);
                if(showHand ? y > destination-1 : y < destination+1) { //if lastY is the same lerp is stuck due to rounding errors between int and double
                    y = destination;
                    setBounds(x,(int) y,width,height);
                    this.cancel();
                }
                setBounds(x,(int) y,width,height);
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
