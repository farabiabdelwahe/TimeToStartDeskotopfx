/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interfaces;

import Classes.Contribution;
import javafx.collections.ObservableList;

/**
 *
 * @author GSC
 */
public interface IContributionDAO 
{
    
    public void AjoutContribution(Contribution c);
    public void SupprimerContribution(Contribution c );
    public void ModifierContribution(Contribution c);
    public ObservableList<Contribution> AfficherContribution(int id);
    public boolean testspam(String content , String owner , String sujname);
    
}
