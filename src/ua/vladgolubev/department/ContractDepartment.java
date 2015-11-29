package ua.vladgolubev.department;

import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.Organization;
import ua.vladgolubev.department.delivery.Delivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContractDepartment implements Serializable {
    private List<Agreement> agreements;
    private List<Organization> organizations;

    private ContractDepartment() {
        agreements = new ArrayList<Agreement>();
        organizations = new ArrayList<Organization>();
    }

    public Agreement.Builder defineAgreement() {
        return Agreement.newBuilder();
    }

    public void signAgreement(Agreement agreement) {
        agreements.add(agreement);
        organizations.add(agreement.getOrganization());
    }

    // Singleton class holder
    public static class ContractDepartmentHolder {
        public static final ContractDepartment HOLDER_INSTANCE = new ContractDepartment();
    }

    public static ContractDepartment getInstance() {
        return ContractDepartmentHolder.HOLDER_INSTANCE;
    }

    @Override
    public String toString() {
        return "ContractDepartment{" +
                "agreements=" + agreements +
                ", organizations=" + organizations +
                '}';
    }
}
