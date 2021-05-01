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
import sk.stu.fiit.GUI.MainWindow.TemporaryForTesting;
import sk.stu.fiit.logging.Log;
import sk.stu.fiit.logging.Logs;

/**
 *
 * @author Matúš Baran
 */
public class Host {

    public static final int port = 48777;
    private boolean active = true;
    private InetAddress hostIP;
    private InetAddress guestIP = null;
    private String fen;

    private Socket senderSocket = null;
    private Socket listenerSocket = null;

    private DataInputStream dis;
    private DataOutputStream dos;

    private MainWindow m;

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

    public Host(MainWindow m, boolean localhost) {
        this.m = m;
        setLocalIP(localhost);
//        listenerT.start();
    }

    public static void main(String[] args) {
//        Host host = new Host(new MainWindow(), true);
//        System.out.println(host.hostIP);
//        host.m.setVisible(true);
//        host.listenerT.start();
//        host.senderT.start();
        int x = 5, y = 0;
        try {
            System.out.println(x / y);
        } catch (Exception e) {
            Logs.log(Log.LogLevel.SEVERE, "Delenie nulou", Host.class.getName());
        }
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
            setGuestIP(listenerSocket.getInetAddress());
            dis = new DataInputStream(listenerSocket.getInputStream());
            dos = new DataOutputStream(listenerSocket.getOutputStream());
            setFen(dis.readUTF());
            /*
            perform operations with fen
             */
//            m.test(parseMsg(msg));
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
     * {@link #setFen(java.lang.String) setFen(String)}. If {@link #guestIP
     * guestIP} equals null, an UnknownHostException is thrown.
     *
     * @throws UnknownHostException
     */
    public void send() throws UnknownHostException {
        if (guestIP == null) {
            throw new UnknownHostException("Guest IP je null");
        }
//        setFen(newFen) before sending message
        try {
            if (senderSocket == null) {
                senderSocket = new Socket(guestIP, port);
            }
            dos = new DataOutputStream(senderSocket.getOutputStream());
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean a) {
        active = a;
    }

    public InetAddress getLocalIp() {
        return hostIP;
    }

    public void setGuestIP(InetAddress ip) throws UnknownHostException {
        guestIP = ip;
    }

    public InetAddress getGuestIP() {
        return guestIP;
    }

    public void setFen(String newFen) {
        fen = newFen;
    }

    public String getFen() {
        return fen;
    }
}
