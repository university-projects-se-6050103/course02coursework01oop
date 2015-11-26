package ua.vladgolubev;

import ua.vladgolubev.agreement.*;
import ua.vladgolubev.department.ContractDepartment;

public class Main {

    public static void main(String[] args) {
        ContractDepartment contractDepartment = ContractDepartment.getInstance();

        Agreement agreement = Agreement.newBuilder()
                .setTitle("Доставка будматеріалів")
                .addOrganization("ТернопільБУД")
                .addSpecificationItem("Бетон", 192.2, UnitOfMeasurement.TON)
                .addSpecificationItem("Дротина", 3429.0, UnitOfMeasurement.M)
                .build();

        System.out.println(agreement);
    }
}
