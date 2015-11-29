package ua.vladgolubev;

import ua.vladgolubev.department.ContractDepartment;
import ua.vladgolubev.department.ContractDepartmentSerializer;
import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.UnitOfMeasurement;
import ua.vladgolubev.department.delivery.Delivery;

public class Main {

    public static void main(String[] args) {
        ContractDepartment contractDepartment = ContractDepartment.getInstance();

        Agreement agreement = contractDepartment.defineAgreement()
                .setTitle("Доставка будматеріалів")
                .addOrganization("ТернопільБУД")
                .addSpecificationItem("Бетон", 192.2, UnitOfMeasurement.TON)
                .addSpecificationItem("Дротина", 3429.0, UnitOfMeasurement.M)
                .addDelivery("Київ", "22.12.2015")
                .build();

        contractDepartment.signAgreement(agreement);

        System.out.println(contractDepartment);

        ContractDepartmentSerializer.storeDepartmentInfo(contractDepartment);
    }
}
