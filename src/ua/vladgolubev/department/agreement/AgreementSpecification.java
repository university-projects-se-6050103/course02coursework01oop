package ua.vladgolubev.department.agreement;

import java.util.ArrayList;
import java.util.List;

public class AgreementSpecification {
    private List<AgreementSpecificationItem> items = new ArrayList<AgreementSpecificationItem>();

    void addSpecification(AgreementSpecificationItem item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return "AgreementSpecification{" +
                "items=" + items +
                '}';
    }
}
