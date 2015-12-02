package ua.vladgolubev.department.agreement;

import java.time.LocalDate;

/**
 * Елемент специфікації, що містить інформацію про матеріал, його кількість та бажану дату поставки.
 */
public class AgreementSpecificationItem {
    private String name;
    private double amount;
    private UnitOfMeasurement unitOfMeasurement;
    private LocalDate date;

    AgreementSpecificationItem(String name, double amount, UnitOfMeasurement unitOfMeasurement) {
        this.name = name;
        this.amount = amount;
        this.unitOfMeasurement = unitOfMeasurement;
        this.date = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "AgreementSpecificationItem{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unitOfMeasurement=" + unitOfMeasurement +
                ", date=" + date +
                '}';
    }
}

/**
 * Одиниця виміру матеріалів поставки.
 */
enum UnitOfMeasurement {
    KG, TON, M
}