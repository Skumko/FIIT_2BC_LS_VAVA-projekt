/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Figurky;

import java.util.Collection;
import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;
import sk.stu.fiit.HraciaDoska.Utils;
import sk.stu.fiit.Side;

/**
 *
 * @author Pavol Belej
 */
public abstract class Piece {

    protected final int position;
    protected final Side colorSide;
    protected boolean hasMoved = false;
    private final Type pieceType;
    private final int savedHashCode;

    public enum Type {
        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");
        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public Piece(final int position, final Side side, Type type, boolean hasMoved) {
        this.position = position;
        this.colorSide = side;
        this.pieceType = type;
        this.savedHashCode = computeHash();
        this.hasMoved = hasMoved;
    }

    public Side getColorSide() {
        return colorSide;
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    public abstract Collection<Move> getPossibleMoves(final Board board);

    /**
     * Method checks if passed number is valid representation of board tile.
     *
     * @param coordinate one-number possible representation of a tile.
     * @return true, if coordinate is valid, otherwise returns false.
     */
    public static boolean checkCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < Utils.NUM_OF_TILES;
    }

    public int getPosition() {
        return this.position;
    }

    public Type getPieceType() {
        return pieceType;
    }

    private int computeHash() {
        int hash = 7;
        hash = 31 * hash + pieceType.hashCode();
        hash = 31 * hash + position;
        hash = 31 * hash + colorSide.hashCode();
        hash = 31 * hash + (hasMoved() ? 1 : 0);
        return hash;
    }

    public abstract Piece movePiece(Move move);

    @Override
    public String toString() {
        return this.pieceType.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Piece)) {
            return false;
        }
        final Piece other = (Piece) obj;
        return this.position == other.getPosition() && this.pieceType == other.getPieceType()
                && this.colorSide == other.getColorSide() && this.hasMoved == other.hasMoved();
    }

    @Override
    public int hashCode() {
        return this.savedHashCode;
    }

}
