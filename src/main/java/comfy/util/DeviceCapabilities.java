package comfy.util;

import comfy.config.SauceLabsConfiguration;
import comfy.config.UIConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static comfy.BasePage.LOGGER;

public class DeviceCapabilities {

    private static final String SAUCE_LABS = "saucelabs";

    private static final String REMOTE_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String SAUCE_LABS_URL = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    private final UIConfiguration uiConfiguration = ConfigFactory.create(UIConfiguration.class);
    private final SauceLabsConfiguration sauceLabsConfiguration = ConfigFactory.create(SauceLabsConfiguration.class);
    private static final String TEST_PLATFORM_J = System.getProperty("TEST_PLATFORM_J");

    @Step("Creating a Driver!")
    public AppiumDriver driver() {
        if (TEST_PLATFORM_J != null) {
            return SAUCE_LABS.equals(TEST_PLATFORM_J) ? getSauceLabsDriver() : getLocalEmulatorDriver();
        }
        return SAUCE_LABS.equals(uiConfiguration.executionPlatform()) ? getSauceLabsDriver()
            : getLocalEmulatorDriver();
    }

    private AppiumDriver getLocalEmulatorDriver() {
        return "android".equals(uiConfiguration.platformName()) ? getAndroidLocalDriver() : getIosLocalDriver();
    }

    @Step("Creating Driver for Android!")
    private AppiumDriver getAndroidLocalDriver() {
        LOGGER.info("Creating a Driver for Android!");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(uiConfiguration.deviceName());
        options.setApp(uiConfiguration.appLink());
        return new AppiumDriver(getUrl(), options);
    }

    @Step("Creating Driver for IOS!")
    private AppiumDriver getIosLocalDriver() {
        LOGGER.info("Creating a Driver for IOS!");
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(uiConfiguration.deviceName());
        options.setApp(uiConfiguration.appLink());
        return new AppiumDriver(getUrl(), options);
    }

    @Step("Connection to server")
    private URL getUrl() {
        LOGGER.info("Connection to server");
        URL remoteUrl = null;
        try {
            remoteUrl = new URL(url());
        } catch (MalformedURLException e) {
            LOGGER.info("File Not Found Exception %s".formatted(e));
        }
        return remoteUrl;
    }

    //private final String link = TEST_PLATFORM_J != null ? TEST_PLATFORM_J : uiConfiguration.executionPlatform();

    @Step("Selected PLATFORM = {this.link}")
    private String url() {
        if (TEST_PLATFORM_J != null) {
            return SAUCE_LABS.equals(TEST_PLATFORM_J) ? SAUCE_LABS_URL : REMOTE_SERVER_URL;
        }
        return SAUCE_LABS.equals(uiConfiguration.executionPlatform()) ? SAUCE_LABS_URL : REMOTE_SERVER_URL;
    }

    private AppiumDriver getSauceLabsDriver() {
        return "Android".equals(sauceLabsConfiguration.platformName())
            ? new AndroidDriver(getUrl(), getMutableCapabilities())
            : new IOSDriver(getUrl(), getMutableCapabilities());
    }

    private MutableCapabilities getMutableCapabilities() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", sauceLabsConfiguration.platformName());
        caps.setCapability("appium:app", sauceLabsConfiguration.appSauceLabs());
        caps.setCapability("appium:deviceName", sauceLabsConfiguration.deviceName());
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("appium:platformVersion", sauceLabsConfiguration.platformVersion());
        caps.setCapability("appium:automationName", sauceLabsConfiguration.automationName());

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", sauceLabsConfiguration.sauceUserName());
        sauceOptions.setCapability("accessKey", sauceLabsConfiguration.sauceAccessKey());
        sauceOptions.setCapability("build", "appium-build-ZCSYN");
        sauceOptions.setCapability("name", "<Rozetka>");

        caps.setCapability("sauce:options", sauceOptions);
        return caps;
    }
}
