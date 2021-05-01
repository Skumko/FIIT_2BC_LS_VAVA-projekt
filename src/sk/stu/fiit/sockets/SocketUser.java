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
import sk.stu.fiit.GUI.MainWindow;
import sk.stu.fiit.logging.Log;
import static sk.stu.fiit.logging.Logs.log;
import static sk.stu.fiit.sockets.SocketUser.PlayerType.HOST;

/**
 *
 * @author matba
 */
public class SocketUser {

    public static final int hostPort = 48777;
    public static final int guestPort = 48778;
    private boolean active = true;
    private InetAddress myIP;
    private InetAddress opponentsIP = null;
    private String fen;

    private Socket senderSocket = null;
    private Socket listenerSocket = null;

    private DataInputStream dis;
    private DataOutputStream dos;

    private MainWindow m;

    private PlayerType type;

    public enum PlayerType {
        HOST, GUEST
    }

    public boolean isHost() {
        if (this.type == HOST) {
            return true;
        }
        return false;
    }

    private final Runnable initHost = new Runnable() {

        @Override
        public void run() {

            try (ServerSocket ss = new ServerSocket(hostPort, 100, myIP);) {

                listenerSocket = ss.accept();
                setOpponentsIP(listenerSocket.getInetAddress());
                dis = new DataInputStream(listenerSocket.getInputStream());

                //SYN
                setFen(dis.readUTF());
                //SYN ACK
                senderSocket = new Socket(getOpponentsIP(), guestPort);
                dos = new DataOutputStream(senderSocket.getOutputStream());
                dos.writeUTF(fen);
                dos.flush();
                //ACK
                setFen(dis.readUTF());

                m.setOpponentIP();
                m.addMouseListeners(true);

                //now just listen for sockets
                while (active) {
                    setFen(dis.readUTF());
                    switch (fen) {
                        case "stalemate":
                            setFen("end");
                            send();
                            return;
                        case "draw":
                            setFen("end");
                            send();
                            m.draw();
                            return;
                        case "Black player":
                            setFen("end");
                            send();
                            setFen("Black player");
                            m.surrender(fen);
                            return;
                        case "checkmate":
                            setFen("end");
                            send();
                            return;
                        case "end":
                            return;
                        default:
                            break;
                    }
                    m.actualizeBoardFromFen(fen);
                }
            } catch (IOException ex) {
                log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
            }
        }
    };
    private final Runnable initGuest = new Runnable() {

        @Override
        public void run() {
            if (opponentsIP == null) {
                throw new RuntimeException("Guest IP je null");
            }
            try (ServerSocket ss = new ServerSocket(guestPort, 100, myIP);) {
                //SYN
                senderSocket = new Socket(opponentsIP, hostPort);
                dos = new DataOutputStream(senderSocket.getOutputStream());
                setFen("Init");
                dos.writeUTF(fen);
                dos.flush();

                //SYN ACK
                listenerSocket = ss.accept();
                dis = new DataInputStream(listenerSocket.getInputStream());
                dis.readUTF();

                //ACK
                dos.writeUTF(fen);
                dos.flush();

                m.setOpponentIP();
                while (active) {
                    setFen(dis.readUTF());
                    switch (fen) {
                        case "stalemate":
                            setFen("end");
                            send();
                            return;
                        case "draw":
                            setFen("end");
                            send();
                            m.draw();
                            return;
                        case "White player":
                            setFen("end");
                            send();
                            setFen("White player");
                            m.surrender(fen);
                            return;
                        case "checkmate":
                            setFen("end");
                            send();
                            return;
                        case "end":
                            return;
                        default:
                            break;
                    }
                    m.actualizeBoardFromFen(fen);
                }
            } catch (IOException ex) {
                log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
            }
        }
    };
    private final Runnable send = new Runnable() {
        @Override
        public void run() {
            try {
                dos.writeUTF(getFen());
                dos.flush();
            } catch (IOException ex) {
                log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
            }
        }
    };

