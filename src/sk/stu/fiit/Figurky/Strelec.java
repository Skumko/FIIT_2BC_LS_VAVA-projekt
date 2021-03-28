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
public class Strelec extends Figurka {

    public Strelec(boolean jeBiela) {
        super(jeBiela);
        this.typ = Typ.STRELEC;
    }

    @Override
    public boolean mozeHybat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
