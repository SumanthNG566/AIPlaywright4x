package com.salesforce.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.salesforce.base.BaseTest;

public class InvalidLoginTest extends BaseTest {

    private static final String INVALID_CREDENTIALS_MSG = "Please check your username and password";
    private static final String EMPTY_USERNAME_MSG = "Please enter your username";
    private static final String EMPTY_PASSWORD_MSG = "Please enter your password";

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
                {"Valid format user with wrong password", "qa.automation.invalid@example.com", "WrongPass@123", INVALID_CREDENTIALS_MSG},
                {"Non-existent username", "no.such.user.20261007@example.com", "Password@123", INVALID_CREDENTIALS_MSG},
                {"Malformed username", "invalid-user", "Password@123", EMPTY_PASSWORD_MSG},
                {"Empty username and password", "", "", EMPTY_USERNAME_MSG},
                {"Empty username only", "", "Password@123", EMPTY_USERNAME_MSG},
                {"Empty password only", "qa.automation.invalid@example.com", "", EMPTY_PASSWORD_MSG}
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Verify login is rejected for invalid credentials")
    public void verifyInvalidLogin(String scenario, String user, String pass, String expectedError) {
        try {
            loginPage.doLogin(user, pass);
            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(actualError.contains(expectedError),
                    "[" + scenario + "] Expected error containing '" + expectedError + "' but was '" + actualError + "'");
            Assert.assertTrue(loginPage.isOnLoginPage(), "[" + scenario + "] User navigated away from login page");
        } catch (IllegalStateException e) {
            Assert.fail("[" + scenario + "] Invalid login verification failed: " + e.getMessage(), e);
        }
    }

    @Test(description = "Verify Remember Me selection does not bypass invalid credential validation")
    public void verifyInvalidLoginWithRememberMe() {
        try {
            loginPage.doLogin("qa.automation.invalid@example.com", "WrongPass@123", true);
            Assert.assertTrue(loginPage.getErrorMessage().contains(INVALID_CREDENTIALS_MSG),
                    "Expected invalid credentials error with Remember Me selected");
            Assert.assertTrue(loginPage.isOnLoginPage(), "User navigated away from login page");
        } catch (IllegalStateException e) {
            Assert.fail("Invalid login with Remember Me failed: " + e.getMessage(), e);
        }
    }
}
