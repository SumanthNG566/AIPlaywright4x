package com.salesforce.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMe;

    @FindBy(xpath = "//label[@for='rememberUn']")
    private WebElement rememberMeLabel;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[@id='forgot_password_link']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//img[@id='logo']")
    private WebElement logo;

    public LoginPage(WebDriver driver, Duration timeout) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance must not be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterUsername(String user) {
        type(username, user, "username");
        return this;
    }

    public LoginPage enterPassword(String pass) {
        type(password, pass, "password");
        return this;
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (WebDriverException e) {
            throw new IllegalStateException("Unable to click the Login button", e);
        }
    }

    public LoginPage setRememberMe(boolean selected) {
        try {
            wait.until(ExpectedConditions.visibilityOf(rememberMeLabel));
            if (rememberMe.isSelected() != selected) {
                rememberMeLabel.click();
            }
            wait.until(ExpectedConditions.elementSelectionStateToBe(rememberMe, selected));
            return this;
        } catch (WebDriverException e) {
            throw new IllegalStateException("Unable to set Remember Me to " + selected, e);
        }
    }

    public boolean submitUsername(String user) {
        enterUsername(user);
        clickLogin();
        return waitForPasswordStepOrError();
    }

    public void doLogin(String user, String pass) {
        doLogin(user, pass, false);
    }

    public void doLogin(String user, String pass, boolean remember) {
        if (isElementDisplayed(password)) {
            enterUsername(user);
        } else if (!submitUsername(user)) {
            return;
        }
        enterPassword(pass);
        if (remember) {
            setRememberMe(true);
        }
        clickLogin();
    }

    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(password);
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText().trim();
        } catch (TimeoutException e) {
            throw new IllegalStateException("Login error message was not displayed", e);
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(username));
            return username.isDisplayed()
                    && loginButton.isDisplayed()
                    && rememberMe.isDisplayed()
                    && logo.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isRememberMeSelected() {
        try {
            return rememberMe.isSelected();
        } catch (WebDriverException e) {
            throw new IllegalStateException("Unable to read Remember Me state", e);
        }
    }

    public boolean isForgotPasswordLinkDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(forgotPasswordLink)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isPasswordMasked() {
        try {
            return "password".equalsIgnoreCase(password.getAttribute("type"));
        } catch (WebDriverException e) {
            throw new IllegalStateException("Unable to read password field type", e);
        }
    }

    public String getLoginButtonValue() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginButton)).getAttribute("value");
        } catch (WebDriverException e) {
            throw new IllegalStateException("Unable to read Login button value", e);
        }
    }

    public boolean waitForNavigationAwayFromLogin() {
        try {
            return wait.until(ExpectedConditions.invisibilityOf(loginButton));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isOnLoginPage() {
        try {
            return driver.getCurrentUrl().contains("login.salesforce.com")
                    && !driver.findElements(By.xpath("//input[@id='Login']")).isEmpty();
        } catch (WebDriverException e) {
            throw new IllegalStateException("Unable to determine current page", e);
        }
    }

    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (WebDriverException e) {
            throw new IllegalStateException("Unable to read page title", e);
        }
    }

    private boolean waitForPasswordStepOrError() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(password),
                    ExpectedConditions.visibilityOf(errorMessage)));
            return isElementDisplayed(password) && !isElementDisplayed(errorMessage);
        } catch (TimeoutException e) {
            throw new IllegalStateException("Neither the password step nor an error message appeared after submitting username", e);
        }
    }

    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    private void type(WebElement element, String value, String fieldName) {
        try {
            WebElement field = wait.until(ExpectedConditions.visibilityOf(element));
            field.clear();
            if (value != null && !value.isEmpty()) {
                field.sendKeys(value);
            }
        } catch (WebDriverException e) {
            throw new IllegalStateException("Unable to type into " + fieldName + " field", e);
        }
    }
}
