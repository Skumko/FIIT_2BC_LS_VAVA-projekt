/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.HraciaDoska;

import sk.stu.fiit.Figurky.Pawn;

/**
 *
 * @author palko
 */
public class Utils {

    public static final int NUM_OF_TILES = 64;
    public static final int WHITE_KING_BASE_POSITION = 60;
    public static final int BLACK_KING_BASE_POSITION = 4;

    public static String getCoordinateNotation(int destinationCoordinate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String getCastlingCapabilites(final Board board) {
        StringBuilder builder = new StringBuilder();
        if (board.getWhiteP().canCastleKingside()) {
            builder.append("K");
        }
        if (board.getWhiteP().canCastleQueenside()) {
            builder.append("Q");
        }
        if (board.getBlackP().canCastleKingside()) {
            builder.append("k");
        }
        if (board.getBlackP().canCastleQueenside()) {
            builder.append("q");
        }
        final String result = builder.toString();
        return result.isEmpty() ? "-" : result;
    }

    public static String getEnPassantTile(final Board board) {
        final Pawn enPassantPawn = board.getEnPassantPawn();
        if (enPassantPawn != null) {
            return getCoordinateNotation(enPassantPawn.getPosition() + 8 * enPassantPawn.getColorSide().getOppositeDirection());
        }
        return "-";
    }

}
