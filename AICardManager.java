import java.awt.*;

public class AICardManager {
    public static AICardPanel[] AIPlayed = new AICardPanel[5]; //TODO replace this

    public static void playCard(Card cardIn, int dropIndex) {
        AICardPanel card = new AICardPanel(cardIn);
        AICardManager.AIPlayed[dropIndex] = card;
        AIDropLocation drop = PanelManager.aiDropLocations[dropIndex];
        Point point = new Point(drop.x,drop.y);
        card.smoothTransition(point);
    }
}
