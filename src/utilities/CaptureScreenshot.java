package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

public class CaptureScreenshot {
    public static void captureScreenShot(CaptureScreenshotType captureScreenshotType, AppiumDriver androidDriver, MobileElement mobileElement, String fileName) throws IOException {
        switch (captureScreenshotType) {
            case WHOLE_SCREENSHOT:
                captureWholeScreenshot(androidDriver, fileName);
                break;
            case ELEMENT_SCREENSHOT:
                captureElementScreenshot(mobileElement, fileName);
                break;
            case AREA_SCREENSHOT:
                captureAreaScreenshot(mobileElement, fileName);
                break;
            default:
                throw new IllegalArgumentException(".............");
        }
    }

    public static void captureWholeScreenshot(AppiumDriver androidDriver, String fileName) throws IOException {
        File screenFile = androidDriver.getScreenshotAs(OutputType.FILE);
        String timeStamp = System.currentTimeMillis() / 1000 + "";
        File destFile = new File("./src/screenshots/" + fileName + "-" + timeStamp + ".png");
        FileUtils.copyFile(screenFile, destFile);
    }

    public static void captureElementScreenshot(MobileElement mobileElement, String fileName) throws IOException {
        File screenFile = mobileElement.getScreenshotAs(OutputType.FILE);
        String timeStamp = System.currentTimeMillis() / 1000 + "";
        File destFile = new File("./src/screenshots/" + fileName + "-" + timeStamp + ".png");
        FileUtils.copyFile(screenFile, destFile);
    }

    public static void captureAreaScreenshot(MobileElement mobileElement, String fileName) throws IOException {
        File screenFile = mobileElement.getScreenshotAs(OutputType.FILE);
        String timeStamp = System.currentTimeMillis() / 1000 + "";
        File destFile = new File("./src/screenshots/" + fileName + "-" + timeStamp + ".png");
        FileUtils.copyFile(screenFile, destFile);
    }
}
