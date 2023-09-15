package comfy;

import com.codeborne.selenide.SelenideElement;

import static comfy.BasePage.LOGGER;

public interface InputAction {

    String nameInput();

    SelenideElement elementInput();

    default void setValue(String text) {
        elementInput().setValue(text);
    }

    default boolean isEmpty() {
        LOGGER.info("%s %s".formatted(nameInput(), "isEmpty =%s".formatted(elementInput().exists())));
        return elementInput().exists();
    }
}
