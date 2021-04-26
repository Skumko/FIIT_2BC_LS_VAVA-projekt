/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.HraciaDoska;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import sk.stu.fiit.Figurky.Piece;
import sk.stu.fiit.Side;

/**
 * The Tile class represents one specific Tile on a chess board. The field
 * {@link Tile.coordinate} is a number representation among all 64 possible
 * tiles. {@link Tile.EMPTY_TILES} is a map of all possible tiles on the chess
 * board.
 *
 * @author Pavol Belej
 */
public abstract class Tile {

    protected final int coordinate;
    private static final Map<Integer, EmptyTile> CACHED_EMPTY_TILES = initEmptyBoard();

    private Tile(int coordinate) {
        this.coordinate = coordinate;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public abstract Piece getPiece();

    public abstract boolean hasPiece();

    private static Map<Integer, EmptyTile> initEmptyBoard() {
        Map<Integer, EmptyTile> emptyTiles = new HashMap<>();

        for (int i = 0; i < Utils.NUM_OF_TILES; i++) {
            emptyTiles.put(i, new EmptyTile(i));
        }
        //we make the map unmodifiable to ensure encapsulation and prevent any modification to tiles of the board, then return it
        return Collections.unmodifiableMap(emptyTiles);
    }

    public static Tile create(int coordinateOfTile, Piece pieceToBePlaced) {
        return pieceToBePlaced == null ? CACHED_EMPTY_TILES.get(coordinateOfTile) : new OccupiedTile(pieceToBePlaced, coordinateOfTile);
    }

    /**
     * Represents a tile that is occupied, meaning there is precisely one piece
     * at a time that is standing on top of specific square of the chess board.
     * {@link piece} field represents that piece.
     */
    public static final class OccupiedTile extends Tile {

        private final Piece piece;

        private OccupiedTile(Piece piece, int c) {
            super(c);
            this.piece = piece;
        }

        /**
         * Returns piece that is currently present on a specific tile, which is
         * instance of {@link OccupiedTile} class.
         *
         * @return {@link Piece}object
         */
        @Override
        public Piece getPiece() {
            return this.piece;
        }

        /**
         * Provides information on whether a specific tile is occupied
         *
         * @return true, since this is a {@link OccupiedTile}
         */
        @Override
        public boolean hasPiece() {
            return true;
        }

        @Override
        public String toString() {
            return piece.getColorSide() == Side.BLACK ? piece.toString().toLowerCase() : piece.toString();
        }

    }

    /**
     * Represents a tile that is empty, meaning it has no piece occupying that
     * square on the chess board
     */
    public static final class EmptyTile extends Tile {

        private EmptyTile(final int c) {
            super(c);
        }

        /**
         * This overridden method returns null, because class represents empty
         * Tile, which does not have any piece on it
         *
         * @return null
         */
        @Override
        public Piece getPiece() {
            return null;
        }

        /**
         * Provides information on whether a specific tile is occupied
         *
         * @return false, since this is a {@link EmptyTile}
         */
        @Override
        public boolean hasPiece() {
            return false;
        }

        @Override
        public String toString() {
            return "-";
        }

    }
}
