/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Controls Networking connections for the server
*/

package src.networking;

import src.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Random;

public class ServerNetworkHandler extends Thread{

    private ServerSocket serverSocket;
    private Socket connectedSocket;
    private final int port;
    private DataOutputStream dOut;

    public ServerNetworkHandler(int port) {
        super(); //initialize thread
        this.port = port;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port); //create server socket
            System.out.println("waiting");
            connectedSocket = serverSocket.accept(); //wait for a player to connect
            System.out.println("CONNECTED!");
            dOut = new DataOutputStream(connectedSocket.getOutputStream()); //get an output stream
            boolean shouldStart = new Random().nextBoolean(); //tell the player if the server or client will start
            PanelManager.start(shouldStart,false);
            dOut.writeBoolean(!shouldStart);
            dOut.flush();


        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }

    public void sendCards() { //see clientnetworkhandler
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

    public Card[] receiveCards() { //see clientnetworkhandler
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
