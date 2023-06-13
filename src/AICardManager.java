/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Plays cards for the AI
*/

package src;

import java.awt.*;

public class AICardManager {
    public static AICardPanel[] AIPlayed = new AICardPanel[5];
    public static void playCard(Card cardIn, int dropIndex) {
        if(AICardManager.AIPlayed[dropIndex] == null) { //make sure the destination drop location is empty
            AICardPanel card = PanelManager.board.createAICard(PanelManager.center, -60, cardIn); //create the card
            AICardManager.AIPlayed[dropIndex] = card; //add the card to the array
            AIDropLocation drop = PanelManager.aiDropLocations[dropIndex]; //get the droplocation
            Point point = new Point(drop.x, drop.y); //move the card
            card.smoothTransition(point);
        }
    }
}
