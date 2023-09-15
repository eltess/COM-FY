package comfy.util;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static comfy.enums.Platform.ANDROID;
import static comfy.util.UiConfiguration.PLATFORM;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.className;

@SuppressWarnings("PMD")
public class ElementUtil {

    public static String getTextFrom(SelenideElement element) {
        return switch(PLATFORM) {
            case IOS -> element.text();
            case ANDROID -> {
                SelenideElement textElement = element.$(className(PLATFORM.getTextClass()));
                yield textElement.exists() ? textElement.text() : "";
            }
        };
    }

    public static SelenideElement getElementWithId(String id) {
        return $(PLATFORM.equals(ANDROID) ? byAttribute("resource-id", id)
            : accessibilityId(id));
    }

    public static ElementsCollection getElementsWithId(String id) {
        return $$(PLATFORM.equals(ANDROID) ? byAttribute("resource-id", id)
            : accessibilityId(id));
    }

    public static SelenideElement getElementContainsId(String id) {
        return $(By.xpath("//*[contains(@%s,'%s')]".formatted(PLATFORM.getIdAttribute(), id)));
    }

    public static ElementsCollection getElementsContainsId(String id) {
        return $$("//*[contains(@%s,'%s')]".formatted(PLATFORM.getIdAttribute(), id));
    }

    public static SelenideElement getElementWithText(String text) {
        return $("//*[@%s='%s']".formatted(PLATFORM.getTextAttribute(), text));
    }
}
