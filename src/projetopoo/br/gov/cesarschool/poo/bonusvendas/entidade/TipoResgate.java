package projetopoo.br.gov.cesarschool.poo.bonusvendas.entidade;

public enum TipoResgate {
    produto(1, "Produto"),
    servico(2, "Serviço"),
    cash(3, "Cash");
    
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
