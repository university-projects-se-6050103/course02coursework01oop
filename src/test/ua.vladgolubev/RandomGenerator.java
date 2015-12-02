package ua.vladgolubev;

import org.jfairy.Fairy;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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