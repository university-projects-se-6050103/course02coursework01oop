package ua.vladgolubev.agreement;

import java.util.ArrayList;
import java.util.List;

public class AgreementSpecification {
    private List<AgreementSpecificationItem> items = new ArrayList<AgreementSpecificationItem>();

    public void addSpecification(AgreementSpecificationItem item) {
        items.add(item);
    }
}
