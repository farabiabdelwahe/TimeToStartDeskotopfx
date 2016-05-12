/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.text.SimpleDateFormat;

/**
 *
 * @author GSC
 */
public class Rapport {
    private SimpleDateFormat Daterapport ;
    private String  idcontenu;

    public Rapport(SimpleDateFormat Daterapport, String idcontenu) {
        this.Daterapport = Daterapport;
        this.idcontenu = idcontenu;
    }

    public SimpleDateFormat getDaterapport() {
        return Daterapport;
    }

    public String getIdcontenu() {
        return idcontenu;
    }

    public void setDaterapport(SimpleDateFormat Daterapport) {
        this.Daterapport = Daterapport;
    }

    public void setIdcontenu(String idcontenu) {
        this.idcontenu = idcontenu;
    }
    
    
    
    
    
    
}
