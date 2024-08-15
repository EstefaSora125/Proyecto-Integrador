package com.Betek.PruebaTecnica.api_rest.model.enums;

import com.Betek.PruebaTecnica.api_rest.exceptions.DifferentTypePatient;
import lombok.Getter;

import java.util.HashMap;

@Getter
public enum TypePatient {
    EPS("EPS"),
    POLIZA("POLIZA"),
    PARTICULAR("PARTICULAR");

    private final String name;
    private static final HashMap<String, TypePatient> hash = new HashMap<>();

    TypePatient(String name) {
        this.name = name;
    }

    static {
        for (TypePatient typePatient: TypePatient.values()){
            hash.put(typePatient.getName(), typePatient);
        }
    }

    public static TypePatient getEnumerate(String name){
        if (hash.get(name.toUpperCase()) != null) {
            return hash.get(name.toUpperCase());
        }else {
            throw new DifferentTypePatient("Tipo de usuario no permitido en el hospital" );
        }
    }

}
