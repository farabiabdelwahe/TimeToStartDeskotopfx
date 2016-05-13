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
public class Admin {

        private String id ;
   private String password ;
   private String name ;
   
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", password=" + password + ", name=" + name + '}';
    }

   
 
   
    public Admin(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
    
    
}
