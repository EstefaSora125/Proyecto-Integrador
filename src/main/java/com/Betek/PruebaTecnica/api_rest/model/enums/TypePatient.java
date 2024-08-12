package com.Betek.PruebaTecnica.api_rest.model.enums;

public enum TypePatient {
    EP("EPS"),
    PO("PÓLIZA"),
    PA("PARTICULAR")
    ;
    private String name;

    TypePatient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
