/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Hrac;

import java.util.Collection;
import sk.stu.fiit.Figurky.King;
import sk.stu.fiit.Figurky.Piece;
import sk.stu.fiit.Figurky.Piece.Type;
import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;
import sk.stu.fiit.Side;

/**
 *
 * @author Pavol Belej
 */
public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMove;
    private final boolean isInCheck;

    public Player(final Board board, final Collection<Move> myLegalMoves, final Collection<Move> opponentLegalMoves) {
        this.board = board;
        this.playerKing = findKing();
        this.legalMove = myLegalMoves;
        //to do 
        this.isInCheck = false;
    }

    public abstract Collection<Piece> getActivePieces();

    public abstract Side getPlayerSide();

    public abstract Player getOpponent();

    private King findKing() {
        for (Piece piece : getActivePieces()) {
            if (piece.getPieceType() == Type.KING) {
                return (King) piece;
            }
        }
        throw new RuntimeException("THERE IS NO KING ON THE BOARD!");
    }

    //TO DO
    public PerformMove makeMove(Move move) {
        return null;
    }

    //TO DO - get status on the king safety
    public boolean isCheck() {
        return false;
    }

    public boolean isCheckmate() {
        return false;
    }

    public boolean isStalemate() {
        return false;
    }
}
