package ua.vladgolubev.department.agreement;

import java.io.Serializable;

public class Material implements Serializable {
    private final String name;

    public Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
