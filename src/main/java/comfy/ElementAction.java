package comfy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static comfy.BasePage.LOGGER;

public interface ElementAction {

    String name();

    SelenideElement element();

    @Step("Wait for {this.name}")
    default void waiterForElement() {
        LOGGER.info("Wait for element to be visible");
        element().should(Condition.visible);
    }

    @Step("Click = {this.name}")
    default void click() {
        waiterForElement();
        LOGGER.info("Click on element");
        element().click();
    }

    @Step("{this.name} is displayed")
    default boolean isDisplayed() {
        waiterForElement();
        LOGGER.info("Element is displayed");
        return element().isDisplayed();
    }

    @Step("{this.name} is enabled")
    default boolean isEnabled() {
        waiterForElement();
        LOGGER.info("Element is enabled");
        return element().isEnabled();
    }

    @Step("{this.name} is selected")
    default boolean isSelected() {
        waiterForElement();
        LOGGER.info("Element is selected");
        return element().isSelected();
    }
}
