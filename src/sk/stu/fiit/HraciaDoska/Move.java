/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.HraciaDoska;

import sk.stu.fiit.Figurky.Pawn;
import sk.stu.fiit.Figurky.Piece;
import sk.stu.fiit.Figurky.Rook;
import static sk.stu.fiit.HraciaDoska.Board.BoardBuilder;

/**
 *
 * @author Pavol Belej
 */
public abstract class Move {

    protected final Board board;
    protected final Piece movedPiece;
    protected final int destinationCoordinate;
    protected final boolean isFirst;

    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
        this.isFirst = !movedPiece.hasMoved();
    }

    public int getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public int getCurrentCoordinate() {
        return this.movedPiece.getPosition();
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public Piece getAttackedPiece() {
        return null;
    }

    public Board execute() {
        final BoardBuilder builder = new BoardBuilder();

        //get all allied pieces except the moved piece and place them on the new board
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        //get all opponents pieces and place them on the new board
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }

        //move the piece
        builder.setPiece(this.movedPiece.movePiece(this));
        //transfer turn to opponent
        builder.setNextToMove(this.board.getCurrentPlayer().getOpponent().getPlayerSide());
        //build and return the board
        return builder.build();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Move)) {
            return false;
        }
        final Move other = (Move) obj;
        return getCurrentCoordinate() == other.getCurrentCoordinate()
                && getDestinationCoordinate() == other.getDestinationCoordinate()
                && getMovedPiece().equals(other.getMovedPiece());
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + this.destinationCoordinate;
        result = 31 * result + this.movedPiece.hashCode();
        result = 31 * result + this.movedPiece.getPosition();
        return result;
    }

    public static final class NormalMove extends Move {

        public NormalMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof NormalMove && super.equals(other);
        }
    }

    public static class AttackMove extends Move {

        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        @Override
        public Board execute() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isAttack() {
            return true;
        }

        @Override
        public Piece getAttackedPiece() {
            return this.attackedPiece;
        }

        @Override
        public int hashCode() {
            return this.attackedPiece.hashCode() + super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AttackMove)) {
                return false;
            }
            final AttackMove other = (AttackMove) obj;
            return super.equals(other) && getAttackedPiece().equals(other.getAttackedPiece());
        }

    }

    public static final class PawnMove extends Move {

        public PawnMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

    }

    public static class PawnJump extends Move {

        public PawnJump(final Board board,
                final Pawn pieceMoved,
                final int destinationCoordinate) {
            super(board, pieceMoved, destinationCoordinate);
        }

        @Override
        public Board execute() {
            final BoardBuilder builder = new BoardBuilder();
            for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }

            final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setNextToMove(this.board.getCurrentPlayer().getOpponent().getPlayerSide());
            return builder.build();
        }

        @Override
        public String toString() {
            return Utils.getCoordinateNotation(this.destinationCoordinate);
        }

    }

    public static class PawnAttackMove extends AttackMove {

        public PawnAttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }

    }

    public static final class EnPassantMove extends PawnAttackMove {

        public EnPassantMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }

    }

    public static final class CastlingMove extends Move {

        protected final Rook castlingRook;
        protected final int rookStart;
        protected final int rookDestination;

        public CastlingMove(Board board, Piece movedPiece, int destinationCoordinate, final Rook castlingRook, final int rookStart, final int rookDestination) {
            super(board, movedPiece, destinationCoordinate);
            this.castlingRook = castlingRook;
            this.rookStart = rookStart;
            this.rookDestination = rookDestination;
        }

        public Rook getCastlingRook() {
            return castlingRook;
        }

        @Override
        public boolean isCastlingMove() {
            return true;
        }

        @Override
        public Board execute() {
            final BoardBuilder builder = new BoardBuilder();

            for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece) && !this.castlingRook.equals(piece)) {
                    builder.setPiece(piece);
                }
            }

            for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }

            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setPiece(new Rook(this.rookDestination, this.castlingRook.getColorSide()));
            builder.setNextToMove(this.board.getCurrentPlayer().getOpponent().getPlayerSide());
            return builder.build();
        }

        @Override
        public String toString() {
            if (castlingRook.getPosition() == movedPiece.getPosition() + 3) {
                return "0-0";
            } else if (castlingRook.getPosition() == movedPiece.getPosition() - 4) {
                return "0-0-0";
            }

            throw new RuntimeException("PIECES NOT PART OF CASTLING PROCCESS");
        }

    }

    public static class MoveFactory {

        private MoveFactory() {
            throw new RuntimeException("Cannot instantiate");
        }

        public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate) {
            for (final Move move : board.getAllLegalMoves()) {
                if (move.getMovedPiece().getPosition() == currentCoordinate
                        && move.getDestinationCoordinate() == destinationCoordinate) {
                    return move;
                }
            }
            //TO DO
            return null;
        }

    }
}
