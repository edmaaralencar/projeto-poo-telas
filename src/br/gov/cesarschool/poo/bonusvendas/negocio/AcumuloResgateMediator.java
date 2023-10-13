package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusDebito;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;

public class AcumuloResgateMediator {
    private static AcumuloResgateMediator instance;
    private CaixaDeBonusDAO repositorioCaixaDeBonus;
    private LancamentoBonusDAO repositorioLancamento;

    public AcumuloResgateMediator() {
        repositorioCaixaDeBonus = new CaixaDeBonusDAO();
        repositorioLancamento = new LancamentoBonusDAO();
    }

    public static synchronized AcumuloResgateMediator getInstancia() {
        if (instance == null) {
            instance = new AcumuloResgateMediator();
        }
        return instance;
    }

    public long gerarCaixaDeBonus(Vendedor vendedor) {
        if (vendedor == null) {
            return 0;
        }

        LocalDate dataAtual =LocalDate.now();
        String dataFormatada =  dataAtual.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        String numeroCaixaDeBonus = vendedor.getCpf().substring(0, vendedor.getCpf().length() - 2) + dataFormatada;

        
        CaixaDeBonus caixaDeBonus = new CaixaDeBonus(Long.parseLong(numeroCaixaDeBonus));
        
        boolean operacaoSucedida = repositorioCaixaDeBonus.incluir(caixaDeBonus);

        if (operacaoSucedida) {
            return Long.parseLong(numeroCaixaDeBonus);
        } else {
            return 0; 
        }
    }

    public String acumularBonus(long numeroCaixaDeBonus, double valor) {
        if (valor <= 0) {
            return "Valor menor ou igual a zero";
        }

        CaixaDeBonus caixaDeBonus = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);

        if (caixaDeBonus == null) {
            return "Caixa de bonus inexistente";
        }

        caixaDeBonus.creditar(valor);
        repositorioCaixaDeBonus.alterar(caixaDeBonus);
        
        LancamentoBonusCredito lancamentoBonusCredito = new LancamentoBonusCredito(caixaDeBonus.getNumero(), valor);
        repositorioLancamento.incluir(lancamentoBonusCredito);
        
        return null;
    }
    
    public String resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipo) {
    	if (valor <= 0) {
            return "Valor menor ou igual a zero";
    	}
    	
    	CaixaDeBonus caixaDeBonus = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
    	
    	if (caixaDeBonus == null) {
    		return "Caixa de bonus inexistente";
    	}
    	
    	if (caixaDeBonus.getSaldo() < valor) {
    		return "Saldo insuficiente";
    	}
    	
    	caixaDeBonus.debitar(valor);
    	
    	repositorioCaixaDeBonus.alterar(caixaDeBonus);
    	
        LancamentoBonusDebito lancamentoDebito = new LancamentoBonusDebito(numeroCaixaDeBonus, valor, tipo);
        repositorioLancamento.incluir(lancamentoDebito);
    	
    	return null;
    }
}
