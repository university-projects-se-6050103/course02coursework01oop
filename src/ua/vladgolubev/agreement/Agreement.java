package ua.vladgolubev.agreement;

import java.time.LocalDateTime;
import java.util.Random;

public class Agreement {
    private String title;
    private Organization organization;
    private int number;
    private LocalDateTime date;
    private AgreementSpecification specification;

    public Agreement(String title, Organization organization) {
        this.title = title;
        this.organization = organization;
        this.number = new Random().nextInt(10000);
        this.date = LocalDateTime.now();
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

    public LocalDateTime getDate() {
        return date;
    }

    public AgreementSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(AgreementSpecification specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "title='" + title + '\'' +
                ", organization=" + organization +
                ", number=" + number +
                ", date=" + date +
                ", specification=" + specification +
                '}';
    }
}
