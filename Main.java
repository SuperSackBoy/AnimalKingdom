import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static mainMenu mainMenuPanel;
    public static GameFrame frame = new GameFrame();
    public static void main(String[] args) {
        Player player = new Player();
        AIBase ai = new AIBase();
        //TODO there might be a memory leak somewhere i dont know

        frame.setVisible(true);

        mainMenuPanel = new mainMenu();
        mainMenuPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Set panel size to match frame
        frame.add(mainMenuPanel);

        PanelManager.init(frame,player,ai);

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
