/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.francelino.entidadetest;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class PessoaFisica
        extends Pessoa {
    
    private long cpf;
//<editor-fold defaultstate="collapsed" desc="construtores">
    
    public PessoaFisica(){
        
    }
    
    public PessoaFisica(long cpf, String nome, long id) {
        super(nome, id);
        this.cpf = cpf;
    }
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public long getCpf() {
        return cpf;
    }
    
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    
//</editor-fold>

    
}
