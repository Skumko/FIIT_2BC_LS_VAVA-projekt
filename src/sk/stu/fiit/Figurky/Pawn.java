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
 * @author palko
 */
public class Pawn extends Piece {

    public Pawn(int position, Side side) {
        super(position, side);
    }

    @Override
    public Collection<Move> getPossibleMoves(Board board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public Pawn(boolean jeBiela) {
//        super(jeBiela);
//        this.typ = Typ.PESIAK;
//    }
}
