package src.networking;

import src.*;

import java.util.Arrays;

public class NetworkPlayer extends AIBase {
    NetworkMenu networkMenu = Main.networkMenu;
    @Override
    public void playAI() {
        Card[] cards;
        if(networkMenu.isHost) {
            networkMenu.serverNetworkHandler.sendCards();
            cards = networkMenu.serverNetworkHandler.receiveCards();
        } else {
            if(!(Arrays.equals(PanelManager.player.PlayerPlayedCards, new PlayerCardPanel[]{null, null, null, null, null}) && PanelManager.player.getHP() == PanelManager.player.getMaxHP())) networkMenu.clientNetworkHandler.sendCards();
            cards = networkMenu.clientNetworkHandler.receiveCards();
        }
        for(int x = 0; x < cards.length; x++) {
            if(cards[x] != null) {
                AICardManager.playCard(cards[x],x);
            }
        }
        attack();
    }
}
