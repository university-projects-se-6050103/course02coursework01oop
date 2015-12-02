package ua.vladgolubev;

import org.jfairy.Fairy;
import org.joda.time.DateTime;
import org.junit.Test;
import ua.vladgolubev.department.ContractDepartment;
import ua.vladgolubev.department.ContractDepartmentSerializer;
import ua.vladgolubev.department.SpecificationsAnalysis;
import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.UnitOfMeasurement;
import ua.vladgolubev.department.delivery.DeliveryPlan;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ContractDepartmentTest {
    private final int defaultNumberOfAgreements = 10;

    public ContractDepartmentTest() {
        ContractDepartment contractDepartment = ContractDepartment.getInstance();

        for (int i = 0; i < defaultNumberOfAgreements && ContractDepartment.getInstance().getAgreements().size() < 10; i++) {
            RandomGenerator rg = new RandomGenerator();
            Agreement agreement = contractDepartment.defineAgreement()
                    .setTitle("Доставка будматеріалів")
                    .addOrganization(rg.organizationName())
                    .addSpecificationItem(
                            rg.specificationName(),
                            rg.doubleNumber(),
                            UnitOfMeasurement.TON
                    )
                    .addSpecificationItem(
                            rg.specificationName(),
                            rg.doubleNumber(),
                            UnitOfMeasurement.M
                    )
                    .addSpecificationItem(
                            rg.specificationName(),
                            rg.doubleNumber(),
                            UnitOfMeasurement.M
                    )
                    .addDelivery(
                            rg.city(),
                            rg.stringDate()
                    )
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
        contractDepartment.addReport(SpecificationsAnalysis.getMostPopularMaterials());
    }

    @Test
    public void testAgreementsGenerated() throws Exception {
        int generatedAgreementsCount = ContractDepartment.getInstance().getAgreements().size();
        assertTrue(generatedAgreementsCount == defaultNumberOfAgreements);
    }

    /**
     * Exported .json file should exist in filesystem.
     *
     * @throws Exception
     */
    @Test
    public void testWritingToFile() throws Exception {
        ContractDepartmentSerializer.storeDepartmentInfo(ContractDepartment.getInstance());
        if (!new File("department.json").exists()) {
            fail("Exported file not found.");
        }
    }
}

/**
 * RandomGenerator class is used to return random dates, strings, etc
 * required for testing contract department.
 */
class RandomGenerator {
    private final Fairy fairy = Fairy.create();
    private final Random random = new Random();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String[] specificationNames = new String[]{"Бетон", "Скло", "Бензин",
            "Електропровід", "Пісок", "Дротина", "Метал"};
    private final String[] organizationNames = new String[]{"Eleks", "SoftServe", "Epam",
            "JSSolutions", "NetCracker", "Luxoft", "GlobalLogic", "Ciklum"};

    public String stringDate() {
        return dateTimeFormatter.format(date());
    }

    public LocalDate date() {
        DateTime dateTime = fairy.dateProducer().randomDateBetweenYears(2010, 2020);
        return LocalDate.of(dateTime.year().get(), dateTime.monthOfYear().get(), dateTime.dayOfMonth().get());
    }

    public String city() {
        return fairy.person().getAddress().getCity();
    }

    public double doubleNumber() {
        return ThreadLocalRandom.current().nextDouble(101, 9928);
    }

    public String specificationName() {
        return specificationNames[random.nextInt(specificationNames.length)];
    }

    public String organizationName() {
        return organizationNames[random.nextInt(organizationNames.length)];
    }
}