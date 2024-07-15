package raytsson.fullstackbackend.domain;

public enum Status {
    ABERTO(0, "ROLE_ABERTO"),
    ANDAMENTO(1, "ROLE_ANDAMENTO"),
    ENCERRADO(2, "ROLE_ENCERRADO");

    private Integer Codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        Codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod){
        if (cod ==null){
            return null;
        }
        for (Status x : Status.values()){
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Status Invalido");
    }
}
