/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.GUI;

import java.awt.Image;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import sk.stu.fiit.Figurky.Pawn;

/**
 *
 * @author Matúš Baran
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        panelGame.setVisible(false);
        panelInit.setVisible(true);
        panelGameBoard.setLayout(null);
        printMyIp(lblLocalIP);
    }

    private int[] nextPosBlackElim = {810, 70};
    private int[] nextPosWhiteElim = {810, 710};
    private Map<JLabel, List<JLabel>> dots = new HashMap<>();
    private long nextDot = 0;

    private void printMyIp(JLabel lbl) {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            lbl.setText(socket.getLocalAddress().getHostAddress());
        } catch (SocketException ex) {
            System.err.println("Doplnit logger");
        } catch (UnknownHostException ex) {
            System.err.println("Doplnit logger");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLanguage = new javax.swing.JPanel();
        btnLanguageSK = new javax.swing.JButton();
        btnLanguageEN = new javax.swing.JButton();
        lblLocalIP = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        panelGame = new javax.swing.JPanel();
        panelGameBoard = new javax.swing.JPanel();
        whitePawnA = new javax.swing.JLabel();
        whitePawnB = new javax.swing.JLabel();
        whitePawnC = new javax.swing.JLabel();
        whitePawnD = new javax.swing.JLabel();
        whitePawnE = new javax.swing.JLabel();
        whitePawnF = new javax.swing.JLabel();
        whitePawnG = new javax.swing.JLabel();
        whitePawnH = new javax.swing.JLabel();
        whiteRookL = new javax.swing.JLabel();
        whiteKnightL = new javax.swing.JLabel();
        whiteBishopL = new javax.swing.JLabel();
        whiteQueen = new javax.swing.JLabel();
        whiteKing = new javax.swing.JLabel();
        whiteBishopR = new javax.swing.JLabel();
        whiteKnightR = new javax.swing.JLabel();
        whiteRookR = new javax.swing.JLabel();
        blackPawnA = new javax.swing.JLabel();
        blackPawnB = new javax.swing.JLabel();
        blackPawnC = new javax.swing.JLabel();
        blackPawnD = new javax.swing.JLabel();
        blackPawnE = new javax.swing.JLabel();
        blackPawnF = new javax.swing.JLabel();
        blackPawnG = new javax.swing.JLabel();
        blackPawnH = new javax.swing.JLabel();
        blackRookL = new javax.swing.JLabel();
        blackKnightL = new javax.swing.JLabel();
        blackBishopL = new javax.swing.JLabel();
        blackQueen = new javax.swing.JLabel();
        blackKing = new javax.swing.JLabel();
        blackBishopR = new javax.swing.JLabel();
        blackKnightR = new javax.swing.JLabel();
        blackRookR = new javax.swing.JLabel();
        lblGameBoard = new javax.swing.JLabel();
        panelGameWhiteMiniFigures = new javax.swing.JPanel();
        panelGameBlackMiniFigures = new javax.swing.JPanel();
        panelGameRows = new javax.swing.JPanel();
        lblGameRows = new javax.swing.JLabel();
        panelGameColumns = new javax.swing.JPanel();
        lblGameColumns = new javax.swing.JLabel();
        panelGameDialog = new javax.swing.JPanel();
        scrollGameMoveHistory = new javax.swing.JScrollPane();
        txtGameMoveHistory = new javax.swing.JTextPane();
        lblGameMoveHistory = new javax.swing.JLabel();
        comboGameBoardColor = new javax.swing.JComboBox<>();
        lblGameBoardColor = new javax.swing.JLabel();
        panelGameOpponentTimer = new javax.swing.JPanel();
        lblGameOpponentTimer = new javax.swing.JLabel();
        panelGameOpponentInfo = new javax.swing.JPanel();
        lblGameOpponentInfo = new javax.swing.JLabel();
        panelGamePlayerTimer = new javax.swing.JPanel();
        lblGamePlayerTimer = new javax.swing.JLabel();
        btnOfferPat = new javax.swing.JButton();
        btnSurrender = new javax.swing.JButton();
        panelInit = new javax.swing.JPanel();
        txtOpponentsIP = new javax.swing.JTextField();
        lblInitEnterIP = new javax.swing.JLabel();
        btnInitRules = new javax.swing.JButton();
        btnIintCreateGame = new javax.swing.JButton();
        btnInitJoinGame = new javax.swing.JButton();
        lblInitGameName = new javax.swing.JLabel();
        lblInitGameShortcut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OMGOC - OnlineMultiplayerGameOfChess");
        setPreferredSize(new java.awt.Dimension(1416, 939));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLanguage.setOpaque(false);
        panelLanguage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLanguageSK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLanguageSKMouseReleased(evt);
            }
        });
        panelLanguage.add(btnLanguageSK, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 10, 30, 30));

        btnLanguageEN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLanguageENMouseReleased(evt);
            }
        });
        panelLanguage.add(btnLanguageEN, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 10, 30, 30));

        lblLocalIP.setForeground(new java.awt.Color(200, 200, 200));
        lblLocalIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLocalIP.setText("192.168.1.50");
        panelLanguage.add(lblLocalIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 100, 20));

        getContentPane().add(panelLanguage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, -1));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(1400, 900));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGame.setBackground(new java.awt.Color(0, 40, 60));
        panelGame.setPreferredSize(new java.awt.Dimension(1400, 900));
        panelGame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGameBoard.setBackground(new java.awt.Color(255, 255, 255));
        panelGameBoard.setOpaque(false);
        panelGameBoard.setPreferredSize(new java.awt.Dimension(800, 800));
        panelGameBoard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        whitePawnA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WP.png"))); // NOI18N
        whitePawnA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                whitePawnAMouseReleased(evt);
            }
        });
        panelGameBoard.add(whitePawnA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, -1, -1));

        whitePawnB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WP.png"))); // NOI18N
        whitePawnB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                whitePawnBMouseReleased(evt);
            }
        });
        panelGameBoard.add(whitePawnB, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, -1, -1));

        whitePawnC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WP.png"))); // NOI18N
        panelGameBoard.add(whitePawnC, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 600, -1, -1));

        whitePawnD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WP.png"))); // NOI18N
        panelGameBoard.add(whitePawnD, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, -1, -1));

        whitePawnE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WP.png"))); // NOI18N
        panelGameBoard.add(whitePawnE, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 600, -1, -1));

        whitePawnF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WP.png"))); // NOI18N
        panelGameBoard.add(whitePawnF, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 600, -1, -1));

        whitePawnG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WP.png"))); // NOI18N
        panelGameBoard.add(whitePawnG, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 600, -1, -1));

        whitePawnH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WP.png"))); // NOI18N
        panelGameBoard.add(whitePawnH, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 600, 100, 100));

        whiteRookL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WR.png"))); // NOI18N
        panelGameBoard.add(whiteRookL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, -1, -1));

        whiteKnightL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WN.png"))); // NOI18N
        panelGameBoard.add(whiteKnightL, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 700, -1, -1));

        whiteBishopL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WB.png"))); // NOI18N
        panelGameBoard.add(whiteBishopL, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 700, -1, -1));

        whiteQueen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WQ.png"))); // NOI18N
        panelGameBoard.add(whiteQueen, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 700, -1, -1));

        whiteKing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WK.png"))); // NOI18N
        panelGameBoard.add(whiteKing, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 700, -1, -1));

        whiteBishopR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WB.png"))); // NOI18N
        panelGameBoard.add(whiteBishopR, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 700, -1, -1));

        whiteKnightR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WN.png"))); // NOI18N
        panelGameBoard.add(whiteKnightR, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 700, -1, -1));

        whiteRookR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/WR.png"))); // NOI18N
        panelGameBoard.add(whiteRookR, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 700, -1, -1));

        blackPawnA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BP.png"))); // NOI18N
        panelGameBoard.add(blackPawnA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 100, 100));

        blackPawnB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BP.png"))); // NOI18N
        panelGameBoard.add(blackPawnB, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 100, 100));

        blackPawnC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BP.png"))); // NOI18N
        panelGameBoard.add(blackPawnC, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 100, 100));

        blackPawnD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BP.png"))); // NOI18N
        panelGameBoard.add(blackPawnD, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 100, 100));

        blackPawnE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BP.png"))); // NOI18N
        panelGameBoard.add(blackPawnE, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 100, 100));

        blackPawnF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BP.png"))); // NOI18N
        panelGameBoard.add(blackPawnF, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 100, 100));

        blackPawnG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BP.png"))); // NOI18N
        panelGameBoard.add(blackPawnG, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 100, 100));

        blackPawnH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BP.png"))); // NOI18N
        panelGameBoard.add(blackPawnH, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 100, 100, 100));

        blackRookL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BR.png"))); // NOI18N
        panelGameBoard.add(blackRookL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 100));

        blackKnightL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BN.png"))); // NOI18N
        panelGameBoard.add(blackKnightL, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 100, 100));

        blackBishopL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BB.png"))); // NOI18N
        panelGameBoard.add(blackBishopL, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 100, 100));

        blackQueen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BQ.png"))); // NOI18N
        panelGameBoard.add(blackQueen, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 100, 100));

        blackKing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BK.png"))); // NOI18N
        panelGameBoard.add(blackKing, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 100, 100));

        blackBishopR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BB.png"))); // NOI18N
        panelGameBoard.add(blackBishopR, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 100, 100));

        blackKnightR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BN.png"))); // NOI18N
        panelGameBoard.add(blackKnightR, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 100, 100));

        blackRookR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/100x100/BR.png"))); // NOI18N
        panelGameBoard.add(blackRookR, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 100, 100));

        lblGameBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/figurky_png/boards/woodboard.png"))); // NOI18N
        lblGameBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblGameBoardMouseReleased(evt);
            }
        });
        panelGameBoard.add(lblGameBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelGameWhiteMiniFigures.setBackground(new java.awt.Color(200, 200, 200));
        panelGameWhiteMiniFigures.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 4));
        panelGameWhiteMiniFigures.setPreferredSize(new java.awt.Dimension(40, 340));
        panelGameWhiteMiniFigures.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelGameBoard.add(panelGameWhiteMiniFigures, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 50, 340));

        panelGameBlackMiniFigures.setBackground(new java.awt.Color(200, 200, 200));
        panelGameBlackMiniFigures.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 4));
        panelGameBlackMiniFigures.setPreferredSize(new java.awt.Dimension(40, 340));
        panelGameBlackMiniFigures.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelGameBoard.add(panelGameBlackMiniFigures, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 400, 50, 340));

        panelGame.add(panelGameBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 840, 800));

        panelGameRows.setBackground(new java.awt.Color(200, 200, 200));
        panelGameRows.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 4));
        panelGameRows.setPreferredSize(new java.awt.Dimension(40, 780));
        panelGameRows.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameRows.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblGameRows.setForeground(new java.awt.Color(102, 102, 0));
        lblGameRows.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameRows.setText("<html>8<br><br><br><br>7<br><br><br><br>6<br><br><br><br>5<br><br><br><br>4<br><br><br><br>3<br><br><br><br>2<br><br><br><br>1</html>");
        panelGameRows.add(lblGameRows, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 780));

        panelGame.add(panelGameRows, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 50, -1));

        panelGameColumns.setBackground(new java.awt.Color(200, 200, 200));
        panelGameColumns.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 4));
        panelGameColumns.setPreferredSize(new java.awt.Dimension(780, 40));
        panelGameColumns.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameColumns.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblGameColumns.setForeground(new java.awt.Color(102, 102, 0));
        lblGameColumns.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameColumns.setText("A              B              C              D              E              F              G              H");
        panelGameColumns.add(lblGameColumns, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 780, 40));

        panelGame.add(panelGameColumns, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 830, -1, 50));

        panelGameDialog.setBackground(new java.awt.Color(200, 200, 200));
        panelGameDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 4));
        panelGameDialog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtGameMoveHistory.setText("A2->A4\n----------\nA7->A5\n----------\nD2->D4\n----------\nD7->D4\n----------\n\n\n\n\n\n\n\n\n\n\n\n\n\n---------");
        txtGameMoveHistory.setFocusable(false);
        scrollGameMoveHistory.setViewportView(txtGameMoveHistory);

        panelGameDialog.add(scrollGameMoveHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 400, 210));

        lblGameMoveHistory.setText("Move history:");
        panelGameDialog.add(lblGameMoveHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        comboGameBoardColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Blue", "Brown", "Green", "Grey", "Red" }));
        comboGameBoardColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboGameBoardColorItemStateChanged(evt);
            }
        });
        panelGameDialog.add(comboGameBoardColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 310, 30));

        lblGameBoardColor.setText("Customize board");
        panelGameDialog.add(lblGameBoardColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        panelGame.add(panelGameDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 180, 420, 470));

        panelGameOpponentTimer.setBackground(new java.awt.Color(200, 200, 200));
        panelGameOpponentTimer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 4));
        panelGameOpponentTimer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameOpponentTimer.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblGameOpponentTimer.setForeground(new java.awt.Color(102, 102, 0));
        lblGameOpponentTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGameOpponentTimer.setText("15:00");
        panelGameOpponentTimer.add(lblGameOpponentTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

        panelGame.add(panelGameOpponentTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 140, 60));

        panelGameOpponentInfo.setBackground(new java.awt.Color(200, 200, 200));
        panelGameOpponentInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameOpponentInfo.setBackground(new java.awt.Color(0, 0, 0));
        lblGameOpponentInfo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblGameOpponentInfo.setForeground(new java.awt.Color(102, 102, 0));
        lblGameOpponentInfo.setText("Matko Kubko, 192.168.78.55");
        panelGameOpponentInfo.add(lblGameOpponentInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 20));

        panelGame.add(panelGameOpponentInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, 290, 40));

        panelGamePlayerTimer.setBackground(new java.awt.Color(200, 200, 200));
        panelGamePlayerTimer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 4));
        panelGamePlayerTimer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGamePlayerTimer.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblGamePlayerTimer.setForeground(new java.awt.Color(102, 102, 0));
        lblGamePlayerTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGamePlayerTimer.setText("15:00");
        panelGamePlayerTimer.add(lblGamePlayerTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

        panelGame.add(panelGamePlayerTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 720, 140, 60));

        btnOfferPat.setBackground(new java.awt.Color(175, 175, 175));
        btnOfferPat.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnOfferPat.setForeground(new java.awt.Color(100, 100, 100));
        btnOfferPat.setText("Offer pat");
        btnOfferPat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 0), 4, true));
        panelGame.add(btnOfferPat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 720, 170, 60));

        btnSurrender.setBackground(new java.awt.Color(175, 175, 175));
        btnSurrender.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSurrender.setForeground(new java.awt.Color(153, 0, 0));
        btnSurrender.setText("Surrender");
        btnSurrender.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 0), 4, true));
        panelGame.add(btnSurrender, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 790, 170, 60));

        jLayeredPane1.add(panelGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelInit.setBackground(new java.awt.Color(0, 40, 60));
        panelInit.setPreferredSize(new java.awt.Dimension(1400, 900));
        panelInit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtOpponentsIP.setBackground(new java.awt.Color(200, 200, 200));
        txtOpponentsIP.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtOpponentsIP.setForeground(new java.awt.Color(102, 102, 0));
        txtOpponentsIP.setPreferredSize(new java.awt.Dimension(320, 50));
        panelInit.add(txtOpponentsIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 460, 320, 50));

        lblInitEnterIP.setBackground(new java.awt.Color(200, 200, 200));
        lblInitEnterIP.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblInitEnterIP.setForeground(new java.awt.Color(200, 200, 200));
        lblInitEnterIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInitEnterIP.setText("Enter opponent's IP address:");
        panelInit.add(lblInitEnterIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 600, -1));

        btnInitRules.setBackground(new java.awt.Color(175, 175, 175));
        btnInitRules.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnInitRules.setForeground(new java.awt.Color(102, 102, 0));
        btnInitRules.setText("Rules");
        btnInitRules.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnInitRulesMouseReleased(evt);
            }
        });
        panelInit.add(btnInitRules, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 710, 320, 60));

        btnIintCreateGame.setBackground(new java.awt.Color(175, 175, 175));
        btnIintCreateGame.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnIintCreateGame.setForeground(new java.awt.Color(102, 102, 0));
        btnIintCreateGame.setText("Create game");
        panelInit.add(btnIintCreateGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 580, 320, 60));

        btnInitJoinGame.setBackground(new java.awt.Color(175, 175, 175));
        btnInitJoinGame.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnInitJoinGame.setForeground(new java.awt.Color(102, 102, 0));
        btnInitJoinGame.setText("Join game");
        panelInit.add(btnInitJoinGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 580, 320, 60));

        lblInitGameName.setFont(new java.awt.Font("Tahoma", 2, 36)); // NOI18N
        lblInitGameName.setForeground(new java.awt.Color(102, 102, 0));
        lblInitGameName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInitGameName.setText(" Online Multiplayer Game Of Chess ");
        panelInit.add(lblInitGameName, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 600, -1));

        lblInitGameShortcut.setFont(new java.awt.Font("Tahoma", 1, 150)); // NOI18N
        lblInitGameShortcut.setForeground(new java.awt.Color(102, 102, 0));
        lblInitGameShortcut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInitGameShortcut.setText("OMGOC");
        lblInitGameShortcut.setPreferredSize(new java.awt.Dimension(700, 200));
        panelInit.add(lblInitGameShortcut, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jLayeredPane1.add(panelInit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblGameBoardMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGameBoardMouseReleased
        // TODO add your handling code here:
        eliminateFigure(whiteQueen, true);
        eliminateFigure(blackPawnB, false);
    }//GEN-LAST:event_lblGameBoardMouseReleased

    private void comboGameBoardColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboGameBoardColorItemStateChanged
        // TODO add your handling code here:
        String color = comboGameBoardColor.getSelectedItem().toString();
        if (color == null) {
            return;
        }
        switch (color) {
            case "Default":
                lblGameBoard.setIcon(new ImageIcon(Paths.get("src", "figurky_png", "boards", "woodboard.png").toString()));
                break;
            case "Blue":
                lblGameBoard.setIcon(new ImageIcon(Paths.get("src", "figurky_png", "boards", "blueboard.png").toString()));
                break;
            case "Brown":
                lblGameBoard.setIcon(new ImageIcon(Paths.get("src", "figurky_png", "boards", "brownboard.png").toString()));
                break;
            case "Green":
                lblGameBoard.setIcon(new ImageIcon(Paths.get("src", "figurky_png", "boards", "greenboard.png").toString()));
                break;
            case "Grey":
                lblGameBoard.setIcon(new ImageIcon(Paths.get("src", "figurky_png", "boards", "greyboard.png").toString()));
                break;
            case "Red":
                lblGameBoard.setIcon(new ImageIcon(Paths.get("src", "figurky_png", "boards", "redboard.png").toString()));
                break;
            default:
                lblGameBoard.setIcon(new ImageIcon(Paths.get("src", "figurky_png", "boards", "woodboard.png").toString()));
        }
    }//GEN-LAST:event_comboGameBoardColorItemStateChanged

    private void btnLanguageENMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLanguageENMouseReleased
        // TODO add your handling code here:
        panelGame.setVisible(false);
        panelInit.setVisible(true);
    }//GEN-LAST:event_btnLanguageENMouseReleased

    private void btnLanguageSKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLanguageSKMouseReleased
        // TODO add your handling code here:
        panelGame.setVisible(true);
        panelInit.setVisible(false);
    }//GEN-LAST:event_btnLanguageSKMouseReleased

    private void btnInitRulesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInitRulesMouseReleased
        // TODO add your handling code here:
        new Tips_And_Rules().setVisible(true);
    }//GEN-LAST:event_btnInitRulesMouseReleased

    private void whitePawnAMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_whitePawnAMouseReleased
        // TODO add your handling code here:
        showPossibleMoves((JLabel) evt.getComponent(), List.of(10, 12, 25));
    }//GEN-LAST:event_whitePawnAMouseReleased

    private void whitePawnBMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_whitePawnBMouseReleased
        // TODO add your handling code here:
        showPossibleMoves((JLabel) evt.getComponent(), List.of(50, 62, 35));
    }//GEN-LAST:event_whitePawnBMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel blackBishopL;
    private javax.swing.JLabel blackBishopR;
    private javax.swing.JLabel blackKing;
    private javax.swing.JLabel blackKnightL;
    private javax.swing.JLabel blackKnightR;
    private javax.swing.JLabel blackPawnA;
    private javax.swing.JLabel blackPawnB;
    private javax.swing.JLabel blackPawnC;
    private javax.swing.JLabel blackPawnD;
    private javax.swing.JLabel blackPawnE;
    private javax.swing.JLabel blackPawnF;
    private javax.swing.JLabel blackPawnG;
    private javax.swing.JLabel blackPawnH;
    private javax.swing.JLabel blackQueen;
    private javax.swing.JLabel blackRookL;
    private javax.swing.JLabel blackRookR;
    private javax.swing.JButton btnIintCreateGame;
    private javax.swing.JButton btnInitJoinGame;
    private javax.swing.JButton btnInitRules;
    private javax.swing.JButton btnLanguageEN;
    private javax.swing.JButton btnLanguageSK;
    private javax.swing.JButton btnOfferPat;
    private javax.swing.JButton btnSurrender;
    private javax.swing.JComboBox<String> comboGameBoardColor;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lblGameBoard;
    private javax.swing.JLabel lblGameBoardColor;
    private javax.swing.JLabel lblGameColumns;
    private javax.swing.JLabel lblGameMoveHistory;
    private javax.swing.JLabel lblGameOpponentInfo;
    private javax.swing.JLabel lblGameOpponentTimer;
    private javax.swing.JLabel lblGamePlayerTimer;
    private javax.swing.JLabel lblGameRows;
    private javax.swing.JLabel lblInitEnterIP;
    private javax.swing.JLabel lblInitGameName;
    private javax.swing.JLabel lblInitGameShortcut;
    private javax.swing.JLabel lblLocalIP;
    private javax.swing.JPanel panelGame;
    private javax.swing.JPanel panelGameBlackMiniFigures;
    private javax.swing.JPanel panelGameBoard;
    private javax.swing.JPanel panelGameColumns;
    private javax.swing.JPanel panelGameDialog;
    private javax.swing.JPanel panelGameOpponentInfo;
    private javax.swing.JPanel panelGameOpponentTimer;
    private javax.swing.JPanel panelGamePlayerTimer;
    private javax.swing.JPanel panelGameRows;
    private javax.swing.JPanel panelGameWhiteMiniFigures;
    private javax.swing.JPanel panelInit;
    private javax.swing.JPanel panelLanguage;
    private javax.swing.JScrollPane scrollGameMoveHistory;
    private javax.swing.JTextPane txtGameMoveHistory;
    private javax.swing.JTextField txtOpponentsIP;
    private javax.swing.JLabel whiteBishopL;
    private javax.swing.JLabel whiteBishopR;
    private javax.swing.JLabel whiteKing;
    private javax.swing.JLabel whiteKnightL;
    private javax.swing.JLabel whiteKnightR;
    private javax.swing.JLabel whitePawnA;
    private javax.swing.JLabel whitePawnB;
    private javax.swing.JLabel whitePawnC;
    private javax.swing.JLabel whitePawnD;
    private javax.swing.JLabel whitePawnE;
    private javax.swing.JLabel whitePawnF;
    private javax.swing.JLabel whitePawnG;
    private javax.swing.JLabel whitePawnH;
    private javax.swing.JLabel whiteQueen;
    private javax.swing.JLabel whiteRookL;
    private javax.swing.JLabel whiteRookR;
    // End of variables declaration//GEN-END:variables

    /**
     * Resets inital coordinates of positions where eliminated figures are put
     */
    private void resetElimPosValues() {
        nextPosBlackElim[0] = 810;
        nextPosBlackElim[1] = 70;
        nextPosWhiteElim[0] = 810;
        nextPosWhiteElim[1] = 710;
    }

    /**
     * Rescales {@link ImageIcon ImageIcon} of {@link JLabel label} of figure
     * and change its location to side panel
     *
     * @param figure {@link JLabel JLabel} of figure to be eliminated
     * @param isWhite true if eliminated figure was white
     */
    private void eliminateFigure(JLabel figure, boolean isWhite) {
        figure.setIcon(rescale((ImageIcon) figure.getIcon(), 20, 20));
        if (isWhite) {
            figure.setBounds(nextPosWhiteElim[0], nextPosWhiteElim[1], 20, 20);
            nextPosWhiteElim[1] -= 20;
        } else {
            figure.setBounds(nextPosBlackElim[0], nextPosBlackElim[1], 20, 20);
            nextPosBlackElim[1] += 20;
        }
    }

    /**
     * Rescales {@link ImageIcon ImageIcon} to fit new size
     *
     * @param i {@link ImageIcon ImageIcon} to be rescaled
     * @param width new width of image
     * @param height new height of image
     * @return rescaled {@link ImageIcon ImageIcon}
     */
    private ImageIcon rescale(ImageIcon i, int width, int height) {
        Image image = i.getImage();
        Image newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    /**
     * Converts x, y representation of gameboard to one-numbered sector
     * representation
     *
     * @param x column number
     * @param y row number
     * @return number of sector
     */
    public static int xyToOne(int x, int y) {
        return x + y * 8;
    }

    /**
     * Converts one-numbered representation of gameboard to x, y representation
     *
     * @param one number of sector
     * @return int array [x, y]
     */
    public static int[] oneToxy(int one) {
        int[] xy = {one % 8, one / 8};
        return xy;
    }

    /**
     * Creates dots of {@link JLabel JLabels} on fileds where figure can
     * possibly move
     *
     * @param figure {@link JLabel label} of selected figure
     * @param sectors {@link List List<int>} of sectors where figure can
     * possibly move
     */
    private void showPossibleMoves(JLabel figure, List sectors) {
        if (dots.containsKey(figure)) {
            removePossibleMoves();
            return;
        }
        removePossibleMoves();

        Function<Integer, JLabel> function = (sector) -> {
            JLabel label = new JLabel();
            panelGameBoard.add(label, 0);
            label.setIcon(new ImageIcon(Path.of("src", "figurky_png", "100x100", "dot.png").toString()));
            int x = oneToxy(sector)[0];
            int y = oneToxy(sector)[1];
            label.setBounds(x * 100, y * 100, 100, 100);
            label.setVisible(true);
            return label;
        };

        dots.put(figure, (List<JLabel>) sectors.stream().map(function).collect(Collectors.toList()));

    }

    /**
     * Removes all dots of possible moves. Removes all labels from
     * {@link JPanel panelGameBoard}. Clears field {@link Map dots}
     */
    public void removePossibleMoves() {
        List<JLabel> labels = dots.values().stream().findFirst().orElse(null);
        if (labels == null) {
            return;
        }
        labels.stream().forEach(label -> panelGameBoard.remove(label));
        panelGameBoard.repaint();
        dots.clear();
    }
}
