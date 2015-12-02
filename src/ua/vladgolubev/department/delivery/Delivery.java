package ua.vladgolubev.department.delivery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Delivery {
    private String destinationLocation;
    private LocalDate estimatedDate;
    private boolean spoiled;

    private Delivery() {
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }


    public LocalDate getEstimatedDate() {
        return estimatedDate;
    }

    public boolean isSpoiled() {
        return spoiled;
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
        private DateTimeFormatter dateFormat;

        private Builder() {
            this.dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        }

        public Builder setDestinationLocation(String destinationLocation) {
            Delivery.this.destinationLocation = destinationLocation;
            return this;
        }

        public Builder setEstimatedDate(String date) {
            Delivery.this.estimatedDate = LocalDate.from(dateFormat.parse(date));
            return this;
        }

        public Builder setSpoiled(boolean spoiled) {
            Delivery.this.spoiled = spoiled;
            return this;
        }

        public Delivery build() {
            return Delivery.this;
        }
    }
}
