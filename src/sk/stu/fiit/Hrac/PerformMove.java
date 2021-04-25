/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Hrac;

import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;

/**
 *
 * @author palko
 */
public class PerformMove {

    private final Board makeMoveBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public enum MoveStatus {
        DONE;

    }

    public PerformMove(Board makeMoveBoard, Move move, MoveStatus moveStatus) {
        this.makeMoveBoard = makeMoveBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return moveStatus;
    }

}
