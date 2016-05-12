/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author GSC
 */
public class Newsletter {
       private String nom ; 
    
    private String Discription;

    public Newsletter(String nom, String Discription) {
        this.nom = nom;
        this.Discription = Discription;
    }

    public String getNom() {
        return nom;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    @Override
    public String toString() {
        return "Newsletter{" + "nom=" + nom + ", Discription=" + Discription + '}';
    }
    
 
}
