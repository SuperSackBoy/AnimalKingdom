import java.awt.*;

public class AICardManager {
    public static AICardPanel[] AIHand = new AICardPanel[5];
    public static AICardPanel[] AIPlayed = new AICardPanel[5];

    public static void playCard(int handIndex, int dropIndex) {
        AICardPanel card = AICardManager.AIHand[handIndex];
        AICardManager.AIHand[handIndex] = new AICardPanel(CardDeck.drawCard()); //TODO should draw a new card
        AICardManager.AIPlayed[dropIndex] = card;
        AIDropLocation drop = PanelManager.aiDropLocations[dropIndex];
        Point point = new Point(drop.x,drop.y);
        card.smoothTransition(point);
    }
}
