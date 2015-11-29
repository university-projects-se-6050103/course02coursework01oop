package ua.vladgolubev.department.agreement;

import java.io.Serializable;
import java.time.LocalDate;

public class AgreementSpecificationItem implements Serializable {
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
