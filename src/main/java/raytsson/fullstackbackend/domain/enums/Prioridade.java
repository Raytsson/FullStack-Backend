package raytsson.fullstackbackend.domain;

public enum Prioridade {
    BAIXA(0, "ROLE_BAIXA"),
    MEDIA(1, "ROLE_MEDIA"),
    ALTA(2, "ROLE_ALTA");

    private Integer Codigo;
    private String descricao;

    Prioridade(Integer codigo, String descricao) {
        Codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer cod){
        if (cod ==null){
            return null;
        }
        for (Prioridade x : Prioridade.values()){
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade Invalido");
    }
}
