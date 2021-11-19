package modules.Lesson_16;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import objects.MenuItem;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import utilities.DriverFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HandleHybridContext {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver();

            // Click Webview label
            MobileElement webviewLbl = appiumDriver.findElementByAccessibilityId("Webview");
            webviewLbl.click();

            Thread.sleep(2000);

            appiumDriver.getContextHandles().forEach(context -> { // Lambda expression
                System.out.println("Here are context: " + context);
            });

            // Webview context
            appiumDriver.context("WEBVIEW_com.wdiodemoapp");

            // Click Navigation Toggle Button
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();

            List<MobileElement> menuItems = appiumDriver.findElementsByCssSelector(".menu__list-item a");
            List<MenuItem> menuItemList = new ArrayList<>();

            for (MobileElement menuItem : menuItems) {
                String menuText = menuItem.getText();
                String menuHyperLink = menuItem.getAttribute("href");

                if (StringUtils.isEmpty(menuText)) {
                    menuItemList.add(new MenuItem("GitHub", menuHyperLink));
                } else {
                    menuItemList.add(new MenuItem(menuText, menuHyperLink));
                }
            }

            // List all menu items list
            menuItemList.forEach(System.out::println); // Lambda expression

            // Switch to Native context
            appiumDriver.context("NATIVE_APP");

            // Interact with native context elements
            MobileElement loginLbl = appiumDriver.findElementByAccessibilityId("Login");
            loginLbl.click();

            appiumDriver.runAppInBackground(Duration.ofSeconds(3)); // Run application in background

        } catch (Exception ignored) {
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
