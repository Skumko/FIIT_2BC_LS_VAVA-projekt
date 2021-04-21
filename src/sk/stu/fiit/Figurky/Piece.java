/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit.Figurky;

/**
 *
 * @author palko
 */
public abstract class Piece {

    //staci vediet ci je biela --> ak biela tak !cierna logicky
    private boolean biela = false;
    private boolean znicena = false;
    protected Typ typ;

    public enum Typ {
        PESIAK, JAZDEC, STRELEC, VEZA, KRAL, KRALOVNA,
    }

    public boolean isZnicena() {
        return znicena;
    }

    public void setZnicena(boolean znicena) {
        this.znicena = znicena;
    }

    public Piece() {
    }

    public Piece(boolean jeBiela) {
        this.biela = jeBiela;
    }

    public boolean isBiela() {
        return biela;
    }

    public Typ getTyp() {
        return typ;
    }

    public abstract boolean mozeHybat();
}
