package ua.vladgolubev.department.delivery;

import ua.vladgolubev.agreement.Agreement;

import java.time.LocalDateTime;

public class Delivery {
    private String destinationLocation;
    private Agreement agreement;
    private LocalDateTime estimatedDate;
    private boolean isExpired;

    public Delivery(String destinationLocation, LocalDateTime estimatedDate) {
        this.destinationLocation = destinationLocation;
        this.estimatedDate = estimatedDate;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public LocalDateTime getEstimatedDate() {
        return estimatedDate;
    }

    public boolean isExpired() {
        return isExpired;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "destinationLocation='" + destinationLocation + '\'' +
                ", agreement=" + agreement +
                ", estimatedDate=" + estimatedDate +
                ", isExpired=" + isExpired +
                '}';
    }
}
