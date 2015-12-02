package ua.vladgolubev.department.agreement;

/**
 * Організація, що підписує договір з договірним відділом.
 */
public class Organization {
    private final String name;

    public Organization(String name) {
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
