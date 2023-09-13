package comfy.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:appium.properties",})
public interface UIConfiguration extends Config {

    @Key("testPlatform")
    @DefaultValue("saucelabs")
    String executionPlatform();

    @Key("platformName")
    @DefaultValue("android")
    String platformName();

    @Key("deviceName")
    @DefaultValue("emulator-5554")
    String deviceName();

    @Key("appLink")
    String appLink();
}