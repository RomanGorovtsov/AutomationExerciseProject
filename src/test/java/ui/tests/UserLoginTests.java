package ui.tests;

import org.junit.jupiter.api.Tag;
import ui.pages.LoginSetupPage;
import ui.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

@Tag("ui")
public class UserLoginTests {

    MainPage mainPage;
    LoginSetupPage loginSetupPage;

    private static final String BASE_URL = "https://automationexercise.com/";
    private static final String VALID_EMAIL = "walterwhite1958@gmail.com";
    private static final String VALID_PASSWORD = "breakingbad";
    private static final String WRONG_EMAIL = "breakingbad@gmail.com";
    private static final String WRONG_PASSWORD = "breakingbad";

    @BeforeEach
    void setUp() {
        open(BASE_URL);
        mainPage = new MainPage();
        loginSetupPage = new LoginSetupPage();
        mainPage.shouldBeOpened();
    }

    @Test
    void testFailedLoginWithWrongEmailAndPassword(){
        navigateToLoginPage();
        performLogin(WRONG_EMAIL, WRONG_PASSWORD);
        loginSetupPage.emailOrPasswordIsIncorrectNotificationIsVisible();
    }

    @Test
    void testLogOut(){
        navigateToLoginPage();
        performLogin(VALID_EMAIL, VALID_PASSWORD);
        mainPage.shouldVisibleLoggedInAsTittle();
        mainPage.clickLogOutButton();
        loginSetupPage.newUserSignUpTitleIsVisible();
    }


    private void navigateToLoginPage() {
        mainPage.clickSingUpLogInButton();
        loginSetupPage.loginToYourAccountTitleIsVisible();
    }

    private void performLogin(String email, String password) {
        loginSetupPage.enterEmailAndPassword(email, password)
                .clickLoginButton();
    }

}
