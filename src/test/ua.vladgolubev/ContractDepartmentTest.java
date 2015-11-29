package ua.vladgolubev;

import org.junit.Before;
import org.junit.Test;
import ua.vladgolubev.department.ContractDepartment;
import ua.vladgolubev.department.agreement.Agreement;
import ua.vladgolubev.department.agreement.UnitOfMeasurement;

import static org.junit.Assert.assertTrue;

public class ContractDepartmentTest {
    private final int numberOfAgreements = 10;
    private final RandomGenerator rg = new RandomGenerator();

    @Before
    public void setUp() throws Exception {
        ContractDepartment contractDepartment = ContractDepartment.getInstance();

        for (int i = 0; i < numberOfAgreements; i++) {
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
    }

    @Test
    public void testAgreementsGenerated() throws Exception {
        assertTrue(ContractDepartment.getInstance().getAgreements().size() == numberOfAgreements);
    }

    @Test  // To delete
    public void toStringShouldPrint() {
        for (Agreement agreement : ContractDepartment.getInstance().getAgreements()) {
            System.out.println(agreement.getOrganization());
        }
        assertTrue(ContractDepartment.getInstance().toString().length() > 0);
    }

}
