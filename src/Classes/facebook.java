/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Mohamed Raid Raddaou
 */
public class facebook {
    private String fusername;
    private int fib;
public facebook(){}
    public facebook(String fusername, int fib) {
        this.fusername = fusername;
        this.fib = fib;
    }

    public String getFusername() {
        return fusername;
    }

    public void setFusername(String fusername) {
        this.fusername = fusername;
    }

    public int getFib() {
        return fib;
    }

    public void setFib(int fib) {
        this.fib = fib;
    }
}
