package br.gov.cesarschool.poo.bonusvendas.entidade;

public enum TipoResgate {
    PRODUTO(1, "Produto"),
    SERVICO(2, "Serviço"),
    CASH(3, "Cash");
    
    private int codigo;
    private String descricao;

    private TipoResgate(int codigo, String descricao) {
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
