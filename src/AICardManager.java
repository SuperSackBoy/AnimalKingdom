package src;

import java.awt.*;

public class AICardManager {
    public static AICardPanel[] AIPlayed = new AICardPanel[5];
    public static void playCard(Card cardIn, int dropIndex) {
        if(AICardManager.AIPlayed[dropIndex] == null) {
            AICardPanel card = PanelManager.board.createAICard(PanelManager.center, -60, cardIn);
            AICardManager.AIPlayed[dropIndex] = card;
            AIDropLocation drop = PanelManager.aiDropLocations[dropIndex];
            Point point = new Point(drop.x, drop.y);
            card.smoothTransition(point);
        }
    }
}
