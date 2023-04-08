/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.francelino.entidadetest;

import java.util.Set;

/**
 *
 * @author Your Name &lt;francelino at ifnmg&gt;
 */
public class EntidadeTest {

    public static void main(String[] args) {
        
        Entidade e = new Entidade(123L);
        Entidade p = new Pessoa("Pessoa",123L);
        Entidade pf = new PessoaFisica(43514090610L,"PessoaFisica",123L);
        System.out.println(e);
        System.out.println(p);
        System.out.println(pf);
        AcessoDados ad = new PessoaFisicaAcessoDados();
//        ad.salvar(e);
        ad.salvar(pf);
//ad.salvar(pf);
//        ((PessoaFisicaAcessoDados) ad).salvar(pf);
        
        System.out.println("=================================");
        
        
        PessoaJuridica pj1=new PessoaJuridica();
        
        pj1.setNome("Empresa 1");
        pj1.setId(554433L);
        pj1.setCnpj(111222333444L);
        
        System.out.println(pj1.toString());
        
        PessoaJuridicaAcessoDados adpj=new PessoaJuridicaAcessoDados();
        
        adpj.salvar(pj1);
        
        
    }
}
