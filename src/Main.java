/*
Aaron Kimbel, Mateo Adebowale, Luca Mazzotta, Rudra Barot
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Main class
*/
package src;

import src.networking.NetworkMenu;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static mainMenu mainMenuPanel;
    public static GameFrame frame = new GameFrame();
    public static WinPanel Wframe = new WinPanel();
    public static GraphicsComponents graphicsComponents;
    public static NetworkMenu networkMenu = new NetworkMenu();
    public static void main(String[] args) {
        Player player = new Player();
        frame.setVisible(true);

        graphicsComponents = new GraphicsComponents();
        mainMenuPanel = new mainMenu();
        Wframe.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Set panel size to match frame
        frame.add(mainMenuPanel);

        PanelManager.init(frame,player);

        Timer timer = new Timer();
        //this is very stupid and im acknowledging that and doing it regardless, it works.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                frame.repaint();
                frame.revalidate();
            }
        }, 1, 1);
    }
}
