package projetopoo.br.gov.cesarschool.poo.bonusvendas.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CaixaDeBonus implements Serializable {
    private static final long serialVersionUID = -8600787050887559618L;
    private long numero;
    private double saldo;
    private java.time.LocalDateTime dataHoraAtualizacao;

    public CaixaDeBonus(long numero) {
        this.numero = numero;
    }

    public long getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public LocalDateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void creditar(double valor) {
        this.saldo = this.saldo + valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public void debitar(double valor) {
        this.saldo = this.saldo - valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }
}
