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
import javax.swing.JOptionPane;
import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;
import sk.stu.fiit.HraciaDoska.Move.Promotion;
import sk.stu.fiit.Side;

/**
 *
 * @author palko
 */
public class Pawn extends Piece {

    private static final int[] POSSIBLE_MOVES_OFFSETS = {7, 8, 9, 16};

    public Pawn(int position, Side side) {
        super(position, side, Type.PAWN, false);
        this.hasMoved = hasMoved();
    }

    public Pawn(int position, Side side, boolean hasMoved) {
        super(position, side, Type.PAWN, hasMoved);
        this.hasMoved = hasMoved();
    }

    /**
     * This method calculates all possible moves for certain {@link Pawn} piece.
     * Pawn can move one tile at a time forward, jump two tiles when it is his
     * first move. Attacking moves of a pawn are forward, one tile diagonally.
     * Pawn also has a special move call 'En passant'.
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

            int possiblePosition = this.position + (this.getColorSide().getDirection() * currentOffset);

            if (!Piece.checkCoordinate(possiblePosition)) {
                continue;
            }

            //moving forward to unoccupied tile
            if (currentOffset == 8 && !board.getTile(possiblePosition).hasPiece()) {
                //check if the forward move is actually promoting
                if (this.colorSide.isPromotionTile(possiblePosition)) {
                    legalMoves.add(new Promotion(new Move.PawnMove(board, this, possiblePosition)));
                } else {
                    legalMoves.add(new Move.PawnMove(board, this, possiblePosition));
                }
            } //moving forward two tiles as a first pawn move
            else if (currentOffset == 16 && !this.hasMoved()) {

                //the tile which the pawn jumps over has to be empty
                final int betweenTileCoordinate = this.position + this.getColorSide().getDirection() * 8;
                if (!board.getTile(betweenTileCoordinate).hasPiece() && !board.getTile(possiblePosition).hasPiece()) {
                    legalMoves.add(new Move.PawnJump(board, this, possiblePosition));
                }

            } //exclusion of attacking move when pawns are on 1st or 8th column
            else if (currentOffset == 7
                    && !((this.getColorSide() == Side.WHITE && (this.position - 7) % 8 == 0)
                    || (this.getColorSide() == Side.BLACK && (this.position) % 8 == 0))) {
                if (board.getTile(possiblePosition).hasPiece()) {
                    final Piece pieceOnTile = board.getTile(possiblePosition).getPiece();
                    if (this.getColorSide() != pieceOnTile.getColorSide()) {
                        if (this.colorSide.isPromotionTile(possiblePosition)) {
                            legalMoves.add(new Promotion(new Move.PawnAttackMove(board, this, possiblePosition, pieceOnTile)));
                        } else {
                            legalMoves.add(new Move.PawnAttackMove(board, this, possiblePosition, pieceOnTile));
                        }
                    }
                }
                if (board.getEnPassantPawn() != null) {
                    System.out.println("there is an enpassant pawn");
                    //we have to check for the position of that pawn
                    if (board.getEnPassantPawn().getPosition() == (this.position + this.getColorSide().getOppositeDirection())) {
                        final Piece piece = board.getEnPassantPawn();
                        if (piece.getColorSide() != this.getColorSide()) {
                            legalMoves.add(new Move.EnPassantMove(board, this, possiblePosition, piece));
                        }
                    }
                }
            } else if (currentOffset == 9
                    && !((this.getColorSide() == Side.WHITE && (this.position) % 8 == 0)
                    || (this.getColorSide() == Side.BLACK && (this.position - 7) % 8 == 0))) {
                if (board.getTile(possiblePosition).hasPiece()) {
                    final Piece pieceOnTile = board.getTile(possiblePosition).getPiece();
                    if (this.getColorSide() != pieceOnTile.getColorSide()) {
                        if (this.colorSide.isPromotionTile(possiblePosition)) {
                            legalMoves.add(new Promotion(new Move.PawnAttackMove(board, this, possiblePosition, pieceOnTile)));
                        } else {
                            legalMoves.add(new Move.PawnAttackMove(board, this, possiblePosition, pieceOnTile));
                        }
                    }
                }
                if (board.getEnPassantPawn() != null) {
                    //we have to check for the position of that pawn
                    if (board.getEnPassantPawn().getPosition() == (this.position - this.getColorSide().getOppositeDirection())) {
                        final Piece piece = board.getEnPassantPawn();
                        if (piece.getColorSide() != this.getColorSide()) {
                            legalMoves.add(new Move.EnPassantMove(board, this, possiblePosition, piece));
                        }
                    }
                }

            }

        }

        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public boolean hasMoved() {
        if (this.getColorSide() == Side.WHITE) {
            for (int i = 48; i < 56; i++) {
                if (this.position == i) {
                    return false;
                }
            }
            return true;
        } else if (this.getColorSide() == Side.BLACK) {
            for (int i = 8; i < 16; i++) {
                if (this.position == i) {
                    return false;
                }
            }
            return true;
        }
        throw new RuntimeException("WRONG PIECE COLOR");
    }

    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getColorSide());
    }

    public Piece getPromoted() {
//        String[] options = {"Queen", "Knight", "Bishop", "Rook"};
//        int x = JOptionPane.showOptionDialog(null, "Choose the promotion piece.",
//                "PROMOTION",
//                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//        switch (x) {
//            case 0:
//                return new Queen(this.position, this.colorSide, false);
//            case 1:
//                return new Knight(this.position, this.colorSide, false);
//            case 2:
//                return new Bishop(this.position, this.colorSide, false);
//            case 3:
//                return new Rook(this.position, this.colorSide, false);
//            default:
        return new Queen(this.position, this.colorSide, false);
//        }
    }

}
