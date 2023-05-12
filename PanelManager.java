import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class PanelManager {
    public static Board board;
    public static PlayerCardPanel[] PlayerHand = new PlayerCardPanel[5];
    public static PlayerCardPanel[] PlayerPlayedCards = new PlayerCardPanel[5];
    public static DropLocation[] dropLocations = new DropLocation[5];
    public static Point mouse;

    public static int CardWidth = 64;
    public static int CardHeight = 96;

    public static int ScreenWidth = 600;
    public static int ScreenHeight = 450;

    public static int center;
    public static int spacing;
    public static int cardY;

    public static void start(JFrame frame) {
        center = frame.getContentPane().getWidth()/2-CardWidth/2;
        cardY = frame.getContentPane().getHeight()-CardHeight;
        while(center <= 0 || cardY <= 0) { //brute force fix for starting off screen
            center = frame.getContentPane().getWidth()/2-CardWidth/2;
            cardY = frame.getContentPane().getHeight()-CardHeight;
        }
        System.out.println(cardY);
        spacing = 10;

        board = new Board();
        frame.add(board);

        PlayerHand[0].setBackground(Color.cyan);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mouseHandle();
                for(PlayerCardPanel c : PlayerHand) {
                    if(c!=null) c.update();
                }
                for(PlayerCardPanel c : PlayerPlayedCards) {
                    if(c!=null) c.update();
                }
                for(DropLocation d : dropLocations) {
                    d.setBounds(d.x, d.y, d.width, d.height);
                }
            }

        }, 0, 1);
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
        board.setBounds(board.x,board.y,board.width,board.height);
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
