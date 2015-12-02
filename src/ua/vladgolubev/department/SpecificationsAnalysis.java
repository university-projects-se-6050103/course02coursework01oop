package ua.vladgolubev.department;

import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.AgreementSpecificationItem;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

public class SpecificationsAnalysis {
    private static Map<String, Double> specificationSummary = new HashMap<>();

    public static void printMostPopularMaterials() {
        summarizeMaterialsData();

        System.out.println("Specifications Analysis. Most popular materials");
        int i = 0;
        for (Map.Entry<String, Double> entry : specificationSummary.entrySet()) {
            if (i < 5) {
                System.out.println(++i + ". " + entry.getKey() + ": " + Math.round(entry.getValue()));
            }
        }
    }

    private static void summarizeMaterialsData() {
        ContractDepartment contractDepartment = ContractDepartment.getInstance();
        for (Agreement agreement : contractDepartment.getAgreements()) {
            for (AgreementSpecificationItem agreementSpecificationItem : agreement.getSpecification().getItems()) {
                Double previousValue = specificationSummary.get(agreementSpecificationItem.getName());
                if (previousValue != null) {
                    specificationSummary.put(
                            agreementSpecificationItem.getName(),
                            previousValue + agreementSpecificationItem.getAmount());
                } else {
                    specificationSummary.put(agreementSpecificationItem.getName(), agreementSpecificationItem.getAmount());
                }
            }
        }
        sortSpecificationsInAscendingOrder();
    }

    private static void sortSpecificationsInAscendingOrder() {
        specificationSummary = specificationSummary.entrySet().stream()
                .sorted(Collections.reverseOrder(comparing(Map.Entry::getValue)))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
