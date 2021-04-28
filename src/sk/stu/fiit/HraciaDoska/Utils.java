/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.HraciaDoska;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import sk.stu.fiit.Figurky.Pawn;

/**
 *
 * @author palko
 */
public class Utils {

    public static final int NUM_OF_TILES = 64;
    public static final int WHITE_KING_BASE_POSITION = 60;
    public static final int BLACK_KING_BASE_POSITION = 4;
    public static final List<String> NOTATIONS = createNotationList();

    public static String getCoordinateNotation(int destinationCoordinate) {
        return NOTATIONS.get(destinationCoordinate);

    }

    private static List<String> createNotationList() {
        return Collections.unmodifiableList(Arrays.asList(
                "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
                "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
                "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
                "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
                "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
                "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
                "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
                "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"));
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
