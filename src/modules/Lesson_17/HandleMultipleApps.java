package modules.Lesson_17;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;

import java.time.Duration;

public class HandleMultipleApps {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        int length = 8;
        boolean useLetters = true;
        boolean useNumbersForEmail = false;
        boolean useNumbersForPassword = true;
        String generatedStringEmail = RandomStringUtils.random(length, useLetters, useNumbersForEmail);
        String generatedStringPassword = RandomStringUtils.random(length, useLetters, useNumbersForPassword);
        String userEmail = generatedStringEmail + "@gmail.com";
        String userPassword = generatedStringPassword;

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLbl = androidDriver.findElementByAccessibilityId("Login");
            loginLbl.click();

            MobileElement emailInput = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']"); // Relative XPath
            MobileElement passwordInput = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']"); // Relative XPath
            MobileElement loginBtn = androidDriver.findElementByAccessibilityId("button-LOGIN");
            emailInput.sendKeys(userEmail);
            passwordInput.sendKeys(userPassword);
            loginBtn.click();

            // Put webdriverio demo app into background
            androidDriver.runAppInBackground(Duration.ofMillis(-1)); // -1 means to deactivate the app entirely

            // Open Settings application
            androidDriver.activateApp("com.android.settings");

            // Switch Wi-Fi enable
            androidDriver.findElementByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]").click();
            WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
            MobileElement wifiSwitchBtn = androidDriver.findElement(By.id("com.android.settings:id/switchWidget"));
            wait.until(ExpectedConditions.visibilityOf(wifiSwitchBtn));

            boolean isWifiOn = wifiSwitchBtn.getText().equals("ON");
            if (isWifiOn) {
                // Switch to OFF
                wifiSwitchBtn.click();

                // Switch to ON again
                wifiSwitchBtn.click();
            } else {
                wifiSwitchBtn.click();
            }

            // Re-launch WebDriverIO app
            androidDriver.activateApp("com.wdiodemoapp");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
