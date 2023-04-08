/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.francelino.entidadetest;

/**
 *
 * @author Artur Pereira Neto &lt; artur.neto@ifnmg.edu.br &gt;
 */
public class PessoaJuridicaAcessoDados
             extends AcessoDados{

    @Override
    public String gerarSqlInsercao(Entidade o) {
      
        long id;
        String nome;
        long cnpj;
        id = o.getId();
//        nome = ((Pessoa) o).getNome();
       
        nome = ((PessoaJuridica)o).getNome();
        cnpj = ((PessoaJuridica) o).getCnpj();


        
        return "\"INSERT INTO pessoaJuridica (" 
                + id + ", '"
                + nome + "', "
                + cnpj + ") VALUES(?,?,?);\""
        ;
        
        
    }
    
    
    
    
    
}
