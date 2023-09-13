package comfy.util;

import comfy.config.UIConfiguration;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import java.net.MalformedURLException;
import java.net.URL;



public class DesiredCapabilities {

    private static final String REMOTE_SERVER_URL = "http://127.0.0.1:4723/wd/hub";

    private final UIConfiguration uiConfiguration = ConfigFactory.create(UIConfiguration.class);

    @Step("Creating a Driver!")
    public AppiumDriver driver() {
        return getLocalEmulatorDriver();
    }

    private AppiumDriver getLocalEmulatorDriver() {
        return uiConfiguration.platformName().equals("android") ? getAndroidLocalDriver() : getIosLocalDriver();
    }

    private AppiumDriver getAndroidLocalDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(uiConfiguration.deviceName());
        options.setApp(uiConfiguration.appLink());
        return new AppiumDriver(getUrl(), options);
    }

    private AppiumDriver getIosLocalDriver() {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(uiConfiguration.deviceName());
        options.setApp(uiConfiguration.appLink());
        return new AppiumDriver(getUrl(), options);
    }

    private URL getUrl() {
        URL remoteUrl;
        try {
            remoteUrl = new URL(url());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return remoteUrl;
    }

    private String url() {
        return REMOTE_SERVER_URL;
    }
}
