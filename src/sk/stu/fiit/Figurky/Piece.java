/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Figurky;

import java.util.Collection;
import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;
import sk.stu.fiit.Side;

/**
 *
 * @author Pavol Belej
 */
public abstract class Piece {

    //staci vediet ci je biela --> ak biela tak !cierna logicky
    protected final int position;
    private final Side colorSide;
    private boolean znicena = false;
//    protected Typ typ;
//
//    public enum Typ {
//        PESIAK, JAZDEC, STRELEC, VEZA, KRAL, KRALOVNA,
//    }

    public boolean isZnicena() {
        return znicena;
    }

    public void setZnicena(boolean znicena) {
        this.znicena = znicena;
    }

    public Piece(final int position, final Side side) {
        this.position = position;
        this.colorSide = side;
    }

    public Side getColorSide() {
        return colorSide;
    }

//    public Typ getTyp() {
//        return typ;
//    }
    public abstract Collection<Move> getPossibleMoves(final Board board);

    public static boolean checkCoordinate(final int coordinate) {
        return coordinate <= 0 && coordinate < 64;
    }
}