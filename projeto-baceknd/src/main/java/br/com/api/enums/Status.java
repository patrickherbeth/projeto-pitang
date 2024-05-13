package br.com.api.enums;

public enum Status {

    ATIVO(0),
    REVOGADO(1),
    REVOGADOSISTEMA(2),
    REVALIDADO(3);

    private Integer id;

    Status(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
