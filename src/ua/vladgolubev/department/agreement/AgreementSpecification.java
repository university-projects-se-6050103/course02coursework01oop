package ua.vladgolubev.department.agreement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgreementSpecification implements Serializable {
    private List<AgreementSpecificationItem> items = new ArrayList<>();

    void addSpecification(AgreementSpecificationItem item) {
        items.add(item);
    }

    public List<AgreementSpecificationItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "AgreementSpecification{" +
                "items=" + items +
                '}';
    }
}
