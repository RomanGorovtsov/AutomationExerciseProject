package ui.pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;


public abstract class BasePage {

    // Общий метод ввода текста по локатору
    protected void type(SelenideElement locator, String text) {
        $(locator).setValue(text);
    }

}
