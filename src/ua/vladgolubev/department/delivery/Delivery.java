package ua.vladgolubev.department.delivery;

import ua.vladgolubev.department.agreement.Agreement;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Delivery implements Serializable {
    private String destinationLocation;
    private Agreement agreement;
    private LocalDateTime estimatedDate;

    private Delivery() { }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public LocalDateTime getEstimatedDate() {
        return estimatedDate;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "destinationLocation='" + destinationLocation + '\'' +
                ", estimatedDate=" + estimatedDate +
                '}';
    }

    public static Builder newBuilder() {
        return new Delivery().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setDestinationLocation(String destinationLocation) {
            Delivery.this.destinationLocation = destinationLocation;
            return this;
        }

        public Builder setAgreement(Agreement agreement) {
            Delivery.this.agreement = agreement;
            return this;
        }

        public Delivery build() {
            return Delivery.this;
        }
    }
}
