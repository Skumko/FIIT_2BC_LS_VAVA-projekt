/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Figurky;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;
import sk.stu.fiit.HraciaDoska.Tile;
import sk.stu.fiit.HraciaDoska.Utils;
import sk.stu.fiit.Side;

/**
 *
 * @author Pavol Belej
 */
public class King extends Piece {

    private static final int[] POSSIBLE_MOVES_OFFSETS = {-9, -8, -7, -1, 1, 7, 8, 9};

    private final boolean canCastleKingside;
    private final boolean canCastleQueenside;
    private final boolean isCastled;

    public King(int position, Side side, final boolean canCastleKingside,
            final boolean canCastleQueenside) {
        super(position, side, Type.KING, false);
        this.isCastled = false;
        this.canCastleKingside = canCastleKingside;
        this.canCastleQueenside = canCastleQueenside;
        this.hasMoved = hasMoved();
    }

    public King(int position, Side side, final boolean isCastled,
            final boolean canCastleKingside, final boolean canCastleQueenside) {
        super(position, side, Type.KING, false);
        this.isCastled = isCastled;
        this.canCastleKingside = canCastleKingside;
        this.canCastleQueenside = canCastleQueenside;
        this.hasMoved = hasMoved();
    }

    public boolean canCastleKingside() {
        return this.canCastleKingside;
    }

    public boolean canCastleQueenside() {
        return this.canCastleQueenside;
    }

    /**
     * This method calculates all possible moves for certain {@link King} piece.
     * King can move in all directions one tile at a time, if that move would
     * not lead him into check.
     *
     * @param board represents current {@link Board} on which the game is
     * played.
     * @return {@link ArrayList} of objects of type {@link Move}, which
     * represents all possible moves.
     */
    @Override
    public Collection<Move> getPossibleMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentOffset : POSSIBLE_MOVES_OFFSETS) {
            final int possiblePosition = this.position + currentOffset;

            if (isExclusion(this.position, currentOffset)) {
                continue;
            }

            if (Piece.checkCoordinate(possiblePosition)) {
                final Tile possibleTile = board.getTile(possiblePosition);
                //check if the tile is NOT occupied
                if (!possibleTile.hasPiece()) {
                    legalMoves.add(new Move.NormalMove(board, this, possiblePosition));
                } else {
                    //if tile is occupied, we need to check which side that piece belongs to
                    final Piece pieceOnTile = possibleTile.getPiece();
                    //we can only move there is it's opposite side piece
                    if (pieceOnTile.getColorSide() != this.getColorSide()) {
                        legalMoves.add(new Move.NormalAttackMove(board, this, possiblePosition, pieceOnTile));
                    }
                }
            }

        }
        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isExclusion(final int currentPosition, final int offset) {
        //if the position is in the first column
        if ((currentPosition % 8 == 0) && (offset == -9 || offset == -1 || offset == 7)) {
            return true;

        }//if the position is in the eighth column
        else if (((currentPosition - 7) % 8 == 0) && (offset == -7 || offset == 1 || offset == 9)) {
            return true;
        }
        //else return false, for the position is not among the exclusive tiles
        return false;
    }

    @Override
    public final boolean hasMoved() {
        if (this.getColorSide() == Side.WHITE && this.getPosition() == Utils.WHITE_KING_BASE_POSITION) {
            return false;
        }
        if (this.getColorSide() == Side.BLACK && this.getPosition() == Utils.BLACK_KING_BASE_POSITION) {
            return false;
        }
        return true;
    }

    @Override
    public King movePiece(Move move) {
        return new King(move.getDestinationCoordinate(), this.getColorSide(), move.isCastlingMove(), false, false);
    }

}
