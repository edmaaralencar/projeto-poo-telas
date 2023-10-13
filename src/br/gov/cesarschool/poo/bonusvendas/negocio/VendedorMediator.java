package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.dao.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.StringUtil;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;

public class VendedorMediator {
    private static VendedorMediator instance;
    private VendedorDAO repositorioVendedor;
    private AcumuloResgateMediator caixaDeBonusMediator;

    public VendedorMediator() {
        repositorioVendedor = new VendedorDAO();
        caixaDeBonusMediator = new AcumuloResgateMediator();
    }

    public static synchronized VendedorMediator getInstancia() {
        if (instance == null) {
            instance = new VendedorMediator();
        }
        return instance;
    }
    
    public ResultadoInclusaoVendedor incluir(Vendedor vendedor) {
    	String mensagemValidacao = validar(vendedor);
    	
    	if (mensagemValidacao != null) {
    		return new ResultadoInclusaoVendedor(0, mensagemValidacao);
    	}
    	
    	Vendedor vendedorExiste = repositorioVendedor.buscar(vendedor.getCpf());
    	
    	if (vendedorExiste != null) {
            return new ResultadoInclusaoVendedor(0, "Vendedor ja existente");
    	} else {	
    		repositorioVendedor.incluir(vendedor);
    		
    		long numeroCaixaDeBonus = caixaDeBonusMediator.gerarCaixaDeBonus(vendedor);
    		
    		return new ResultadoInclusaoVendedor(numeroCaixaDeBonus, null);
    	}    	
    }
    
    public String alterar(Vendedor vendedor) {
    	String mensagemValidacao = validar(vendedor);
    	
    	if (mensagemValidacao != null) {
    		return mensagemValidacao;
    	}
    	
    	Vendedor vendedorExiste = repositorioVendedor.buscar(vendedor.getCpf());
    	
    	if (vendedorExiste == null) {
            return "Vendedor inexistente";
    	} else {	
        	repositorioVendedor.alterar(vendedor);
        	
        	return null;
    	}    	
    }
    
    public Vendedor buscar(String cpf) {
    	return repositorioVendedor.buscar(cpf);
    }
    
    private String validar(Vendedor vendedor) {
    	if (StringUtil.ehNuloOuBranco(vendedor.getCpf())) {
    		return "CPF nao informado";
    	}
    	
    	if (!ValidadorCPF.ehCpfValido(vendedor.getCpf())) {
    		return "CPF invalido";
    	}
    	
    	if (StringUtil.ehNuloOuBranco(vendedor.getNomeCompleto())) {
    		return "Nome completo nao informado";
    	}
    	
    	if (vendedor.getSexo() == null) {
    		return "Sexo nao informado";
    	}
    	
    	if (vendedor.getDataNascimento() == null) {
    		return "Data de nascimento nao informada";
    	}
    	
    	if (vendedor.calcularIdade() < 18) {
    		return "Data de nascimento invalida";
    	}
    	
    	if (vendedor.getRenda() < 0) {
    		return "Renda menor que zero";
    	}
    	
    	if (vendedor.getEndereco() == null) {
    		return "Endereco nao informado";
    	}
    	
    	if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getLogradouro())) {
    		return "Logradouro nao informado";
    	}
    	
    	if (vendedor.getEndereco().getLogradouro().length() < 4) {
    		return "Logradouro tem menos de 04 caracteres";
    	}
    	
    	if (vendedor.getEndereco().getNumero() < 0) {
    		return "Numero menor que zero";
    	}
    	
    	if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getCidade())) {
    		return "Cidade nao informada";
    	}
    	
    	if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getEstado())) {
    		return "Estado nao informado";
    	}
    	
    	if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getPais())) {
    		return "Pais nao informado";
    	}
    	
    	return null;
    }
}
