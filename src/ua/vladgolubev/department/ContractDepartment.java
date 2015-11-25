package ua.vladgolubev.department;

import ua.vladgolubev.agreement.Agreement;
import ua.vladgolubev.agreement.Organization;
import ua.vladgolubev.department.delivery.Delivery;

import java.util.List;

public class ContractDepartment {
    private List<Agreement> agreements;
    private List<Delivery> deliveries;
    private List<Organization> organizations;

    private ContractDepartment() {

    }

    // Singleton class holder
    public static class ContractDepartmentHolder {
        public static final ContractDepartment HOLDER_INSTANCE = new ContractDepartment();
    }

    public static ContractDepartment getInstance() {
        return ContractDepartmentHolder.HOLDER_INSTANCE;
    }
}
