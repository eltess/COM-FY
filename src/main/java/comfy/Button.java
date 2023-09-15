package comfy;

import com.codeborne.selenide.SelenideElement;
import comfy.util.UiConfiguration;

import static com.codeborne.selenide.Selenide.$x;

public class Button implements ElementAction {

    private final String buttonName;
    public SelenideElement rootElement;

    public Button(String id, String name) {
        this.rootElement = $x("//*[@%s='%s']".formatted(UiConfiguration.platform.getIdAttribute(), id));
        this.buttonName = "%s %s".formatted(name, "Button");
    }

    @Override
    public String name() {
        return buttonName;
    }

    @Override
    public SelenideElement element() {
        return rootElement;
    }
}
