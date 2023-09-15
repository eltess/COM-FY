package comfy;

import com.codeborne.selenide.SelenideElement;
import comfy.util.UiConfiguration;

import static com.codeborne.selenide.Selenide.$x;

public class Button implements ElementAction {

    private final String name;
    public SelenideElement element;

    public Button(String id, String name) {
        this.element = $x("//*[@%s='%s']".formatted(UiConfiguration.PLATFORM.getIdAttribute(), id));
        this.name = "%s %s".formatted(name, "Button");
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
