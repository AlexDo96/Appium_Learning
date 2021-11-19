package modules.Lesson_15;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;

import java.time.Duration;

public class Swipe_Vertical {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            // Click Forms label
            MobileElement formsLabel = androidDriver.findElementByAccessibilityId("Forms");
            formsLabel.click();

            // Check to see whether we are on Forms screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

            // Get Mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 90 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            // Convert to PointOptions - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Perform Touch actions
            TouchAction touchAction = new TouchAction(androidDriver);

            // Swipe up
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
                    .moveTo(endPoint)
                    .release()
                    .perform(); // without this, will be no action at all


            // Click on [Active] button
            MobileElement activeBtn = androidDriver.findElementByAccessibilityId("button-Active");
            activeBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
