package ua.vladgolubev;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.vladgolubev.department.ContractDepartment;
import ua.vladgolubev.department.ContractDepartmentSerializer;
import ua.vladgolubev.department.SpecificationsAnalysis;
import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.UnitOfMeasurement;
import ua.vladgolubev.department.delivery.DeliveryPlan;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ContractDepartmentTest {
    private static final int defaultNumberOfAgreements = 10;

    /**
     * Restore ContractDepartment class instance from file
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        ContractDepartment contractDepartment = ContractDepartmentSerializer.loadDepartmentInfo();
        ContractDepartment.setInstance(contractDepartment);
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
     * Imported contract department data should not throw exceptions
     *
     * @throws Exception
     */
    @Test
    public void testImportFromFile() throws Exception {
        ContractDepartment contractDepartment = ContractDepartmentSerializer.loadDepartmentInfo();
        ContractDepartment.setInstance(contractDepartment);
    }
}
