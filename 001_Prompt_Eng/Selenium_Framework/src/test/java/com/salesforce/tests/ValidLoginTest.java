package com.salesforce.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.salesforce.base.BaseTest;

public class ValidLoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify all login page UI elements are displayed")
    public void verifyLoginPageUiElements() {
        try {
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page core elements are not displayed");
            Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed(), "Forgot Password link is not displayed");
            Assert.assertEquals(loginPage.getLoginButtonValue(), "Log In", "Login button label mismatch");
        } catch (IllegalStateException e) {
            Assert.fail("UI verification failed: " + e.getMessage(), e);
        }
    }

    @Test(priority = 2, description = "Verify login page title")
    public void verifyLoginPageTitle() {
        try {
            Assert.assertTrue(loginPage.getPageTitle().contains("Salesforce"),
                    "Unexpected page title: " + loginPage.getPageTitle());
        } catch (IllegalStateException e) {
            Assert.fail("Title verification failed: " + e.getMessage(), e);
        }
    }

    @Test(priority = 3, description = "Verify password field masks input")
    public void verifyPasswordFieldIsMasked() {
        try {
            Assert.assertTrue(loginPage.submitUsername("qa.automation.masking@example.com"),
                    "Password step was not displayed after submitting username");
            loginPage.enterPassword("MaskedValue@123");
            Assert.assertTrue(loginPage.isPasswordMasked(), "Password field is not masked");
        } catch (IllegalStateException e) {
            Assert.fail("Password masking verification failed: " + e.getMessage(), e);
        }
    }

    @Test(priority = 4, description = "Verify Remember Me checkbox can be selected and deselected")
    public void verifyRememberMeToggle() {
        try {
            loginPage.setRememberMe(true);
            Assert.assertTrue(loginPage.isRememberMeSelected(), "Remember Me was not selected");
            loginPage.setRememberMe(false);
            Assert.assertFalse(loginPage.isRememberMeSelected(), "Remember Me was not deselected");
        } catch (IllegalStateException e) {
            Assert.fail("Remember Me verification failed: " + e.getMessage(), e);
        }
    }

    @Test(priority = 5, description = "Verify successful login with valid credentials and Remember Me")
    public void verifyValidLogin() {
        String user = credential("sf.username", "SF_USERNAME");
        String pass = credential("sf.password", "SF_PASSWORD");
        if (user.isEmpty() || pass.isEmpty()) {
            throw new SkipException("Valid credentials not supplied via -Dsf.username/-Dsf.password or SF_USERNAME/SF_PASSWORD");
        }
        try {
            loginPage.doLogin(user, pass, true);
            Assert.assertTrue(loginPage.waitForNavigationAwayFromLogin(),
                    "User remained on the login page after submitting valid credentials");
            Assert.assertFalse(loginPage.isOnLoginPage(), "Login page still displayed after valid login");
        } catch (IllegalStateException e) {
            Assert.fail("Valid login flow failed: " + e.getMessage(), e);
        }
    }
}
