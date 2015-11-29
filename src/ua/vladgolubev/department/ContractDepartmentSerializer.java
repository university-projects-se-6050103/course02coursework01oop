package ua.vladgolubev.department;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContractDepartmentSerializer {
    private static Gson gson;
    private static Path defaultJsonPath = Paths.get("./department.json");

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public static void storeDepartmentInfo(ContractDepartment contractDepartment) {
        String json = gson.toJson(contractDepartment);
        try {
            Files.write(defaultJsonPath, json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: Unable to write a file");
        }
    }

    public static ContractDepartment loadDepartmentInfo() {
        String json = null;
        try {
            json = new String(Files.readAllBytes(defaultJsonPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: Unable to read a file");
        }
        if (json != null) {
            ContractDepartment contractDepartment = gson.fromJson(json, ContractDepartment.class);
            return contractDepartment;
        } else {
            return ContractDepartment.getInstance();
        }
    }
}
