package utils.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.PropertyManager;

public abstract class DriverManager {

    AppiumDriver driver;
    DesiredCapabilities cap = new DesiredCapabilities();
    
    protected abstract AppiumDriver createDriver(AppiumDriverLocalService service);
   
    public AppiumDriver getDriver(AppiumDriverLocalService service, String deviceName, String platform) {
        if (null == driver) {
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            cap.setCapability("appActivity",PropertyManager.getInstance().get("application.activity.name"));
            cap.setCapability("appPackage", PropertyManager.getInstance().get("application.package.name"));
            cap.setCapability("avd", deviceName);
            driver = createDriver(service);
        }
        return driver;
    }
}

    
   
