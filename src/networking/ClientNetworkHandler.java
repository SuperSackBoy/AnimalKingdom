/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Controls Networking connections for the client
*/
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
        super(); //initialize thread stuff
        this.ip = ip; //set ip/port
        this.port = port;
    }

    public void run() {
        try {
            connectedSocket = new Socket(ip, port); //create the socket
            dOut = new DataOutputStream(connectedSocket.getOutputStream()); //create an output stream
            DataInputStream dIn = new DataInputStream(connectedSocket.getInputStream()); //read who should start
            PanelManager.start(dIn.readBoolean(),false);
        } catch (SocketException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }

    public void sendCards() { //send everything in the array across the output stream
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

    public Card[] receiveCards() { //read everything from the input stream
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
