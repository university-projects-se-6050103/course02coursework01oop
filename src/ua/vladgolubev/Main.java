package ua.vladgolubev;

import ua.vladgolubev.agreement.*;
import ua.vladgolubev.department.ContractDepartment;

public class Main {

    public static void main(String[] args) {
        ContractDepartment contractDepartment = ContractDepartment.getInstance();

        Organization organization = new Organization("ТернопільБУД");
        Agreement agreement = Agreement.newBuilder()
                .setTitle("Доставка будматеріалів")
                .setOrganization(organization)
                .addSpecificationItem("Бетон", 192.2, UnitOfMeasurement.TON)
                .addSpecificationItem("Дротина", 3429.0, UnitOfMeasurement.M)
                .build();

        System.out.println(agreement);
    }
}
