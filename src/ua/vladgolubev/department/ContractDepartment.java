package ua.vladgolubev.department;

import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.Organization;

import java.util.ArrayList;
import java.util.List;

public class ContractDepartment {
    private List<Agreement> agreements;

    private ContractDepartment() {
        agreements = new ArrayList<>();
    }

    public Agreement.Builder defineAgreement() {
        return Agreement.newBuilder();
    }

    public void signAgreement(Agreement agreement) {
        agreements.add(agreement);
    }

    public List<Agreement> getAgreements() {
        return agreements;
    }

    // Singleton class holder
    public static class ContractDepartmentHolder {
        public static ContractDepartment HOLDER_INSTANCE = new ContractDepartment();
    }

    public static ContractDepartment getInstance() {
        return ContractDepartmentHolder.HOLDER_INSTANCE;
    }

    public static void setInstance(ContractDepartment instance) {
        ContractDepartmentHolder.HOLDER_INSTANCE = instance;
    }

    @Override
    public String toString() {
        return "ContractDepartment{" +
                "agreements=" + agreements +
                '}';
    }
}
