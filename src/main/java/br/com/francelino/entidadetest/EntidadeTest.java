/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.francelino.entidadetest;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class EntidadeTest {

    public static void main(String[] args) {
        Entidade e = new Entidade(123L);
        Entidade p = new Pessoa("Pessoa",123L);
        Entidade pf = new PessoaFisica(43514090610L,"PessoaFisica1",123L);
        System.out.println(e);
        System.out.println(p);
        System.out.println(pf);
        AcessoDados ad = new PessoaFisicaAcessoDados();
        ad.salvar(e);
//        ad.salvar(p);
ad.salvar(pf);
        ((PessoaFisicaAcessoDados) ad).salvar(pf);
        
    }
}
