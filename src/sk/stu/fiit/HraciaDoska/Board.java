/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.HraciaDoska;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import sk.stu.fiit.Figurky.*;
import sk.stu.fiit.Hrac.BlackPlayer;
import sk.stu.fiit.Hrac.Player;
import sk.stu.fiit.Hrac.WhitePlayer;
import sk.stu.fiit.Side;

/**
 *
 * @author palko
 */
public class Board {

    private final List<Tile> boardTiles;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private final WhitePlayer whiteP;
    private final BlackPlayer blackP;
    private final Player currentPlayer;

    private final Pawn enPassantPawn;

    private Board(final BoardBuilder builder) {
        this.boardTiles = createBoardTiles(builder);
        this.whitePieces = getActivePieces(builder, Side.WHITE);
        this.blackPieces = getActivePieces(builder, Side.BLACK);

        final Collection<Move> whiteInitialLegalMoves = getPiecesLegalMoves(this.whitePieces);
        final Collection<Move> blackInitialLegalMoves = getPiecesLegalMoves(this.blackPieces);

        this.whiteP = new WhitePlayer(this, whiteInitialLegalMoves, blackInitialLegalMoves);
        this.blackP = new BlackPlayer(this, blackInitialLegalMoves, whiteInitialLegalMoves);
        this.currentPlayer = builder.nextToMove.pickSide(this.whiteP, this.blackP);
        this.enPassantPawn = builder.enPassantPawn;
    }

    public Tile getTile(final int possiblePosition) {
        return boardTiles.get(possiblePosition);
    }

    public Collection<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Collection<Piece> getBlackPieces() {
        return blackPieces;
    }

    public WhitePlayer getWhiteP() {
        return whiteP;
    }

    public BlackPlayer getBlackP() {
        return blackP;
    }

    public Pawn getEnPassantPawn() {
        return this.enPassantPawn;
    }

    public Collection<Move> getAllLegalMoves() {
        return Stream.concat(this.whiteP.getLegalMoves().stream(),
                this.blackP.getLegalMoves().stream()).collect(Collectors.toList());
    }

    private List<Tile> createBoardTiles(BoardBuilder builder) {
        final Tile[] tiles = new Tile[Utils.NUM_OF_TILES];
        for (int i = 0; i < Utils.NUM_OF_TILES; i++) {
            tiles[i] = Tile.create(i, builder.boardState.get(i));

        }
        return Collections.unmodifiableList(Arrays.asList(tiles));
    }

    public static Board createStartBoard() {

        final BoardBuilder builder = new BoardBuilder();
        //Black side
        builder.setPiece(new Rook(0, Side.BLACK));
        builder.setPiece(new Knight(1, Side.BLACK));
        builder.setPiece(new Bishop(2, Side.BLACK));
        builder.setPiece(new Queen(3, Side.BLACK));
        builder.setPiece(new King(4, Side.BLACK, true, true));
        builder.setPiece(new Bishop(5, Side.BLACK));
        builder.setPiece(new Knight(6, Side.BLACK));
        builder.setPiece(new Rook(7, Side.BLACK));
        builder.setPiece(new Pawn(8, Side.BLACK));
        builder.setPiece(new Pawn(9, Side.BLACK));
        builder.setPiece(new Pawn(10, Side.BLACK));
        builder.setPiece(new Pawn(11, Side.BLACK));
        builder.setPiece(new Pawn(12, Side.BLACK));
        builder.setPiece(new Pawn(13, Side.BLACK));
        builder.setPiece(new Pawn(14, Side.BLACK));
        builder.setPiece(new Pawn(15, Side.BLACK));

        //White side
        builder.setPiece(new Pawn(48, Side.WHITE));
        builder.setPiece(new Pawn(49, Side.WHITE));
        builder.setPiece(new Pawn(50, Side.WHITE));
        builder.setPiece(new Pawn(51, Side.WHITE));
        builder.setPiece(new Pawn(52, Side.WHITE));
        builder.setPiece(new Pawn(53, Side.WHITE));
        builder.setPiece(new Pawn(54, Side.WHITE));
        builder.setPiece(new Pawn(55, Side.WHITE));
        builder.setPiece(new Rook(56, Side.WHITE));
        builder.setPiece(new Knight(57, Side.WHITE));
        builder.setPiece(new Bishop(58, Side.WHITE));
        builder.setPiece(new Queen(59, Side.WHITE));
        builder.setPiece(new King(60, Side.WHITE, true, true));
        builder.setPiece(new Bishop(61, Side.WHITE));
        builder.setPiece(new Knight(62, Side.WHITE));
        builder.setPiece(new Rook(63, Side.WHITE));

        builder.setNextToMove(Side.WHITE);
        return builder.build();
    }

    private static Collection<Piece> getActivePieces(final BoardBuilder builder, final Side side) {
        return builder.boardState.values().stream()
                .filter(piece -> piece.getColorSide() == side)
                .collect(Collectors.toList());
    }

    private Collection<Move> getPiecesLegalMoves(final Collection<Piece> pieces) {
        return pieces.stream()
                .flatMap(piece -> piece.getPossibleMoves(this).stream())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        //implementation to create fen string from board
        final StringBuilder builder = new StringBuilder();
        int numberOfEmptyTiles = 0;
        for (int i = 0; i < Utils.NUM_OF_TILES; i++) {
            if (i > 0 && i % 8 == 0) {
                if (numberOfEmptyTiles != 0) {
                    builder.append(numberOfEmptyTiles);
                }
                builder.append("/");
                numberOfEmptyTiles = 0;
            }
            final Tile tile = this.boardTiles.get(i);
            if (tile.hasPiece()) {
                if (numberOfEmptyTiles != 0) {
                    builder.append(numberOfEmptyTiles);
                }
                final String pieceString = this.boardTiles.get(i).toString();
                builder.append(pieceString);
            } else {
                numberOfEmptyTiles++;
            }

        }
        builder.append(" ").append(getCurrentPlayer().toString());
        builder.append(" ").append(Utils.getCastlingCapabilites(this));
        builder.append(" ").append(Utils.getEnPassantTile(this));
        builder.append(" 0 1");

        return builder.toString();
    }

    public String boardFromString(final String fenString) {
        return "";
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public static class BoardBuilder {

        Map<Integer, Piece> boardState;
        Side nextToMove;
        Pawn enPassantPawn;

        public BoardBuilder() {
            this.boardState = new HashMap<>();
        }

        public BoardBuilder setPiece(final Piece piece) {
            this.boardState.put(piece.getPosition(), piece);
            return this;
        }

        public BoardBuilder setNextToMove(final Side nextToMove) {
            this.nextToMove = nextToMove;
            return this;
        }

        public Board build() {
            return new Board(this);
        }

        void setEnPassantPawn(Pawn movedPawn) {
            this.enPassantPawn = movedPawn;
        }

    }
}
