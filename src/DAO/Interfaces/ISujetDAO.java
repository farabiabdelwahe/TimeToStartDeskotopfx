/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interfaces;

import Classes.Sujet;
import javafx.collections.ObservableList;

/**
 *
 * @author GSC
 */
public interface ISujetDAO 
{
    
    public void Ajoutsujet(Sujet s);
    public void Supprimersujet(Sujet s );
    public void Modifiersujet(Sujet s);
    public ObservableList<Sujet> AfficherSujet();
    public ObservableList<Sujet> AfficherSujetSelonCategorie(String categ);
       public int returnidcr(int id );
    
}
