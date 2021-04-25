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
    private final Side colorSide;
    protected boolean hasMoved = false;
    private final Type pieceType;

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

    public Piece(final int position, final Side side, Type type) {
        this.position = position;
        this.colorSide = side;
        this.pieceType = type;
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

    @Override
    public String toString() {
        return this.pieceType.getName();
    }

}
