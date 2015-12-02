package ua.vladgolubev;

import org.jfairy.Fairy;
import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.vladgolubev.department.ContractDepartment;
import ua.vladgolubev.department.ContractDepartmentSerializer;
import ua.vladgolubev.department.SpecificationsAnalysis;
import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.UnitOfMeasurement;
import ua.vladgolubev.department.delivery.DeliveryPlan;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ContractDepartmentTest {
    private static final int defaultNumberOfAgreements = 10;
    private static final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    /**
     * Restore ContractDepartment class instance from file
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        ContractDepartment contractDepartment = ContractDepartmentSerializer.loadDepartmentInfo();
        ContractDepartment.setInstance(contractDepartment);
        System.setOut(new PrintStream(outputContent));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        System.setOut(null);
    }

    /**
     * Fill ContractDepartment with random data
     */
    private static void setUpFromScratch() {
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

    /**
     * Number of generated agreements should be equal with desired generated number
     *
     * @throws Exception
     */
    @Test
    public void testAgreementsGenerated() throws Exception {
        int generatedAgreementsCount = ContractDepartment.getInstance().getAgreements().size();
        assertTrue(generatedAgreementsCount == defaultNumberOfAgreements);
    }

    /**
     * Exporting should not throw exceptions
     *
     * @throws Exception
     */
    @Test
    public void testExport() throws Exception {
        ContractDepartment contractDepartmentInstance = ContractDepartment.getInstance();
        ContractDepartmentSerializer.storeDepartmentInfo(contractDepartmentInstance);
    }

    /**
     * Exported .json file should exist in filesystem.
     *
     * @throws Exception
     */
    @Test
    public void testExportedFileIsWritten() throws Exception {
        ContractDepartmentSerializer.storeDepartmentInfo(ContractDepartment.getInstance());
        if (!new File(ContractDepartmentSerializer.getDefaultJsonPath().toString()).exists()) {
            fail("Exported file not found.");
        }
    }

    /**
     * Imported contract department data should not throw exceptions.
     *
     * @throws Exception
     */
    @Test
    public void testImportFromFile() throws Exception {
        ContractDepartment contractDepartment = ContractDepartmentSerializer.loadDepartmentInfo();
        ContractDepartment.setInstance(contractDepartment);
    }

    /**
     * SpecificationsAnalysis should print to console.
     *
     * @throws Exception
     */
    @Test
    public void testSpecificationAnalysisOutput() throws Exception {
        SpecificationsAnalysis.print();
        assertTrue(outputContent.toString().length() > 0);
        outputContent.reset();
    }

    /**
     * Agreement methods should not throw exceptions
     * @throws Exception
     */
    @Test
    public void testAgreementGetters() throws Exception {
        for (Agreement agreement : ContractDepartment.getInstance().getAgreements()) {
            agreement.getTitle();
            agreement.getOrganization();
            agreement.getDelivery().getEstimatedDate();
            agreement.getDelivery().getDestinationLocation();
            agreement.getNumber();
            agreement.getDate();
            agreement.toString();
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