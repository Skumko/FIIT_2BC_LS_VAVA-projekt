/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Hrac;

import java.util.Collection;
import sk.stu.fiit.Figurky.Piece;
import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;
import sk.stu.fiit.Side;

/**
 *
 * @author Pavol Belej
 */
public class WhitePlayer extends Player {

    public WhitePlayer(Board board, Collection<Move> myLegalMoves, Collection<Move> opponentLegalMoves) {
        super(board, myLegalMoves, opponentLegalMoves);
    }

    @Override
    public Side getPlayerSide() {
        return Side.WHITE;
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackP();
    }

    @Override
    public String toString() {
        return "w";
    }

}
