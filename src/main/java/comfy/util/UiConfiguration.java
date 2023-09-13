package comfy.util;


import comfy.enums.Platform;

import static comfy.enums.Platform.ANDROID;
import static comfy.enums.Platform.IOS;
import static comfy.util.ReadProperties.readProperties;

public class UiConfiguration {

    public static Platform PLATFORM = readProperties().getProperty("platform").equals("android") ? ANDROID : IOS;
}
