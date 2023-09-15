package comfy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static comfy.BasePage.LOGGER;

public interface ElementAction {

    String nameAction();

    SelenideElement elementAction();

    default void waiterForElement() {
        elementAction().should(Condition.visible);
    }

    @Step("Click = {this.name}")
    default void click() {
        LOGGER.info("%s = %s".formatted("Click", nameAction()));
        waiterForElement();
        elementAction().click();
    }

    @Step("Checking the results on the display = {this.name}")
    default boolean isDisplayed() {
        waiterForElement();
        LOGGER.info("%s %s".formatted(nameAction(), "isDisplayed =%s".formatted(elementAction().isDisplayed())));
        return elementAction().isDisplayed();
    }

    default boolean isEnabled() {
        waiterForElement();
        LOGGER.info("%s %s".formatted(nameAction(), "isEnabled =%s".formatted(elementAction().isEnabled())));
        return elementAction().isEnabled();
    }

    default boolean isSelected() {
        waiterForElement();
        LOGGER.info("%s %s".formatted(nameAction(), "isSelected =%s".formatted(elementAction().isSelected())));
        return elementAction().isSelected();
    }
}
