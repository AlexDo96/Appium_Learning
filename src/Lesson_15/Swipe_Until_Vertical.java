package Lesson_15;

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
import java.util.List;

public class Swipe_Until_Vertical {
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
            int xStartPoint = 30 * screenWidth / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 90 * screenHeight / 100;
            int yEndPoint = 40 * screenHeight / 100;

            // Convert to PointOptions - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Perform Touch actions
            TouchAction touchAction = new TouchAction(androidDriver);
            int MAX_SWIPE_TIME = 5;
            int swipeTime = 0;

            while (swipeTime < MAX_SWIPE_TIME) {
                // Using findElements -> When element is found, put it in List<MobileElement>
                List<MobileElement> matchedCards = androidDriver.findElementsByXPath("//*[@text='You found me!!!']");
                if (!matchedCards.isEmpty()) {
                    break;
                }

                // Swipe
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform(); // without this, will be no action at all

                swipeTime++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
