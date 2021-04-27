/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Hrac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import sk.stu.fiit.Figurky.King;
import sk.stu.fiit.Figurky.Piece;
import sk.stu.fiit.Figurky.Piece.Type;
import sk.stu.fiit.Figurky.Rook;
import static sk.stu.fiit.Hrac.PerformMove.MoveStatus;
import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;
import sk.stu.fiit.HraciaDoska.Tile;
import sk.stu.fiit.Side;

/**
 *
 * @author Pavol Belej
 */
public abstract class Player {

    protected final Board board;
    private final King playerKing;
    private final Collection<Move> legalMoves;
    private final boolean isInCheck;

    public Player(final Board board, final Collection<Move> myLegalMoves, final Collection<Move> opponentLegalMoves) {
        this.board = board;
        this.playerKing = findKing();
        myLegalMoves.addAll(checkCastling(myLegalMoves, opponentLegalMoves));
        this.legalMoves = Collections.unmodifiableCollection(myLegalMoves);
        this.isInCheck = !calculateOpponentsAttacksOnTile(this.playerKing.getPosition(), opponentLegalMoves).isEmpty();

    }

    public abstract Collection<Piece> getActivePieces();

    public abstract Side getPlayerSide();

    public abstract Player getOpponent();

    public King getPlayerKing() {
        return playerKing;
    }

    public Collection<Move> getLegalMoves() {
        return legalMoves;
    }

    protected static Collection<Move> calculateOpponentsAttacksOnTile(final int tileCoordinate, final Collection<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();
        moves.stream().filter(move -> (tileCoordinate == move.getDestinationCoordinate())).forEachOrdered(move -> {
            attackMoves.add(move);
        });
        return Collections.unmodifiableList(attackMoves);
    }

    protected Collection<Move> checkCastling(final Collection<Move> playerMoves, final Collection<Move> opponentMoves) {

        final List<Move> castles = new ArrayList<>();

        final int kingPosition = this.getPlayerKing().getPosition();

        //kingside castle
        if (!getPlayerKing().hasMoved() && !this.isInCheck()) {
            if (!this.board.getTile(kingPosition + 1).hasPiece()
                    && !this.board.getTile(kingPosition + 2).hasPiece()) {

                final Tile kingRookTile = this.board.getTile(kingPosition + 3);
                if (kingRookTile.hasPiece() && !kingRookTile.getPiece().hasMoved()) {

                    if (Player.calculateOpponentsAttacksOnTile(kingPosition + 1, opponentMoves).isEmpty()
                            && Player.calculateOpponentsAttacksOnTile(kingPosition + 2, opponentMoves).isEmpty()
                            && kingRookTile.getPiece().getPieceType() == Type.ROOK) {
                        castles.add(new Move.CastlingMove(this.board, this.playerKing, kingPosition + 2,
                                (Rook) kingRookTile.getPiece(), kingRookTile.getCoordinate(), kingPosition + 1));

                    }

                }
            }
        }

        //queenside castle
        if (!getPlayerKing().hasMoved() && !this.isInCheck()) {
            if (!this.board.getTile(kingPosition - 1).hasPiece()
                    && !this.board.getTile(kingPosition - 2).hasPiece()
                    && !this.board.getTile(kingPosition - 3).hasPiece()) {

                final Tile queenRookTile = this.board.getTile(kingPosition - 4);
                if (queenRookTile.hasPiece() && !queenRookTile.getPiece().hasMoved()) {

                    if (Player.calculateOpponentsAttacksOnTile(kingPosition - 1, opponentMoves).isEmpty()
                            && Player.calculateOpponentsAttacksOnTile(kingPosition - 2, opponentMoves).isEmpty()
                            && Player.calculateOpponentsAttacksOnTile(kingPosition - 3, opponentMoves).isEmpty()
                            && queenRookTile.getPiece().getPieceType() == Type.ROOK) {
                        castles.add(new Move.CastlingMove(this.board, this.playerKing, kingPosition - 2,
                                (Rook) queenRookTile.getPiece(), queenRookTile.getCoordinate(), kingPosition - 1));
                    }
                }
            }
        }

        return Collections.unmodifiableList(castles);

    }

    private King findKing() {
        for (Piece piece : getActivePieces()) {
            if (piece.getPieceType() == Type.KING) {
                return (King) piece;
            }
        }
        //TO DO maybe add logger (also wherever exception are being thrown )
        throw new RuntimeException("THERE IS NO KING ON THE BOARD!");
    }

    public PerformMove makeMove(Move move) {
        if (!this.legalMoves.contains(move)) {
            return new PerformMove(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        //we get the board after performing the move, which has to be legal
        final Board boardAfterMove = move.execute();
        /*after performing that move however we may expose our king to danger,
          and that is not allowed
         */

        //we get all possible moves of the opponent, and check if some of them attack this player's king
        final Collection<Move> attacksOnKing = Player.
                calculateOpponentsAttacksOnTile(boardAfterMove.getCurrentPlayer().getOpponent().getPlayerKing().getPosition(),
                        boardAfterMove.getCurrentPlayer().getLegalMoves());

        if (!attacksOnKing.isEmpty()) {
            return new PerformMove(this.board, move, MoveStatus.KING_IN_CHECK);
        }

        return new PerformMove(boardAfterMove, move, MoveStatus.DONE);
    }

    private boolean canEscape() {
        return this.legalMoves.stream()
                .anyMatch(move -> makeMove(move)
                .getMoveStatus() == MoveStatus.DONE);
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isInCheckMate() {
        return this.isInCheck && !canEscape();
    }

    public boolean isStalemate() {
        return false;
    }

    public boolean canCastleKingside() {
        return this.playerKing.canCastleKingside();
    }

    public boolean canCastleQueenside() {
        return this.playerKing.canCastleQueenside();
    }
}
