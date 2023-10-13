package br.gov.cesarschool.poo.bonusvendas.entidade.geral;

public enum Sexo {
    FEMININO(1, "Feminino"),
    MASCULINO(2, "Masculino");
    private int codigo;
    private String descricao;

    private Sexo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
