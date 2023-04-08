/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.francelino.entidadetest;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class Pessoa
        extends Entidade {
  
//<editor-fold defaultstate="collapsed" desc="atributos">
        private String nome;
//</editor-fold>
     

//<editor-fold defaultstate="collapsed" desc="construtores">
    
    public Pessoa() {
        
    }
    
    public Pessoa(String nome, long id) {
        super(id);
        this.nome = nome;
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="metodos">
    
    @Override
    public String toString() {
        return "Pessoa{" 
                + "id=" + getId() 
                + " nome=" + nome 
                + '}';
    }
    
//</editor-fold>
    
    
    
    

}
