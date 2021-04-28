/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.util.Collection;
import sk.stu.fiit.HraciaDoska.Board;
import sk.stu.fiit.HraciaDoska.Move;

/**
 *
 * @author matba
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = Board.createStartBoard();
        System.out.println(board.toString());
        Collection<Move> legalmoves = board.getCurrentPlayer().getLegalMoves();
        for (Move legalmove : legalmoves) {
            System.out.println(legalmove);
        }
    }

}
