package ua.vladgolubev.department;

import java.util.ArrayList;
import java.util.List;

/**
 * Універсальний звіт, що може містити звітну інформацію.
 * @param <T>
 */
public class Report<T> {
    private List<T> reportItems = new ArrayList<>();

    public Report<T> addReport(T reportItem) {
        reportItems.add(reportItem);
        return this;
    }
}
