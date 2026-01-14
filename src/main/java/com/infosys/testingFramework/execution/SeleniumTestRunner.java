package com.infosys.testingFramework.execution;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTestRunner {

    public boolean run(String url) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(url);
            return driver.getTitle() != null && !driver.getTitle().isEmpty();
        } catch (Exception e) {
            return false;
        } finally {
            driver.quit();
        }
    }
}
