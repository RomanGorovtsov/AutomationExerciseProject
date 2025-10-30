package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginSetupPage extends BasePage {

    private final SelenideElement newUserSignUpTitle = $x("//*[text() = 'New User Signup!']");
    private final SelenideElement loginToYourAccountTitle = $x("//h2[text() = 'Login to your account']");
    private final SelenideElement loginToYourAccountEmailField = $x("//h2[text() = 'Login to your account']/parent::div//input[@name='email']");
    private final SelenideElement loginToYourAccountPasswordField = $x("//h2[text() = 'Login to your account']/parent::div//input[@name='password']");
    private final SelenideElement loginButton = $x("//h2[text() = 'Login to your account']/parent::div//button");
    private final SelenideElement signUpNameField = $x("//*[@placeholder='Name']");
    private final SelenideElement signUpEmailAddressField = $x("//*[text() = 'New User Signup!']/parent::div//*[@placeholder='Email Address']");
    private final SelenideElement signUpButton = $x("//button[text() = 'Signup']");
    private final SelenideElement enterAccountInformationTitle = $x("//b[text() = 'Enter Account Information']");
    private final SelenideElement mrTitle = $x("//*[@id='uniform-id_gender1']");
    private final SelenideElement mrsTitle = $x("//*[@id='uniform-id_gender2']");
    private final SelenideElement passwordField = $x("//*[@type='password']");
    private final SelenideElement daysDropdown = $x("//*[@id='days']");
    private final SelenideElement monthsDropdown = $x("//*[@id='months']");
    private final SelenideElement yearsDropdown = $x("//*[@id='years']");
    private final SelenideElement SignUpForOurNewsLetterCheckBox = $x("//*[text() ='Sign up for our newsletter!']/parent::div[@class='checkbox']");
    private final SelenideElement receiveSpecialOffersFromOurPartnersCheckBox = $x("//*[text() ='Receive special offers from our partners!']/parent::div[@class='checkbox']");
    private final SelenideElement firstNameField = $x("//*[@id='first_name']");
    private final SelenideElement lastNameField = $x("//*[@id='last_name']");
    private final SelenideElement companyField = $x("//*[@id='company']");
    private final SelenideElement address1Field = $x("//*[@id='address1']");
    private final SelenideElement address2Field = $x("//*[@id='address2']");
    private final SelenideElement countryDropDown = $x("//*[@id='country']");
    private final SelenideElement stateField = $x("//*[@id='state']");
    private final SelenideElement cityField = $x("//*[@id='city']");
    private final SelenideElement zipCodeField = $x("//*[@id='zipcode']");
    private final SelenideElement mobilePhoneField = $x("//*[@id='mobile_number']");
    private final SelenideElement createAccountButton = $x("//button[text()='Create Account']");
    private final SelenideElement accountCreatedNotification = $x("//*[text() = 'Account Created!']");
    private final SelenideElement continueButton = $x("//a[text() = 'Continue']");
    private final SelenideElement accountDeletedNotification = $x("//*[text() = 'Account Deleted!']");
    private final SelenideElement emailOrPasswordIsIncorrectNotification = $x("//p[text()='Your email or password is incorrect!']");
    private final SelenideElement emailAddressAlreadyExistError = $x("//p[text() = 'Email Address already exist!']");

    public boolean newUserSignUpTitleIsVisible() {
        return newUserSignUpTitle.is(visible);
    }

    public boolean loginToYourAccountTitleIsVisible() {
        return loginToYourAccountTitle.is(visible);
    }

    public boolean emailOrPasswordIsIncorrectNotificationIsVisible() {
        return emailOrPasswordIsIncorrectNotification.is(visible);
    }

    public LoginSetupPage enterNameAndEmailAddress(String name, String emailAddress) {
        type(signUpNameField, name);
        type(signUpEmailAddressField, emailAddress);
        return this;
    }

    public LoginSetupPage enterEmailAndPassword(String email, String password) {
        type(loginToYourAccountEmailField, email);
        type(loginToYourAccountPasswordField, password);
        return this;
    }

    public void clickSignUpButton() {
        signUpButton.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean shouldVisibleAccountCreatedNotification() {
        return accountCreatedNotification.is(visible);
    }

    public void enterAccountInformation(String password, String day, String month, String year) {
        mrTitle.click();
        type(passwordField, password);
        daysDropdown.selectOption(day);
        monthsDropdown.selectOption(month);
        yearsDropdown.selectOption(year);
        SignUpForOurNewsLetterCheckBox.click();
        receiveSpecialOffersFromOurPartnersCheckBox.click();
    }

    public void enterAddressInformation(String name, String lastName, String company, String address1, String address2, String country, String state, String city, String zipCode, String mobilePhone) {
        type(firstNameField, name);
        type(lastNameField, lastName);
        type(companyField, company);
        type(address1Field, address1);
        type(address2Field, address2);
        countryDropDown.selectOption(country);
        type(stateField, state);
        type(cityField, city);
        type(zipCodeField, zipCode);
        type(mobilePhoneField, mobilePhone);
        createAccountButton.click();
    }

    public boolean shouldVisibleEnterAccountInformationTitle() {
        return enterAccountInformationTitle.is(visible);
    }

    public boolean shouldVisibleEmailAddressAlreadyExistError() {
        return emailAddressAlreadyExistError.is(visible);
    }

    public boolean shouldVisibleAccountDeletedNotification() {
        return accountDeletedNotification.is(visible);
    }

    public void clickContinueButton() {
        continueButton.click();
    }
}
