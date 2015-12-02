package ua.vladgolubev;

import org.jfairy.Fairy;
import org.joda.time.DateTime;
import org.junit.Test;
import ua.vladgolubev.department.ContractDepartment;
import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.UnitOfMeasurement;
import ua.vladgolubev.department.delivery.DeliveryPlan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

public class ContractDepartmentTest {
    private final int defaultNumberOfAgreements = 10;

    public ContractDepartmentTest() {
        ContractDepartment contractDepartment = ContractDepartment.getInstance();

        for (int i = 0; i < defaultNumberOfAgreements && ContractDepartment.getInstance().getAgreements().size() < 10; i++) {
            RandomGenerator rg = new RandomGenerator();
            Agreement agreement = contractDepartment.defineAgreement()
                    .setTitle("Доставка будматеріалів")
                    .addOrganization(rg.getRandomOrganizationName())
                    .addSpecificationItem(rg.getRandomSpecificationName(), rg.getRandomDouble(), UnitOfMeasurement.TON)
                    .addSpecificationItem(rg.getRandomSpecificationName(), rg.getRandomDouble(), UnitOfMeasurement.M)
                    .addSpecificationItem(rg.getRandomSpecificationName(), rg.getRandomDouble(), UnitOfMeasurement.M)
                    .addDelivery(rg.getRandomCity(), rg.getRandomStringDate())
                    .build();

            contractDepartment.signAgreement(agreement);
        }

        contractDepartment.addDeliveryPlan(
                new DeliveryPlan()
                        .planDelivery(contractDepartment.getAgreements().get(2).getDelivery())
                        .planDelivery(contractDepartment.getAgreements().get(4).getDelivery()));
        contractDepartment.addDeliveryPlan(
                new DeliveryPlan()
                        .planDelivery(contractDepartment.getAgreements().get(1).getDelivery())
                        .planDelivery(contractDepartment.getAgreements().get(7).getDelivery()));
    }

    @Test
    public void testAgreementsGenerated() throws Exception {
        int generatedAgreementsCount = ContractDepartment.getInstance().getAgreements().size();
        assertTrue(generatedAgreementsCount == defaultNumberOfAgreements);
    }
}

class RandomGenerator {
    private final Fairy fairy = Fairy.create();
    private final Random random = new Random();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String[] specificationNames = new String[]{
            "Бетон",
            "Скло",
            "Бензин",
            "Електропровід",
            "Пісок",
            "Дротина",
            "Метал"
    };
    private final String[] organizationNames = new String[]{
            "Eleks",
            "SoftServe",
            "Epam",
            "JSSolutions",
            "NetCracker",
            "Luxoft",
            "GlobalLogic",
            "Ciklum"
    };


    public String getRandomStringDate() {
        return dateTimeFormatter.format(getRandomDate());
    }

    public LocalDate getRandomDate() {
        DateTime dateTime = fairy.dateProducer().randomDateBetweenYears(2010, 2020);
        return LocalDate.of(dateTime.year().get(), dateTime.monthOfYear().get(), dateTime.dayOfMonth().get());
    }

    public String getRandomCity() {
        return fairy.person().getAddress().getCity();
    }

    public double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble(101, 9928);
    }

    public String getRandomSpecificationName() {
        return specificationNames[random.nextInt(specificationNames.length)];
    }

    public String getRandomOrganizationName() {
        return organizationNames[random.nextInt(organizationNames.length)];
    }
}