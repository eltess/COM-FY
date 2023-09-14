package comfy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static comfy.BasePage.LOGGER;

public interface ElementAction {

    String name();

    SelenideElement element();

    default void waiterForElement() {
        LOGGER.info("Wait for element to be visible");
        element().should(Condition.visible);
    }

    @Step("Click = {this.name}")
    default void click() {
        waiterForElement();
        LOGGER.info("Click on " +name());
        element().click();
    }
    @Step("{this.name} is displayed")
    default boolean isDisplayed() {
        waiterForElement();
        LOGGER.info(name() + " is displayed");
        return element().isDisplayed();
    }
    @Step("{this.name} is enabled")
    default boolean isEnabled() {
        waiterForElement();
        LOGGER.info(name() + "is enabled");
        return element().isEnabled();
    }
    @Step("{this.name} is selected")
    default boolean isSelected() {
        waiterForElement();
        LOGGER.info(name()+" is selected");
        return element().isSelected();
    }
}
