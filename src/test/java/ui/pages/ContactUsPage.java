package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;


import static com.codeborne.selenide.Selenide.$x;

public class ContactUsPage extends BasePage {

    private final SelenideElement getInTouchTitle = $x("//h2[text() = 'Get In Touch']");
    private final SelenideElement nameField = $x("//input[@name='name']");
    private final SelenideElement emailField = $x("//input[@name='email']");
    private final SelenideElement subjectField = $x("//input[@name='subject']");
    private final SelenideElement yourMessageHereField = $x("//textarea[@id='message']");
    public final SelenideElement submitButton = $x("//input[@name='submit']");
    public final SelenideElement selectFileButton = $x("//input[@type='file']");
    public final SelenideElement submitSuccessfullyTitle = $x("//div[text()='Success! Your details have been submitted successfully.']");
    public final SelenideElement homeButton = $x("//div[@id='form-section']");


    public void enterNameEmailSubjectMessage(String name, String email, String subject, String message){
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        subjectField.sendKeys(subject);
        yourMessageHereField.sendKeys(message);
    }

    public boolean getInTouchTitleIsVisible(){
            return getInTouchTitle.is(visible);
        }
}

