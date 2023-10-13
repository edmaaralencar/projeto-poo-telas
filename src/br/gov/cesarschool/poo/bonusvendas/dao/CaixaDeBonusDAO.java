package br.gov.cesarschool.poo.bonusvendas.dao;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class CaixaDeBonusDAO {
    private static final String BRANCO = "";
    private CadastroObjetos cadastro = new CadastroObjetos(CaixaDeBonus.class);

    public boolean incluir(CaixaDeBonus caixaDeBonus) {
        CaixaDeBonus caixaDeBonusBusca = buscar(caixaDeBonus.getNumero());

        if (caixaDeBonusBusca != null) {
            return false;
        } else {
            cadastro.incluir(caixaDeBonus, BRANCO + caixaDeBonus.getNumero());
            return true;
        }
    }

    public CaixaDeBonus buscar(long numero) {
        return (CaixaDeBonus)cadastro.buscar(BRANCO + numero);
    }
    
    public void excluir(long numero) {
    	cadastro.excluir(BRANCO + numero);
    	
    	CaixaDeBonus CaixaDeBonus = (CaixaDeBonus)buscar(numero);
    	
    	if (CaixaDeBonus == null) {
    		System.out.println("Excluído com sucesso!");
    	} else {
    		System.out.println("Ocorreu um erro!");
    	}
    }
    
    public void alterar(CaixaDeBonus caixaDeBonus) {
    	cadastro.alterar(caixaDeBonus, BRANCO + caixaDeBonus.getNumero());
    }
    
    public CaixaDeBonus[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(CaixaDeBonus.class);
        CaixaDeBonus[] prods = new CaixaDeBonus[rets.length];
        
        for(int i = 0; i < rets.length; i++) {
            prods[i] = (CaixaDeBonus)rets[i];
        }
        return prods;
    }
}
