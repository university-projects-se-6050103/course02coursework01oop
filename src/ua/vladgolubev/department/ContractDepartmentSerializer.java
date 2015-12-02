package ua.vladgolubev.department;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ContractDepartmentSerializer {
    private static Gson gson;
    private static String defaultFilename = "department.json";
    private static Path defaultJsonPath = Paths.get("./" + defaultFilename);

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    private ContractDepartmentSerializer() {
    }

    public static String getDefaultFilename() {
        return defaultFilename;
    }

    public static Path getDefaultJsonPath() {
        return defaultJsonPath;
    }

    public static void storeDepartmentInfo(ContractDepartment contractDepartment) throws IOException {
        String json = gson.toJson(contractDepartment);
        Files.write(defaultJsonPath, json.getBytes());
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
