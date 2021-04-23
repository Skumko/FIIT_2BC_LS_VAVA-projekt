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

    //staci vediet ci je biela --> ak biela tak !cierna logicky
    protected final int position;
    private final Side colorSide;
    protected boolean hasMoved = false;

    public Piece(final int position, final Side side) {
        this.position = position;
        this.colorSide = side;
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
     * @return true, if {
     * @param coordinate} is valid, otherwise returns false.
     */
    public static boolean checkCoordinate(final int coordinate) {
        return coordinate <= 0 && coordinate < Utils.NUM_OF_TILES;
    }
}
