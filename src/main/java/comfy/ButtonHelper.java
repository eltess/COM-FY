package comfy;

import com.codeborne.selenide.SelenideElement;
import comfy.util.UiConfiguration;

import static com.codeborne.selenide.Selenide.$x;

public class ButtonHelper implements ElementAction {

    private final String buttonName;
    public SelenideElement elementName;

    public ButtonHelper(String id, String name) {
        this.elementName = $x("//*[@%s='%s']".formatted(UiConfiguration.platform.getIdAttribute(), id));
        this.buttonName = "%s %s".formatted(name, "Button");
    }

    @Override
    public String name() {
        return buttonName;
    }

    @Override
    public SelenideElement element() {
        return elementName;
    }
}
