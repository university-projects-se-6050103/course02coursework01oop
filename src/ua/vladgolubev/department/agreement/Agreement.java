package ua.vladgolubev.department.agreement;

import ua.vladgolubev.department.delivery.Delivery;

import java.time.LocalDate;
import java.util.Random;

public class Agreement {
    private String title;
    private Organization organization;
    private int number;
    private LocalDate date;
    private AgreementSpecification specification;
    private Delivery delivery;

    private Agreement() {
    }

    public String getTitle() {
        return title;
    }

    public Organization getOrganization() {
        return organization;
    }

    public int getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public AgreementSpecification getSpecification() {
        return specification;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "title='" + title + '\'' +
                ", organization=" + organization +
                ", number=" + number +
                ", date=" + date +
                ", specification=" + specification +
                ", delivery=" + delivery +
                '}';
    }

    public static Builder newBuilder() {
        return new Agreement().new Builder();
    }

    public class Builder {
        private Builder() {
            Agreement.this.number = new Random().nextInt(10000);
            Agreement.this.date = LocalDate.now();
            Agreement.this.specification = new AgreementSpecification();
        }

        public Builder setTitle(String title) {
            Agreement.this.title = title;
            return this;
        }

        public Builder addOrganization(String organizationName) {
            Agreement.this.organization = new Organization(organizationName);
            return this;
        }

        public Builder addSpecificationItem(String name, double amount, UnitOfMeasurement unitOfMeasurement) {
            Agreement.this.specification.addSpecification(new AgreementSpecificationItem(name, amount, unitOfMeasurement));
            return this;
        }

        public Builder addDelivery(String destination, String desiredDate) {
            delivery = Delivery.newBuilder()
                    .setDestinationLocation(destination)
                    .setEstimatedDate(desiredDate)
                    .setSpoiled(false)
                    .build();
            return this;
        }

        public Agreement build() {
            return Agreement.this;
        }
    }
}
