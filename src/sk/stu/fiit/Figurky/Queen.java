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
import sk.stu.fiit.Side;

/**
 *
 * @author palko
 */
public class Queen extends Piece {

    private static final int[] POSSIBLE_MOVES_VECTORS = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(int position, Side side) {
        super(position, side, Type.QUEEN, false);
    }

    public Queen(int position, Side side, boolean hasMoved) {
        super(position, side, Type.QUEEN, hasMoved);
    }

    /**
     * This method calculates all possible moves for certain {@link Queen}
     * piece. Queen combines moves of a {@link Bishop} and a {@link Rook}. That
     * said, queen can move horizontally, vertically and diagonally any number
     * of tiles.
     *
     * @param board represents current {@link Board} on which the game is
     * played.
     * @return {@link ArrayList} of objects of type {@link Move}, which
     * represents all possible moves.
     */
    @Override
    public Collection<Move> getPossibleMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentOffset : POSSIBLE_MOVES_VECTORS) {
            //we set possiblePosition to position of this piece at the start of each cycle
            int possiblePosition = this.position;
            do {

                if (isExclusion(possiblePosition, currentOffset)) {
                    break;
                }
                possiblePosition += currentOffset;
                if (Piece.checkCoordinate(possiblePosition)) {
                    final Tile possibleTile = board.getTile(possiblePosition);
                    //check if the tile is NOT occupied
                    if (!possibleTile.hasPiece()) {
                        legalMoves.add(new Move.NormalMove(board, this, possiblePosition));
                    } else {
                        //if tile is occupied, we need to check which side that piece belongs to
                        final Piece pieceAtTile = possibleTile.getPiece();
                        //we can only move there is it's opposite side piece
                        if (pieceAtTile.getColorSide() != this.getColorSide()) {
                            legalMoves.add(new Move.NormalAttackMove(board, this, possiblePosition, pieceAtTile));
                        }
                        //after we find a blocking piece, queen can no more move in that direction
                        //regardless of the side which that piece belongs to (only knight can jump over pieces)
                        break;
                    }
                }

            } while (Piece.checkCoordinate(possiblePosition));
        }

        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isExclusion(final int currentPosition, final int offset) {
        //if the position is in the first column
        if ((currentPosition % 8 == 0) && (offset == -9 || offset == -1 || offset == 7)) {
            return true;
        } //if the position is in the eighth column
        else if (((currentPosition - 7) % 8 == 0) && (offset == -7 || offset == 1 || offset == 9)) {
            return true;

        }
        return false;
    }

    @Override
    public Queen movePiece(Move move) {
        return new Queen(move.getDestinationCoordinate(), move.getMovedPiece().getColorSide());
    }

}
