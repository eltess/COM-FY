package comfy;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static comfy.util.UiConfiguration.PLATFORM;

public class Input implements InputAction, ElementAction {

    private final String name;
    public SelenideElement element;

    public Input(String id, String name) {
        this.element = $x("//*[@%s='%s']".formatted(PLATFORM.getIdAttribute(), id));
        this.name = "%s %s".formatted(name, "Input");
    }

    @Override
    public String nameInput() {
        return name;
    }

    @Override
    public SelenideElement elementInput() {
        return element;
    }

    @Override
    public String nameAction() {
        return name;
    }

    @Override
    public SelenideElement elementAction() {
        return element;
    }

}
