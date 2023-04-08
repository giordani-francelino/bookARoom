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
    
        public Entidade e1 =new Entidade();
     
    
        public void salvar(Entidade e1) {
        System.out.println(this.gerarSqlInsercao(e1));
    }

    public String gerarSqlInsercao(Entidade e1) {
        //return null;
        return"aqui ej nulo";
    }
    

}
