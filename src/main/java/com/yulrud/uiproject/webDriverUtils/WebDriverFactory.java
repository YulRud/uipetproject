package com.yulrud.uiproject.webDriverUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.yulrud.uiproject.webDriverUtils.Browsers.CHROME;
import static com.yulrud.uiproject.webDriverUtils.Browsers.FIREFOX;

public class WebDriverFactory {
    private static final String WIN_DRIVERS_FOLDER = "drivers/win_drivers";
    private static final String UNIX_DRIVERS_FOLDER = "drivers/unix_drivers";
    private static final String MACOS_DRIVERS_FOLDER = "drivers/macOS_drivers";

    public static WebDriver create(Browsers browser) {
        WebDriver driver = null;
        if (browser == CHROME) {
            System.setProperty("webdriver.chrome.driver", getDriverPath(browser));
            driver = new ChromeDriver();
        } else if (browser == FIREFOX) {
            System.setProperty("webdriver.gecko.driver", getDriverPath(browser));
            driver = new FirefoxDriver();
        }
        return driver;
    }

    private static String getDriverPath(Browsers browser) {
        String path = "";

        if (OSValidator.isWindows()) {
            if (browser == CHROME) {
                path = String.format("%s/chromedriver_win32/chromedriver.exe", WIN_DRIVERS_FOLDER);
            } else if (browser == FIREFOX) {
                path = String.format("%s/geckodriver-v0.29.0-win32/geckodriver.exe", WIN_DRIVERS_FOLDER);
            }
        } else if (OSValidator.isUnix()) {
            if (browser == CHROME) {
                path = String.format("%s/chromedriver_linux64/chromedriver", UNIX_DRIVERS_FOLDER);
            } else if (browser == FIREFOX) {
                path = String.format("%s/geckodriver-v0.29.0-linux32/geckodriver", UNIX_DRIVERS_FOLDER);
            }
        } else if (OSValidator.isMac()) {
            if (browser == CHROME) {
                path = String.format("%s/chromedriver_mac64/chromedriver", MACOS_DRIVERS_FOLDER);
            } else if (browser == FIREFOX) {
                path = String.format("%s/geckodriver-v0.29.0-macos/geckodriver", MACOS_DRIVERS_FOLDER);
            }
        }
        return path;
    }
}
