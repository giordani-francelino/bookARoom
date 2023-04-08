/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.francelino.entidadetest;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class PessoaFisicaAcessoDados 
        extends AcessoDados {

    
    @Override
    public String gerarSqlInsercao(Entidade o) {
        
        long id;
        String nome;
        long cpf;
        id = o.getId();
//        nome = ((Pessoa) o).getNome();
      
        nome = ((PessoaFisica)o).getNome();
        cpf = ((PessoaFisica) o).getCpf();


        
        return "INSERT INTO pessoaFisica (" 
                + id + ", '"
                + nome + "', "
                + cpf + ") VALUES(?,?,?)"
        ;
    }
 
    
}
