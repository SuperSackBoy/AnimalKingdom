/*
Aaron Kimbel
ICS4U0-C
Final Project
Animal Kingdom: Card Arena
Set up the connection between host/client
*/

package src.networking;

import src.GraphicsComponents;
import src.Main;
import src.PanelManager;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.*;

public class NetworkMenu extends JPanel{
    private BufferedImage bgImg; //image variable
    public ServerNetworkHandler serverNetworkHandler;
    public ClientNetworkHandler clientNetworkHandler;
    public boolean isHost = true;
    public void init() {
        JFrame frame = Main.frame;
        frame.getContentPane().removeAll();
        frame.add(this);
        this.setLayout(null);
        this.setBounds(0,0, PanelManager.ScreenWidth, PanelManager.ScreenHeight);

        EnterPort enterPort = new EnterPort(0,100,100,50,7777);

        JButton btnHost = new JButton("Host");
        //Start button setup
        btnHost.setBounds(10, 10, 100, 55);
        GraphicsComponents.buttonImageLoader(btnHost);
        btnHost.addMouseListener(GraphicsComponents.buttonGrow(btnHost));
        btnHost.setBorderPainted(false);
        btnHost.setFont(GraphicsComponents.pixelFontMid);

        JButton btnJoin = new JButton("Join");
        btnJoin.setBounds(10, 75, 100, 55);
        GraphicsComponents.buttonImageLoader(btnJoin);
        btnJoin.addMouseListener(GraphicsComponents.buttonGrow(btnJoin));
        btnJoin.setBorderPainted(false);
        btnJoin.setFont(GraphicsComponents.pixelFontMid);

        btnHost.addActionListener(e -> {
            this.removeAll();
            JButton btnStart = new JButton("START");
            btnStart.setBounds(0,50,100,50);
            btnStart.addActionListener(e2 -> {
                int port = enterPort.getPort();
                if(port != -1) {
                    this.removeAll();
                    JLabel waiting = new JLabel("WAITING FOR CONNECTION");
                    waiting.setBounds(0,70,300,100);
                    waiting.setVerticalAlignment(JLabel.TOP);
                    this.add(waiting);
                    try {
                        JLabel ip = new JLabel("YOUR LOCAL IP: " + InetAddress.getLocalHost().getHostAddress());
                        ip.setBounds(0, 100, 300, 100);
                        ip.setVerticalAlignment(JLabel.TOP);
                        this.add(ip);
                    } catch (UnknownHostException ignored) {}
                    serverNetworkHandler = new ServerNetworkHandler(port);
                    serverNetworkHandler.start();
                    this.isHost = true;
                }
            });
            this.add(enterPort); this.add(btnStart);
        });

        btnJoin.addActionListener(e -> {
            this.removeAll();
            try {
                InetAddress localHost = Inet4Address.getLocalHost();
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
                System.out.println(networkInterface.getInterfaceAddresses().get(1).getNetworkPrefixLength());
            } catch (SocketException | UnknownHostException ignored) {}
            EnterIP enterIP = new EnterIP(0,150,100,50,"localhost");
            JButton btnConnect = new JButton("CONNECT");
            btnConnect.setBounds(0,50,100,50);
            btnConnect.addActionListener(e2 -> {
                int port = enterPort.getPort();
                if(port != -1) {
                    String ip = enterIP.getIP();
                    if(ip != null) {
                        clientNetworkHandler = new ClientNetworkHandler(ip,port);
                        clientNetworkHandler.start();
                        this.isHost = false;
                    }
                }
            });
            this.add(enterIP); this.add(enterPort); this.add(btnConnect);
        });


        this.add(btnHost); this.add(btnJoin);
    }
}
