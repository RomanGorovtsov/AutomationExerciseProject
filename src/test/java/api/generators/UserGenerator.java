package api.generators;

import api.models.User;
import com.github.javafaker.Faker;

public class UserGenerator {
    private static final Faker faker = new Faker();

    private static final String[] TITLES = {"Mr", "Mrs"};
    private static final String[] COUNTRIES = {"United States", "United Kingdom", "Canada", "Australia", "India", "Belarus"};

    public static User generateRandomUser() {
        return new User(
                faker.name().username(),
                faker.internet().emailAddress(),
                faker.internet().password(6, 12),
                getRandomTitle(),
                String.valueOf(faker.number().numberBetween(1, 28)),
                String.valueOf(faker.number().numberBetween(1, 12)),
                String.valueOf(faker.number().numberBetween(1970, 2000)),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.company().name(),
                faker.address().streetAddress(),
                getRandomCountry(),
                faker.address().zipCode(),
                faker.address().state(),
                faker.address().city(),
                faker.phoneNumber().cellPhone()
        );
    }

    private static String getRandomTitle() {
        return TITLES[faker.random().nextInt(TITLES.length)];
    }

    private static String getRandomCountry() {
        return COUNTRIES[faker.random().nextInt(COUNTRIES.length)];
    }
}