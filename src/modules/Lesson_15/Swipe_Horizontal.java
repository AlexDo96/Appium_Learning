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

public class Swipe_Horizontal {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            // Click Swipe label
            MobileElement swipeLabel = androidDriver.findElementByAccessibilityId("Swipe");
            swipeLabel.click();

            // Check to see whether we are on Swipe screen
            WebDriverWait wait = new WebDriverWait(androidDriver, 30L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text='Swipe horizontal']")));

            // Get Mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 10 * screenWidth / 100;
            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = yStartPoint;

            // Convert to PointOptions - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Perform Touch actions
            TouchAction touchAction = new TouchAction(androidDriver);

            // Swipe
            for (int i = 0; i < 5; i++) {
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform(); // without this, will be no action at all
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
