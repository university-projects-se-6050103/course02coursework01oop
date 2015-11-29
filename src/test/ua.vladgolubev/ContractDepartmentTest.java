package ua.vladgolubev;

import org.jfairy.Fairy;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import ua.vladgolubev.department.ContractDepartment;
import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.UnitOfMeasurement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;;

public class ContractDepartmentTest {
    private final int numberOfAgreements = 10;
    private final Fairy fairy = Fairy.create();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Before
    public void setUp() throws Exception {
        ContractDepartment contractDepartment = ContractDepartment.getInstance();

        for (int i = 0; i < numberOfAgreements; i++) {
            Agreement agreement = contractDepartment.defineAgreement()
                    .setTitle("Доставка будматеріалів")
                    .addOrganization("ТернопільБУД")
                    .addSpecificationItem("Бетон", 192.2, UnitOfMeasurement.TON)
                    .addSpecificationItem("Дротина", 3429.0, UnitOfMeasurement.M)
                    .addDelivery(getRandomCity(), dateTimeFormatter.format(getRandomDate()))
                    .build();

            contractDepartment.signAgreement(agreement);
        }

    }

    @Test
    public void toStringShouldPrint() {
        for (Agreement agreement : ContractDepartment.getInstance().getAgreements()) {
            System.out.println(agreement.getDelivery().getEstimatedDate());
        }
        assertTrue(ContractDepartment.getInstance().toString().length() > 0);
    }

    private LocalDate getRandomDate() {
        DateTime dateTime = fairy.dateProducer().randomDateBetweenYears(2010, 2020);
        return LocalDate.of(dateTime.year().get(), dateTime.monthOfYear().get(), dateTime.dayOfMonth().get());
    }

    private String getRandomCity() {
        return fairy.person().getAddress().getCity();
    }
}
