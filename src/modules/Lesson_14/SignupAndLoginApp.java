package modules.Lesson_14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;

public class SignupAndLoginApp {
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
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            // Explicit Wait
            WebDriverWait explicitWait = new WebDriverWait(androidDriver, 30);

            // 1. Click Login feature
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            // 2. Switch to Sign up tab
            MobileElement signUpTab = androidDriver.findElementByAccessibilityId("button-sign-up-container");
            signUpTab.click();

            // 3. Input register info
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@content-desc='input-email']"))).sendKeys(userEmail);
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@content-desc='input-password']"))).sendKeys(userPassword);
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@content-desc='input-repeat-password']"))).sendKeys(userPassword);
            MobileElement signUpBtn = androidDriver.findElementByAccessibilityId("button-SIGN UP");
            signUpBtn.click();
            MobileElement signUpMessage = androidDriver.findElementById("android:id/message");
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/message")));
            System.out.println("Sign up result: " + signUpMessage.getText());
            MobileElement okBtn = androidDriver.findElementByXPath("//*[contains(@text, 'OK')]");
            okBtn.click();

            // 4. Login with registered info
            MobileElement loginTab = androidDriver.findElementByAccessibilityId("button-login-container");
            explicitWait.until(ExpectedConditions.elementToBeClickable(loginTab));
            loginTab.click();
            MobileElement emailInput = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']"); // Relative XPath
            MobileElement passwordInput = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']"); // Relative XPath
            MobileElement loginBtn = androidDriver.findElementByAccessibilityId("button-LOGIN");
            MobileElement loginDescription = androidDriver.findElementByXPath("//*[contains(@text, 'When the device')]"); // Conditional XPath
            emailInput.clear();
            passwordInput.clear();
            emailInput.sendKeys(userEmail);
            passwordInput.sendKeys(userPassword);
            System.out.println("Login description: " + loginDescription.getText());
            loginBtn.click();
            MobileElement loginResultDialog = androidDriver.findElementById("android:id/alertTitle");
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));
            System.out.println("Login result: " + loginResultDialog.getText());

        } catch (Exception ignored) {
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
