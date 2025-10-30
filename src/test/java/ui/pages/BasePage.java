package ui.pages;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public abstract class BasePage {

    // Общий метод клика по любому элементу
    protected void click(By locator) {
        $(locator).click();
    }

    // Общий метод ввода текста
    protected void type(SelenideElement locator, String text) {
        $(locator).setValue(text);
    }

    // Проверка видимости элемента
    protected void shouldBeVisible(By locator) {
        $(locator).shouldBe(visible);
    }
}
