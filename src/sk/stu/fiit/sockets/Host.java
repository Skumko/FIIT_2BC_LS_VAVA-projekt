/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * @author Matúš Baran
 */
public class Host {

    private boolean active = true;
    private static InetAddress hostIP;

    private Socket senderSocket;
    private Socket listenerSocket;

    private DataInputStream dis;
    private DataOutputStream dos;

    private Thread senderT = new Thread() {
        @Override
        public void run() {
            send();
        }
    };
    private Thread listenerT = new Thread() {
        @Override
        public void run() {
            listen();
        }
    };

    private MainWindow m = new MainWindow();

    public static void main(String[] args) {
        Host host = new Host();
        Host.setIP(true);
        System.out.println(hostIP);
        host.m.setVisible(true);
        host.listenerT.start();
        host.senderT.start();
    }

    public void listen() {
        try (ServerSocket ss = new ServerSocket(48777, 100, hostIP);) {
            this.listenerSocket = ss.accept();
            this.dis = new DataInputStream(listenerSocket.getInputStream());
            this.dos = new DataOutputStream(listenerSocket.getOutputStream());
            String msg = dis.readUTF();
            this.m.test(parseMsg(msg));
            while (active) {
//                listenerSocket = ss.accept();
                msg = dis.readUTF();
                this.m.test(parseMsg(msg));
            }
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send() {
        try {
            this.senderSocket = new Socket("127.0.0.1", 48777);
            for (int i = 0; i < 130; i++) {
                Thread.sleep(300);
                System.out.println(i + " krat");
                String msg = Integer.toString(i);
                this.dos = new DataOutputStream(senderSocket.getOutputStream());
                this.dos.writeUTF(msg);
                this.dos.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List parseMsg(String msg) {
        int x = Integer.parseInt(msg) % 62;
        return List.of((int) (Math.random() * 63),(int) (Math.random() * 63), (int) (Math.random() * 63), (int) (Math.random() * 63));
//        if (msg.equals("7926")) {
//            return List.of(7, 9, 26);
//        }
//        return List.of(1, 2);
    }

    public static void setIP(boolean local) {
        try (final DatagramSocket socket = new DatagramSocket()) {
            if (local) {
                hostIP = InetAddress.getLoopbackAddress();
            } else {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                hostIP = socket.getLocalAddress();
            }
        } catch (SocketException | UnknownHostException ex) {
            System.err.println("Doplnit logger");
        }
    }
}
