package ua.vladgolubev.department.agreement;

import java.io.Serializable;

public class Organization implements Serializable {
    private final String name;

    Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                '}';
    }
}
