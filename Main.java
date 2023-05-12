import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //TODO there might be a memory leak somewhere i dont know
        GameFrame frame = new GameFrame();
        PanelManager.start(frame);
    }
}
