package br.gov.cesarschool.poo.bonusvendas.dao;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class LancamentoBonusDAO {
    private static final String BRANCO = "";
    private CadastroObjetos cadastro = new CadastroObjetos(LancamentoBonus.class);

    public boolean incluir(LancamentoBonus lancamentoBonus) {
    	String codigoFormatado = BRANCO + lancamentoBonus.getNumeroCaixaDeBonus() + DateTimeFormatter.ofPattern("yyyyMMddhhmmss").format(lancamentoBonus.getDataHoraLancamento());
        LancamentoBonus lancamentoBonusBusca = buscar(codigoFormatado);

        if (lancamentoBonusBusca != null) {
            return false;
        } else {
        	cadastro.incluir(lancamentoBonus, codigoFormatado);
            return true;
        }
    }

    public LancamentoBonus buscar(String codigo) {
        return (LancamentoBonus)cadastro.buscar(BRANCO + codigo);
    }
    
    public void excluir(String codigo) {
    	cadastro.excluir(BRANCO + codigo);
    	
    	LancamentoBonus LancamentoBonus = (LancamentoBonus)buscar(codigo);
    	
    	if (LancamentoBonus == null) {
    		System.out.println("Excluído com sucesso!");
    	} else {
    		System.out.println("Ocorreu um erro!");
    	}
    }
    
    public LancamentoBonus[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(LancamentoBonus.class);
        LancamentoBonus[] prods = new LancamentoBonus[rets.length];
        
        for(int i = 0; i < rets.length; i++) {
            prods[i] = (LancamentoBonus)rets[i];
        }
        return prods;
    }
}
