/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Figurky;

import java.util.ArrayList;
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
public class Knight extends Piece {

    private final static int[] PROBABLE_POSSIBLE_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int position, final Side side) {
        super(position, side);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        int possiblePosition;
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentOffset : PROBABLE_POSSIBLE_MOVES) {
            possiblePosition = this.position + currentOffset;

            if (isExclusion(this.position, currentOffset)) {
                continue;
            }
            if (Piece.checkCoordinate(possiblePosition)) {
                final Tile possibleTile = board.getTile(possiblePosition);
                if (!possibleTile.hasPiece()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtTile = possibleTile.getPiece();
                    if (pieceAtTile.getColorSide() != this.getColorSide()) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * This methods checks if the current pieces is on the tile that does not
     * work according to some of the offset logic. This is true if the tile is
     * in the first, second, seventh or eighth column(file). In those, specific
     * offsets do not work as expected and we need to catch these exceptions.
     *
     * @param currentPosition represents the number representation of the tile
     * on the chess board.
     * @param offset represents offset from current tile, by which we get to the
     * new tile after performing a move.
     * @return true, if the {
     * @param currentPosition} is in the first, second, seventh or eighth
     * column(file)
     */
    private static boolean isExclusion(final int currentPosition, final int offset) {
        //if the position is in the first column
        if ((currentPosition % 8 == 0) && (offset == -17 || offset == -10 || offset == 6 || offset == 15)) {
            return true;
            //if the position is in the second column
        } else if (((currentPosition - 1) % 8 == 0) && (offset == -10 || offset == 6)) {
            return true;
            //if the position is in the seventh column
        } else if (((currentPosition - 6) % 8 == 0) && (offset == -6 || offset == 10)) {
            return true;
            //if the position is in the eighth column
        } else if (((currentPosition - 7) % 8 == 0) && (offset == -15 || offset == -6 || offset == 10 || offset == 17)) {
            return true;
        }
        //else return false, for the position is not among the exclusive tiles
        return false;
    }
}
