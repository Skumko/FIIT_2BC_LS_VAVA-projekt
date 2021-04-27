/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import sk.stu.fiit.Hrac.BlackPlayer;
import sk.stu.fiit.Hrac.Player;
import sk.stu.fiit.Hrac.WhitePlayer;

/**
 *
 * @author Pavol Belej
 */
public enum Side {
    WHITE {
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public Player pickSide(final WhitePlayer whiteP, final BlackPlayer blackP) {
            return whiteP;
        }

        @Override
        public int getOppositeDirection() {
            return 1;
        }
    },
    BLACK {
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public Player pickSide(final WhitePlayer whiteP, final BlackPlayer blackP) {
            return blackP;
        }

        @Override
        public int getOppositeDirection() {
            return -1;
        }
    };

    public abstract int getDirection();

    public abstract int getOppositeDirection();

    public abstract Player pickSide(WhitePlayer whiteP, BlackPlayer blackP);

}
