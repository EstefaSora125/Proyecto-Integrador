package com.Betek.PruebaTecnica.api_rest.model.enums;

import lombok.Getter;

@Getter
public enum TypeId {
    CC("Cédula de Ciudadanía"),
    TI("Tarjeta de identidad"),
    CE("Cédula de Extranjería"),
    RI("Registro Civil");

    private final String name;

    TypeId(String name) {
        this.name = name;
    }

}
