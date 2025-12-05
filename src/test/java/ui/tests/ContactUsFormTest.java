package ui.tests;

import ui.pages.ContactUsPage;
import ui.pages.LoginSetupPage;
import ui.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class ContactUsFormTest {

    MainPage mainPage;
    LoginSetupPage loginSetupPage;
    ContactUsPage contactUsPage;

    private static final String BASE_URL = "https://automationexercise.com/";

    @BeforeEach
    void setUp(){
        open(BASE_URL);
        mainPage = new MainPage();
        loginSetupPage = new LoginSetupPage();
        contactUsPage = new ContactUsPage();
        mainPage.shouldBeOpened();
        mainPage.clickContactUsButton();
        contactUsPage.getInTouchTitleIsVisible();
    }
}
