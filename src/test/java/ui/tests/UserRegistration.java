package ui.tests;

import org.junit.jupiter.api.Tag;
import ui.pages.LoginSetupPage;
import ui.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

@Tag("ui")
public class UserRegistration extends BaseTest {

    MainPage mainPage;
    LoginSetupPage loginSetupPage;

    private static final String BASE_URL = "https://automationexercise.com/";

    @BeforeEach
    void setUp(){
        open(BASE_URL);
        mainPage = new MainPage();
        loginSetupPage = new LoginSetupPage();
        mainPage.shouldBeOpened();
        mainPage.clickSingUpLogInButton();
        loginSetupPage.newUserSignUpTitleIsVisible();
    }

    @Test
    void testValidUserRegistration() {
        loginSetupPage.enterNameAndEmailAddress("Roman", "roman12345@genius.com");
        loginSetupPage.clickSignUpButton();
        loginSetupPage.shouldVisibleEnterAccountInformationTitle();
        loginSetupPage.enterAccountInformation("Qwerty123", "15", "June", "1996");
        loginSetupPage.enterAddressInformation("Walter", "White", "Grey Brain", "3828 Piermont Drive Northeast", "9800 Montgomery Blvd NE, Albuquerque, NM", "United States", "New Mexico", "Albuquerque", "9800", "(505) 193-0809");
        loginSetupPage.shouldVisibleAccountCreatedNotification();
        loginSetupPage.clickContinueButton();
        mainPage.shouldBeOpened();
        mainPage.clickDeleteAccountButton();
        loginSetupPage.shouldVisibleAccountDeletedNotification();
        loginSetupPage.clickContinueButton();
    }


    @Test
    void registrationWithExistEmailAddress(){
            loginSetupPage.newUserSignUpTitleIsVisible();
            loginSetupPage.enterNameAndEmailAddress("Walter", "walterwhite1958@gmail.com");
            loginSetupPage.clickSignUpButton();
            loginSetupPage.shouldVisibleEmailAddressAlreadyExistError();
    }
}

