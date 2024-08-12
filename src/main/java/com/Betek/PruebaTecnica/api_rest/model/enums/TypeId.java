package com.Betek.PruebaTecnica.api_rest.model.enums;

public enum TypeId {
    CC("Cédula de Ciudadanía"),
    TI("Tarjeta de identidad"),
    CE("Cédula de Extranjería"),
    RI("Registro Civil")
    ;

    private String name;

    TypeId(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
