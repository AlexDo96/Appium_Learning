package modules.Lesson_17;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import utilities.DriverFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwipeToOpenNotification {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

            // Get Mobile window size
            Dimension windowSize = androidDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = xStartPoint;
            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

            // Convert to PointOptions - Coordinates
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Perform Touch actions
            TouchAction touchAction = new TouchAction(androidDriver);

            // Swipe down to display notification
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
                    .moveTo(endPoint)
                    .release()
                    .perform(); // without this, will be no action at all

            Thread.sleep(2000);

            // Get the info inside the notifications by getting a list
            List<MobileElement> notificationElems = androidDriver.findElements(By.id("android:id/notification_main_column"));

            if (notificationElems.isEmpty()) {
                throw new RuntimeException("Notification List is empty");
            }

            Map<String, String> notificationList = new HashMap<>(); // Get notifications as key, value

            notificationElems.forEach(notification -> {
                String notificationTitle = notification.findElement(By.id("android:id/title")).getText();
                String notificationContent = notification.findElement(By.id("android:id/big_text")).getText();
                notificationList.put(notificationTitle, notificationContent); // Add notifications to notificationList (HashMap)
            });

            // Swipe up to hide notification
            touchAction
                    .press(endPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
                    .moveTo(startPoint)
                    .release()
                    .perform(); // without this, will be no action at all

            notificationList.keySet().forEach(key -> {
                System.out.println(key + ": " + notificationList.get(key));
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
