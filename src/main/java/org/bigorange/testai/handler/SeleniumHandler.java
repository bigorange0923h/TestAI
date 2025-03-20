package org.bigorange.testai.handler;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

/**
 * 加载Html
 */
@Component
public class SeleniumHandler {
    @Value("${selenium.browser}")
    private String browserType;
    @Value("${selenium.headless}")
    private boolean headless;
    @Value("${selenium.chromedriver.path}")
    private String chromeDriverPath;

    public String fetchHtml(String url) throws IOException {
        // 1. 设置 ChromeDriver 路径
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        // WebDriver driver = createDriver();
        // 2. 启动 Chrome 浏览器
        WebDriver driver = new ChromeDriver();
        try {
            driver.get(url);
            // 显式等待动态内容加载
            new WebDriverWait(driver, Duration.ofSeconds(10).toMillis())
                    .until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
            return driver.getPageSource();
        } finally {
            driver.quit(); // 确保释放资源
        }
    }

    private WebDriver createDriver() {
        WebDriverManager.getInstance(DriverManagerType.valueOf(browserType.toUpperCase())).setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless");
        return new ChromeDriver(options);
    }

}
