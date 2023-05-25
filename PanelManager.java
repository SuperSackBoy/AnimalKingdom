/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Controls everything to do with the visualization of the game
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class PanelManager {
    public static Board board;
    public static PlayerDropLocation[] dropLocations = new PlayerDropLocation[5];
    public static AIDropLocation[] aiDropLocations = new AIDropLocation[5];
    public static Point mouse;

    public static int CardWidth = 96;
    public static int CardHeight = 144;

    public static int ScreenWidth = 900;
    public static int ScreenHeight = 675;

    public static int center;
    public static int spacing;
    public static int cardY;

    public static Player player;
    public static JFrame frame;
    public static AIBase ai;

    public static void init(JFrame jframe, Player plyr, AIBase aiBase) {
        player = plyr;
        frame = jframe;
        ai = aiBase;
    }

    public static void start() {
        System.out.println(mainMenu.debugCode);
        CardDeck.deckReset();
        frame.getContentPane().removeAll();

        center = frame.getContentPane().getWidth()/2-CardWidth/2;
        cardY = frame.getContentPane().getHeight()-CardHeight;
        while(center <= 0 || cardY <= 0) { //brute force fix for starting off screen
            center = frame.getContentPane().getWidth()/2-CardWidth/2;
            cardY = frame.getContentPane().getHeight()-CardHeight;
        }
        spacing = 10;

        createHud(frame);

        board = new Board();
        frame.add(board);



        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mouseHandle();
                for(Component c : board.getComponents()) {
                    if (c instanceof CardInterface) {
                        ((CardInterface) c).update();
                    }
                }
                /*
                for(PlayerCardPanel c : player.PlayerHand) {
                    if(c!=null) c.update();
                }
                for(PlayerCardPanel c : player.PlayerPlayedCards) {
                    if(c!=null) c.update();
                }
                 */
                for(PlayerDropLocation d : dropLocations) {
                    d.setBounds(d.x, d.y, d.width, d.height);
                }
                VPDisplay.setText("VP: " + player.getVP());
                playerHPBar.setPercent((float) player.getHP() / (float) player.getMaxHP());
                AIHPBar.setPercent((float) ai.getHP() / (float) player.getMaxHP());
            }

        }, 0, 1);
        frame.requestFocus();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 32) {//space
                    board.move();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        board.setBounds(board.x,(int) board.y,board.width,board.height);
    }
    public static HealthBar playerHPBar;
    public static HealthBar AIHPBar;
    public static JLabel VPDisplay = new JLabel();;
    public static void createHud(JFrame frame) {
        playerHPBar = new HealthBar(0,0,150, 65, 1f, "player", SwingConstants.LEFT);
        frame.add(playerHPBar);

        AIHPBar = new HealthBar(0,75,150, 65, 1f, "opponent", SwingConstants.RIGHT);
        frame.add(AIHPBar);

        JButton button = new JButton("End Turn");
        button.setBounds(0,150,150,65);
        button.addActionListener(e -> endTurn());
        button.setFocusable(false);
        frame.add(button);

        VPDisplay.setBounds(0,300,200,80);
        VPDisplay.setForeground(Color.white);
        frame.add(VPDisplay);


    }

    public static void endTurn() {
        //TODO code here :)
        System.out.println("END TURN");
        board.moveUp();
        player.resetVP();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int x;
            @Override
            public void run() {
                if(player.PlayerPlayedCards[x] != null )
                    player.PlayerPlayedCards[x].attack();
                x++;
                if(x > player.PlayerPlayedCards.length-1) {
                    //board.moveDown();
                    this.cancel();
                }
            }
        },200,100);
        ai.playAI();

    }

    public static void mouseHandle() {
        mouse = board.getMousePosition();
    }

    //linear interpolation
    public static double lerp(double from, double to, double p) {
        assert p >= 0 && p <= 1 : "interpolation position out of range";
        return from + (to - from) * p;
    }
}
