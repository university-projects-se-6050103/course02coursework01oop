package ua.vladgolubev.agreement;

import java.time.LocalDateTime;

public class AgreementSpecificationItem {
    private String name;
    private double amount;
    private UnitOfMeasurement unitOfMeasurement;
    private LocalDateTime date;

    public AgreementSpecificationItem(String name, double amount, UnitOfMeasurement unitOfMeasurement) {
        this.name = name;
        this.amount = amount;
        this.unitOfMeasurement = unitOfMeasurement;
        this.date = LocalDateTime.now();
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

    public LocalDateTime getDate() {
        return date;
    }
}
