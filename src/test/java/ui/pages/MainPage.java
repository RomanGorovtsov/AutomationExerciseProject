package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends BasePage {

    private final SelenideElement mainPageMarker = $x("//*[text() = 'Full-Fledged practice website for Automation Engineers']");
    private final SelenideElement singUpLogInButton = $x("//*[text() = ' Signup / Login']");
    private final SelenideElement loggedInAsTittle = $x("//*[contains(text(), ' Logged in as ')]");
    private final SelenideElement deleteAccountButton = $x("//*[text() = ' Delete Account']");
    private final SelenideElement logOutButton = $x("//*[text() = ' Logout']");
    private final SelenideElement contactUsButton = $x("//a[text() = ' Contact us']");

    public SelenideElement shouldBeOpened() {
        return mainPageMarker.shouldBe(visible);
    }

    public void clickSingUpLogInButton() {
        singUpLogInButton.click();
    }

    public void clickContactUsButton() {
        contactUsButton.click();
    }

    public void clickDeleteAccountButton() {
        deleteAccountButton.click();
    }

    public void clickLogOutButton() {
        logOutButton.click();
    }

    public SelenideElement shouldVisibleLoggedInAsTittle() {
        return loggedInAsTittle.shouldBe(visible);
    }
}
