/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.HraciaDoska;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import sk.stu.fiit.Figurky.*;
import sk.stu.fiit.Hrac.BlackPlayer;
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

    private Board(BoardBuilder builder) {
        this.boardTiles = createBoardTiles(builder);
        this.whitePieces = getActivePieces(builder, Side.WHITE);
        this.blackPieces = getActivePieces(builder, Side.BLACK);

        final Collection<Move> whiteInitialLegalMoves = getLegalMoves(this.whitePieces);
        final Collection<Move> blackInitialLegalMoves = getLegalMoves(this.blackPieces);

        this.whiteP = new WhitePlayer(this, whiteInitialLegalMoves, blackInitialLegalMoves);
        this.blackP = new BlackPlayer(this, blackInitialLegalMoves, whiteInitialLegalMoves);
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
        builder.setPiece(new King(4, Side.BLACK));
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
        builder.setPiece(new King(60, Side.WHITE));
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

    private Collection<Move> getLegalMoves(final Collection<Piece> pieces) {
        return pieces.stream()
                .flatMap(piece -> piece.getPossibleMoves(this).stream())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Utils.NUM_OF_TILES; i++) {
            final String tileString = this.boardTiles.get(i).toString();
            builder.append(String.format("%3s", tileString));
            //if we get to the edge of the rank
            if ((i + 1) % 8 == 0) {
                builder.append("\n");
            }

        }
        return builder.toString();
    }

    public static class BoardBuilder {

        Map<Integer, Piece> boardState;
        Side nextToMove;

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
    }
}
