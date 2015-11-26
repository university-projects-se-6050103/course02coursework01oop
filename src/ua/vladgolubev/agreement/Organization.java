package ua.vladgolubev.agreement;

public class Organization {
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
