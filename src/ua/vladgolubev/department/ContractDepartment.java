package ua.vladgolubev.department;

import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.Organization;
import ua.vladgolubev.department.delivery.Delivery;
import ua.vladgolubev.department.delivery.DeliveryPlan;

import java.util.ArrayList;
import java.util.List;

public class ContractDepartment {
    private List<Agreement> agreements;
    private List<DeliveryPlan> deliveryPlans;
    private List<Report> reports;

    private ContractDepartment() {
        agreements = new ArrayList<>();
        deliveryPlans = new ArrayList<>();
        reports = new ArrayList<>();
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

    public void addDeliveryPlan(DeliveryPlan deliveryPlan) {
        deliveryPlans.add(deliveryPlan);
    }

    public List<DeliveryPlan> getDeliveryPlans() {
        return deliveryPlans;
    }

    public List<Report> getReports() {
        return reports;
    }

    public <T> void addReport(T report) {
        this.reports.add(new Report().addReport(report));
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
