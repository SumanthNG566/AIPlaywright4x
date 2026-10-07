package com.salesforce.base;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.salesforce.pages.LoginPage;

public abstract class BaseTest {

    private static final String CONFIG_FILE = "config.properties";

    protected final Properties config = new Properties();
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected Duration explicitWait;

    @BeforeTest(alwaysRun = true)
    public void loadConfiguration() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IOException("Configuration file not found on classpath: " + CONFIG_FILE);
            }
            config.load(input);
        }
        explicitWait = Duration.ofSeconds(Long.parseLong(config.getProperty("explicit.wait.seconds", "15")));
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("") String browserParam) {
        String browser = resolve("browser", browserParam, config.getProperty("browser", "chrome"));
        boolean headless = Boolean.parseBoolean(resolve("headless", "", config.getProperty("headless", "false")));
        try {
            driver = createDriver(browser, headless);
            driver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(Long.parseLong(config.getProperty("page.load.timeout.seconds", "30"))));
            driver.manage().window().maximize();
            driver.get(config.getProperty("base.url"));
            loginPage = new LoginPage(driver, explicitWait);
        } catch (WebDriverException e) {
            quitDriver();
            throw new IllegalStateException("Failed to initialise " + browser + " browser session", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }

    @AfterTest(alwaysRun = true)
    public void finalCleanup() {
        quitDriver();
    }

    protected String credential(String propertyKey, String envKey) {
        String value = System.getProperty(propertyKey);
        if (value == null || value.isBlank()) {
            value = System.getenv(envKey);
        }
        return value == null ? "" : value.trim();
    }

    private WebDriver createDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }
                return new EdgeDriver(edgeOptions);
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications", "--remote-allow-origins=*");
                if (headless) {
                    chromeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                }
                return new ChromeDriver(chromeOptions);
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private String resolve(String systemKey, String parameterValue, String fallback) {
        String systemValue = System.getProperty(systemKey);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }
        if (parameterValue != null && !parameterValue.isBlank()) {
            return parameterValue.trim();
        }
        return fallback;
    }

    private void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException e) {
                System.err.println("Driver quit failed: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}