    public SocketUser(MainWindow m, PlayerType type) {
        this.m = m;
        this.type = type;
        setMyIP(false);
    }

    public void host() {
        new Thread(initHost).start();
    }

    public void guest() {
        new Thread(initGuest).start();
    }

    public void sendFen() {
        new Thread(send).start();
    }

    public void sendFenDraw() {
        new Thread(send).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
        }
        setFen("stalemate");
        new Thread(send).start();
    }

    public void sendFenCheckmate() {
        new Thread(send).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
        }
        setFen("checkmate");
        new Thread(send).start();
    }

    public void close() {
        active = false;
        if (listenerSocket != null) {
            try {
                dis.close();
                dos.close();
                senderSocket.close();
//                listenerSocket.close();

                dis = null;
                dos = null;
                senderSocket = null;
//                listenerSocket = null;
            } catch (IOException ex) {
                log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
            }
        }
    }

    /**
     * Initialization of host
     */
    public void initializeHost() {
        try (ServerSocket ss = new ServerSocket(hostPort, 100, myIP);) {

            listenerSocket = ss.accept();
            setOpponentsIP(listenerSocket.getInetAddress());
            dis = new DataInputStream(listenerSocket.getInputStream());

            //SYN
            setFen(dis.readUTF());
            //SYN ACK
            senderSocket = new Socket(getOpponentsIP(), guestPort);
            dos = new DataOutputStream(senderSocket.getOutputStream());
            dos.writeUTF(fen);
            dos.flush();
            //ACK
            setFen(dis.readUTF());

            m.setOpponentIP();
            m.addMouseListeners(true);

            //now just listen for sockets
            while (active) {
                setFen(dis.readUTF());
                switch (fen) {
                    case "draw":
                        setFen("end");
                        send();
                        return;
                    case "Black player":
                        setFen("end");
                        send();
                        setFen("Black player");
                        m.surrender(fen);
                        return;
                    case "checkmate":
                        setFen("end");
                        send();
                        return;
                    case "end":
                        return;
                    default:
                        break;
                }
                m.actualizeBoardFromFen(fen);
            }
            listenerSocket.close();
        } catch (IOException ex) {
            log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
        }
    }

    /**
     * Initialization of guest
     */
    public void initializeGuest() {
        if (opponentsIP == null) {
            throw new RuntimeException("Guest IP je null");
        }
        try (ServerSocket ss = new ServerSocket(guestPort, 100, myIP);) {
            //SYN
            senderSocket = new Socket(opponentsIP, hostPort);
            dos = new DataOutputStream(senderSocket.getOutputStream());
            setFen("Init");
            dos.writeUTF(fen);
            dos.flush();

            //SYN ACK
            listenerSocket = ss.accept();
            dis = new DataInputStream(listenerSocket.getInputStream());
            dis.readUTF();

            //ACK
            dos.writeUTF(fen);
            dos.flush();

            m.setOpponentIP();
            while (active) {
                setFen(dis.readUTF());
                switch (fen) {
                    case "draw":
                        setFen("end");
                        send();
                        return;
                    case "White player":
                        setFen("end");
                        send();
                        setFen("White player");
                        m.surrender(fen);
                        return;
                    case "checkmate":
                        setFen("end");
                        send();
                        return;
                    case "end":
                        return;
                    default:
                        break;
                }
                m.actualizeBoardFromFen(fen);
            }
        } catch (IOException ex) {
            log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
        }
    }

    /**
     * Method to send string representation of board via socket. Field
     * {@link #fen fen}, which contains string representation of board, needs to
     * be set before calling this method via
     * {@link #setFen(java.lang.String) setFen(String)}. If {@link #guestIP
     * guestIP} equals null, an UnknownHostException is thrown.
     */
    public void send() {
        try {
            dos.writeUTF(getFen());
            dos.flush();
        } catch (IOException ex) {
            log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
        }
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
            log(Log.LogLevel.SEVERE, MainWindow.class.getName(), ex.getMessage());
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
