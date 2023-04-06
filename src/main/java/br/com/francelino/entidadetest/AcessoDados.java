/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.francelino.entidadetest;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class AcessoDados {

    public void salvar(Entidade o) {
        System.out.println(this.gerarSqlInsercao(o));
    }

    public String gerarSqlInsercao(Entidade o) {
        return null;
    }
    

} 