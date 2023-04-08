/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.francelino.entidadetest;

/**
 *
 * @author Artur Pereira Neto &lt; artur.neto@ifnmg.edu.br &gt;
 */
public class PessoaJuridica extends Pessoa {

//<editor-fold defaultstate="collapsed" desc="atributos">
    private long cnpj;

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="construtores">
    public PessoaJuridica() {
    }

    public PessoaJuridica(long cnpj) {
        this.cnpj = cnpj;
    }

    public PessoaJuridica(long cnpj, String nome, long id) {
        super(nome, id);
        this.cnpj = cnpj;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="metodos">
    @Override
    public String toString() {
        return "Pessoa Juridica={"
                + "id= " + this.getId()
                + " nome= " + this.getNome()
                + " CNPJ= " + this.getCnpj()
                + "}";
    }

//</editor-fold>
}
