/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.francelino.entidadetest;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class Entidade {

//<editor-fold defaultstate="collapsed" desc="atributos">
        private long id;
//</editor-fold>
    

//<editor-fold defaultstate="collapsed" desc="construtores">
    
    public Entidade(){
        
    }
    public Entidade(long id) {
        this.id = id;
    }
//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="metodos">
    
        @Override
    public String toString() {
        return "Entidade{" + "id=" + id + '}';
    }
    
//</editor-fold>
    
    
    

    
    
}
