package comfy.util;

import comfy.config.UIConfiguration;
import comfy.enums.Platform;
import org.aeonbits.owner.ConfigFactory;

import static comfy.enums.Platform.ANDROID;
import static comfy.enums.Platform.IOS;

public class UiConfiguration {

    private static final UIConfiguration UI_CONFIGURATION = ConfigFactory.create(UIConfiguration.class);
    public static final Platform PLATFORM = "android".equals(UI_CONFIGURATION.platformName()) ? ANDROID : IOS;
}
