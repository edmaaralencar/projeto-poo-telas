package br.gov.cesarschool.poo.bonusvendas;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CaixaDeBonusDAO caixaDeBonusDao = new CaixaDeBonusDAO();
		
		CaixaDeBonus[] caixaDeBonuses = caixaDeBonusDao.buscarTodos();
		
		CaixaDeBonus caixaDeBonus = caixaDeBonusDao.buscar(Long.parseLong("4981639031020231009"));
		
		System.out.println(caixaDeBonus.getSaldo());
		
		for (int i = 0; i < caixaDeBonuses.length; i++) {
			System.out.println("Código: " + caixaDeBonuses[i].getNumero());
		}

	}

}
