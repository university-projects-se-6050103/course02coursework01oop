package ua.vladgolubev.department;

import ua.vladgolubev.department.engineer.Engineer;

import java.util.ArrayList;
import java.util.List;

public class ContractDepartment {
    private List<Engineer> engineers;

    public ContractDepartment() {
        this.engineers = new ArrayList<Engineer>();
    }

    public List<Engineer> getEngineers() {
        return engineers;
    }
}
