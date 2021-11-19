package modules.Lesson_16;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import utilities.DriverFactory;

public class DriverKeyAction {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AndroidDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLbl = appiumDriver.findElementByAccessibilityId("Login");
            loginLbl.click();

            Thread.sleep(2000);

            // Back to Home screen using key BACK
            appiumDriver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

        } catch (Exception ignored) {
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
