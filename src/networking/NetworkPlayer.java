package src.networking;

import src.*;

import java.awt.*;
import java.util.Arrays;

public class NetworkPlayer extends AIBase {
    NetworkMenu networkMenu = Main.networkMenu;
    @Override
    public void playAI() {
        Card[] cards;
        if(networkMenu.isHost) {
            if(!(Arrays.equals(PanelManager.player.PlayerPlayedCards, new PlayerCardPanel[]{null, null, null, null, null})
                    && PanelManager.player.getHP() == PanelManager.player.getMaxHP())) {
                networkMenu.serverNetworkHandler.sendCards();
            }
            cards = networkMenu.serverNetworkHandler.receiveCards();
        } else {
            if(!(Arrays.equals(PanelManager.player.PlayerPlayedCards, new PlayerCardPanel[]{null, null, null, null, null})
                    && PanelManager.player.getHP() == PanelManager.player.getMaxHP())) {
                networkMenu.clientNetworkHandler.sendCards();
            }
            cards = networkMenu.clientNetworkHandler.receiveCards();
        }
        for(int x = 0; x < cards.length; x++) {
            if(cards[x] != null) {
                AICardManager.playCard(cards[x],x);
            }
        }
        attack();
        if (getHP() <= 0) {
            EventQueue.invokeLater(() -> {
                try {
                    Main.frame.getContentPane().removeAll();
                    Main.frame.add(Main.Wframe);
                    Main.Wframe.loadBackgroundImage(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
