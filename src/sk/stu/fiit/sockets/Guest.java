/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sk.stu.fiit.GUI.MainWindow;

/**
 *
 * @author matba
 */
public class Guest {

    public static final int port = 48777;
    private boolean active = true;
    private InetAddress guestIP;
    private InetAddress hostIP = null;
    private String fen;

    private Socket senderSocket;
    private Socket listenerSocket;

    private DataInputStream dis;
    private DataOutputStream dos;

    private MainWindow m;

    private final Thread senderT = new Thread() {
        @Override
        public void run() {
            send();
        }
    };
    private final Thread listenerT = new Thread() {
        @Override
        public void run() {
            listen();
        }
    };

    public Guest(MainWindow m) {
        this.m = m;
        setLocalIP(active);
    }

    /**
     * Starts executing method {@link #listen() listen()} in separate {@link
     * #listenerT thread}
     */
    public void startListener() {
        listenerT.start();
    }

    /**
     * Method to listen for incoming sockets.
     */
    public void listen() {
        try (ServerSocket ss = new ServerSocket(port, 100, hostIP);) {
            listenerSocket = ss.accept();
            while (active) {
                setFen(dis.readUTF());
                /*
                perform operations with fen
                actualize the board
                 */
//                m.test(parseMsg(msg));
            }
            listenerSocket.close();
        } catch (IOException ex) {
            System.err.println("Doplnit logger");
        }
    }

    /**
     * Starts executing method {@link #send() send()} in separate {@link
     * #senderT thread}
     */
    public void startSender() {
        senderT.start();
    }

    /**
     * Method to send string representation of board via socket. Field
     * {@link #fen fen}, which contains string representation of board, needs to
     * be set before calling this method via
     * {@link #setFen(java.lang.String) setFen(String)}.
     */
    public void send() {
//        setFen(newFen) before sending message
        try {
            senderSocket = new Socket(hostIP, port);
            dis = new DataInputStream(senderSocket.getInputStream());
            dos = new DataOutputStream(senderSocket.getOutputStream());
            dos.writeUTF(getFen());
            dos.flush();
        } catch (IOException ex) {
            System.err.println("Doplnit logger");
        }
    }

    private List parseMsg(String msg) {
        int x = Integer.parseInt(msg) % 62;
        return List.of((int) (Math.random() * 63), (int) (Math.random() * 63), (int) (Math.random() * 63), (int) (Math.random() * 63));
    }

    public final void setLocalIP(boolean localhost) {
        try (final DatagramSocket socket = new DatagramSocket()) {
            if (localhost) {
                hostIP = InetAddress.getLoopbackAddress();
            } else {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                hostIP = socket.getLocalAddress();
            }
        } catch (SocketException | UnknownHostException ex) {
            System.err.println("Doplnit logger");
        }
    }

    public InetAddress getLocalIP() {
        return guestIP;
    }

    public void setHostIP(String ip) throws UnknownHostException {
        guestIP = InetAddress.getByName(ip);
    }

    public InetAddress getHostIp() {
        return hostIP;
    }

    public void setFen(String newFen) {
        fen = newFen;
    }

    public String getFen() {
        return fen;
    }
}
