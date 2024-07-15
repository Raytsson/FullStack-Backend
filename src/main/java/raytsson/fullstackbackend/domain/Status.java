package raytsson.fullstackbackend.domain;

public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    private Integer Codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
        Codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod){
        if (cod ==null){
            return null;
        }
        for (Perfil x : Perfil.values()){
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Invalido");
    }
}
