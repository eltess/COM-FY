package comfy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public interface ElementAction {

    String name();

    SelenideElement element();

    default void waiterForElement() {
        element().should(Condition.visible);
    }


    default void click() {
        waiterForElement();
        element().click();
    }

    default boolean isDisplayed() {
        waiterForElement();
        return element().isDisplayed();
    }

    default boolean isEnabled() {
        waiterForElement();
        return element().isEnabled();
    }

    default boolean isSelected() {
        waiterForElement();
        return element().isSelected();
    }
}
