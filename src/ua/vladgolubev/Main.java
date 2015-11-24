package ua.vladgolubev;

import ua.vladgolubev.agreement.*;

public class Main {

    public static void main(String[] args) {
        Organization organization = new Organization("ТернопільБУД");
        Agreement agreement = new Agreement("Доставка будматеріалів", organization);

        agreement.addSpecificationItem("Бетон", 192.2, UnitOfMeasurement.TON);
        agreement.addSpecificationItem("Дротина", 3429.0, UnitOfMeasurement.M);

        System.out.println(agreement);
    }
}
