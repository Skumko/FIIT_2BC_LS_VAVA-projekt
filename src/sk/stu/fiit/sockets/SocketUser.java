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
import static sk.stu.fiit.sockets.SocketUser.PlayerType.GUEST;
import static sk.stu.fiit.sockets.SocketUser.PlayerType.HOST;

/**
 *
 * @author matba
 */
public class SocketUser {

    public static final int port = 48777;
    private boolean active = true;
    private InetAddress myIP;
    private InetAddress opponentsIP = null;
    private String fen;

    private Socket senderSocket = null;
    private Socket socket = null;

    private DataInputStream dis;
    private DataOutputStream dos;

    private MainWindow m;

    private PlayerType type;

    public enum PlayerType {
        HOST, GUEST
    }

    private final Thread senderT = new Thread() {
        @Override
        public void run() {
            try {
                send();
            } catch (UnknownHostException ex) {
                Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    private final Thread listenerT = new Thread() {
        @Override
        public void run() {
            listen();
        }
    };

    public SocketUser(MainWindow m, PlayerType type) {
        this.m = m;
        this.type = type;
        setMyIP(false);
//        listenerT.start();
    }

//    public static void main(String[] args) {
//        Host host = new Host(new MainWindow(), true);
//        System.out.println(host.hostIP);
//        host.m.setVisible(true);
//        host.listenerT.start();
//        host.senderT.start();
//    }
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
        try (ServerSocket ss = new ServerSocket(port, 100, myIP);) {
            if (type == HOST) {
                socket = ss.accept();
                setOpponentsIP(socket.getInetAddress());
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                setFen(dis.readUTF());
                m.addMouseListeners(true);
                m.showGame();
            }
            /*
            perform operations with fen
             */
//            m.test(parseMsg(msg));
            while (active) {
                setFen(dis.readUTF());
                m.actualizeBoardFromFen(fen);
                m.addMouseListeners(m.isWhite());
                /*
                perform operations with fen
                actualize the board
                 */
            }
            socket.close();
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
     * {@link #setFen(java.lang.String) setFen(String)}. If {@link #guestIP
     * guestIP} equals null, an UnknownHostException is thrown.
     *
     * @throws UnknownHostException
     */
    public void send() throws UnknownHostException {
        if (opponentsIP == null) {
            throw new UnknownHostException("Guest IP je null");
        }
//        setFen(newFen) before sending message
        try {
//            if (senderSocket == null) {
            if (type == GUEST && socket == null) {
                socket = new Socket(opponentsIP, port);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
            }
            dos.writeUTF(getFen());
            dos.flush();
        } catch (IOException ex) {
            System.err.println("Doplnit logger");
        }
        /*
        try {
            senderSocket = new Socket("127.0.0.1", port);
            for (int i = 0; i < 130; i++) {
                Thread.sleep(300);
                System.out.println(i + " krat");
                String msg = Integer.toString(i);
                dos = new DataOutputStream(senderSocket.getOutputStream());
                dos.writeUTF(msg);
                dos.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
    }

    @MainWindow.TemporaryForTesting
    private List parseMsg(String msg) {
        int x = Integer.parseInt(msg) % 62;
        return List.of((int) (Math.random() * 63), (int) (Math.random() * 63), (int) (Math.random() * 63), (int) (Math.random() * 63));
    }

    public final void setMyIP(boolean localhost) {
        try (final DatagramSocket socket = new DatagramSocket()) {
            if (localhost) {
                myIP = InetAddress.getLoopbackAddress();
            } else {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                myIP = socket.getLocalAddress();
            }
        } catch (SocketException | UnknownHostException ex) {
            System.err.println("Doplnit logger");
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean a) {
        active = a;
    }

    public InetAddress getMyIp() {
        return myIP;
    }

    public void setOpponentsIP(InetAddress ip) throws UnknownHostException {
        opponentsIP = ip;
    }

    public void setOpponentsIP(String ip) throws UnknownHostException {
        opponentsIP = InetAddress.getByName(ip);
    }

    public InetAddress getOpponentsIP() {
        return opponentsIP;
    }

    public void setFen(String newFen) {
        fen = newFen;
    }

    public String getFen() {
        return fen;
    }
}
