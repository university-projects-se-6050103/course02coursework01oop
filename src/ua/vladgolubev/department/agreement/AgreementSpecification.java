package ua.vladgolubev.department.agreement;

import java.util.ArrayList;
import java.util.List;

/**
 * Специфікація договору, що містить матеріали, що мають буди поставленими за договором.
 */
public class AgreementSpecification {
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
