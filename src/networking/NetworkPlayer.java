/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Alternative AI for networking
*/

package src.networking;

import src.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class NetworkPlayer extends AIBase {
    NetworkMenu networkMenu = Main.networkMenu;

    public NetworkPlayer() {
        super();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getHP() <= 0) {
                    EventQueue.invokeLater(() -> {
                        try {
                            Main.frame.getContentPane().removeAll();
                            Main.frame.add(Main.Wframe);
                            Main.Wframe.loadBackgroundImage(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        },100, 100);
    }
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
    }
}
