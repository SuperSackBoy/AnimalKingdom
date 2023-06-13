package src;/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Controls everything to do with the visualization of the game
*/

import src.networking.NetworkPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.Font.*;

public class PanelManager {
    public static Board board;
    public static PlayerDropLocation[] dropLocations = new PlayerDropLocation[5];
    public static AIDropLocation[] aiDropLocations = new AIDropLocation[5];
    public static Point mouse;

    public static final ImageIcon selectIcon = new ImageIcon(Main.class.getResource("imageAssets/CardSelectIcon.png"));
    public static JLabel selectLbl = new JLabel();

    public static JButton endTurnButton;

    public static JButton showhandButton;

    private static Font minecraft;

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
    private static boolean useAI;

    public static void init(JFrame jframe, Player plyr) {
        player = plyr;
        frame = jframe;
        ai = new AIBase();
    }

    public static void start(boolean playerStart, boolean AI) {
        useAI = AI;
        try{
            // load a custom font in your project folder0f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            minecraft = createFont(TRUETYPE_FONT, PanelManager.class.getResourceAsStream("imageAssets/Minecraft.ttf")).deriveFont(13f);
            ge.registerFont(createFont(TRUETYPE_FONT, PanelManager.class.getResourceAsStream("imageAssets/Minecraft.ttf")));
        }
        catch(IOException | FontFormatException ignored){

        }

        if(!useAI) ai = new NetworkPlayer();
        frame.getContentPane().removeAll();

        center = frame.getContentPane().getWidth()/2-CardWidth/2;
        cardY = frame.getContentPane().getHeight()-CardHeight;
        while(center <= 0 || cardY <= 0) { //brute force fix for starting off screen
            center = frame.getContentPane().getWidth()/2-CardWidth/2;
            cardY = frame.getContentPane().getHeight()-CardHeight;
        }
        spacing = 10;

        board = new Board();

        createHud(frame);

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

                for(PlayerDropLocation d : dropLocations) {
                    d.setBounds(d.x, d.y, d.width, d.height);
                }
                VPDisplay.setText("Value Points: " + player.getVP());
                playerHPBar.setPercent((float) player.getHP() / (float) player.getMaxHP());
                playerHPBar.label.setText("Player: "+player.getHP());
                AIHPBar.setPercent((float) ai.getHP() / (float) ai.getMaxHP());
                AIHPBar.label.setText("Opponent: "+ai.getHP());
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

        if(!playerStart) {
            board.y = (float) -ScreenHeight /2;
            board.showHand = !board.showHand;
            endTurn();
        }
        board.setBounds(board.x,(int) board.y,board.width,board.height);
        arrowImgUpdater(true);
    }
    public static HealthBar playerHPBar;
    public static HealthBar AIHPBar;
    public static JLabel VPDisplay = new JLabel();
    public static void createHud(JFrame frame) {
        playerHPBar = new HealthBar(6,25,150, 60, 1f, "Player: " +player.getHP(), SwingConstants.LEFT);
        playerHPBar.label.setFont(minecraft);
        frame.add(playerHPBar);

        AIHPBar = new HealthBar(6,75,150, 60, 1f, "Opponent: " +ai.getHP(), SwingConstants.RIGHT);
        AIHPBar.label.setFont(minecraft);
        frame.add(AIHPBar);

        selectLbl.setVisible(false);
        selectLbl.setIcon(selectIcon);
        selectLbl.setBounds(10,10,16*2,20*2);
        frame.add(selectLbl);

        endTurnButton = new JButton("End Turn");
        endTurnButton.addMouseListener(mainMenu.buttonGrow(endTurnButton));
        endTurnButton.setBounds(725,550,150,75);
        endTurnButton.addActionListener(e -> endTurn());
        endTurnButton.setFocusable(false);
        endTurnButton.setFont(minecraft);
        endTurnButton.setBorderPainted(false);
        mainMenu.buttonImageLoader(endTurnButton);
        frame.add(endTurnButton);

        JButton surrenderButton = new JButton("Surrender");
        surrenderButton.addMouseListener(mainMenu.buttonGrow(surrenderButton));
        surrenderButton.setBounds(725,15,150,75);
        surrenderButton.addActionListener(e -> surrender());
        surrenderButton.setFocusable(false);
        surrenderButton.setFont(minecraft);
        surrenderButton.setBorderPainted(false);
        mainMenu.buttonImageLoader(surrenderButton);
        frame.add(surrenderButton);

        showhandButton = new JButton();
        showhandButton.setBounds(6,550,30*3,24*3);
        arrowImgUpdater(false);
        showhandButton.setText("arrow");
        showhandButton.setFont(new Font("TimesRoman", Font.PLAIN, 1));
        showhandButton.setOpaque(false);
        showhandButton.setContentAreaFilled(false);
        showhandButton.setFocusable(false);
        showhandButton.setFocusPainted(false);
        showhandButton.setBorderPainted(false);
        showhandButton.addMouseListener(buttonGrow(showhandButton));
        showhandButton.addActionListener(e -> showhand());
        frame.add(showhandButton);

        VPDisplay.setBounds(6,125,200,80);
        VPDisplay.setFont(minecraft);
        VPDisplay.setForeground(Color.white);
        frame.add(VPDisplay);
    }

    public static void arrowImgUpdater(boolean invert){
        if (board == null) return;
        ImageIcon imageIcon;

        boolean bool = board.showHand;
        if(invert) bool = !bool;

        if (bool) imageIcon = new ImageIcon(Main.class.getResource("imageAssets/ArrowUp.png"));
        else imageIcon = new ImageIcon(Main.class.getResource("imageAssets/ArrowDown.png"));

        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(showhandButton.getWidth(), showhandButton.getHeight(),  Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        showhandButton.setIcon(imageIcon);
    }

    /**
     * Makes a button grow when highlighted to make it feel more alive
     * @param button uses the button set in parameters to grow on highlight
     */
    public static MouseListener buttonGrow(JButton button) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBounds(button.getX() - 5, button.getY() - 5, button.getWidth() + 10, button.getHeight() + 10);
                arrowImgUpdater(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBounds(button.getX() + 5, button.getY() + 5, button.getWidth() - 10, button.getHeight() - 10);
                arrowImgUpdater(true);
            }
        };
    }

    public static void surrender() {
        {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Main.frame.getContentPane().removeAll();
                        Main.frame.add(Main.Wframe);
                        Main.Wframe.loadBackgroundImage(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return; //breaks the for loop
    }


    public static void endTurn() {
        endTurnButton.setEnabled(false);
        //System.out.println("END TURN"); (Couldn't bring myself to delete this momentus line of code
        board.moveUp();
        player.setVP(0);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int x;
            @Override
            public void run() {
                if(player.PlayerPlayedCards[x] != null )
                    player.PlayerPlayedCards[x].attack();
                x++;
                if(x > player.PlayerPlayedCards.length-1) {
                    ai.playAI();
                    this.cancel();
                }
            }
        },200,100);
    }

    public static void showhand() {
        board.move();
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
