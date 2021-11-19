package modules.Lesson_17;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CaptureScreenshot;
import utilities.CaptureScreenshotType;
import utilities.DriverFactory;

public class TakingScreenShots {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLbl = androidDriver.findElementByAccessibilityId("Login");
            loginLbl.click();

            // Wait until we are on Login Screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
            MobileElement loginBtn = androidDriver.findElementByAccessibilityId("button-LOGIN");
            wait.until(ExpectedConditions.visibilityOf(loginBtn));

            // Taking whole screen
            CaptureScreenshot.captureScreenShot(CaptureScreenshotType.WHOLE_SCREENSHOT, androidDriver, null, "loginForm");

            // Taking element screenshot
            CaptureScreenshot.captureScreenShot(CaptureScreenshotType.ELEMENT_SCREENSHOT, null, loginBtn, "loginBtn");

            // Taking area screenshot
            MobileElement loginNav = androidDriver.findElementByXPath("//android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]");
            CaptureScreenshot.captureScreenShot(CaptureScreenshotType.AREA_SCREENSHOT, null, loginNav, "loginNav");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
