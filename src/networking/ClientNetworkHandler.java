package src.networking;

import src.*;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ClientNetworkHandler extends Thread{

    private Socket connectedSocket;
    private final int port;
    private final String ip;
    private DataOutputStream dOut;

    public ClientNetworkHandler(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        try {
            connectedSocket = new Socket(ip, port);
            dOut = new DataOutputStream(connectedSocket.getOutputStream());
            PanelManager.start(false,false);
        } catch (SocketException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }

    public void closeSocket() {
        try {
            if(!connectedSocket.isClosed()) connectedSocket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }

    public void sendConnected() throws IOException {
        dOut.writeBoolean(true);
        dOut.flush();
        System.out.println("CONNECTED");
        System.out.println(connectedSocket.isConnected());
    }

    public void sendCards() {
        for(PlayerCardPanel card : PanelManager.player.PlayerPlayedCards) {
            try {
                if (card != null) {
                    dOut.writeUTF(card.getCard().getName());
                } else {
                    dOut.writeUTF("null");
                }
                dOut.flush();
            } catch (IOException ignored) {}
        }
    }

    public Card[] receiveCards() {
        Card[] cards = new Card[5];
        try {
            DataInputStream dIn = new DataInputStream(connectedSocket.getInputStream());
            for(int x = 0; x < cards.length; x++) {
                String in = dIn.readUTF();
                if(!in.equals("null")) {
                    cards[x] = CardDeck.findCard(in);
                    CardDeck.removeCard(in);
                } else {
                    cards[x] = null;
                }
            }
        } catch (IOException ignored) {}
        return cards;
    }
}
