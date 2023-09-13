package comfy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

public interface ElementAction {
    Logger LOGGER = Logger.getLogger(ElementAction.class);

    String name();

    SelenideElement element();

    default void waiterForElement() {
        LOGGER.info("Wait for element to be visible");
        element().should(Condition.visible);
    }

    @Step("Click on element")
    default void click() {
        waiterForElement();
        LOGGER.info("Click on element");
        element().click();
    }
    @Step("Element is displayed")
    default boolean isDisplayed() {
        waiterForElement();
        LOGGER.info("Element is displayed");
        return element().isDisplayed();
    }
    @Step("Element is enabled")
    default boolean isEnabled() {
        waiterForElement();
        LOGGER.info("Element is enabled");
        return element().isEnabled();
    }
    @Step("Element is selected")
    default boolean isSelected() {
        waiterForElement();
        LOGGER.info("Element is selected");
        return element().isSelected();
    }
}
