/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interfaces;

import Classes.Projet;
import Classes.Sujet;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author GSC
 */
public interface IprojectDAO {
     public boolean Ajoutsujet(Projet p);

    List<Projet> findAllbyuser(int i);
    
    

    
}
