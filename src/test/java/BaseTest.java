import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import comfy.enums.Platform;
import comfy.util.DeviceCapabilities;
import comfy.util.UiConfiguration;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected SoftAssert softAssert;
    protected static Platform PLATFORM = UiConfiguration.PLATFORM;

    public BaseTest() {

    }

    @BeforeClass
    public void before() {
        DeviceCapabilities capabilities = new DeviceCapabilities();
        AppiumDriver appiumDriver = capabilities.driver();
        WebDriverRunner.setWebDriver(appiumDriver);

        Configuration.reportsFolder = "target/test-result/reports";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
            .screenshots(true)
            .savePageSource(true));

    }

    @BeforeMethod
    public void resetSoftAssert() {
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void after() {
        WebDriverRunner.getWebDriver().quit();
    }
}
