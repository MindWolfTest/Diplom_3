package ru.praktikum.pageobject;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.praktikum.constants.ConstantsForHomePage.TEXT_IN_PERSONAL_ACCOUNT_PAGE;
import static ru.praktikum.constants.URL.HOME_PAGE;
import static ru.praktikum.constants.URL.LOGIN_PAGE;

public class LoginPage
{
    private final WebDriver driver;
    private By inputEmailField = By.xpath(".//fieldset[1]/div/div/input");
    private By inputPasswordField = By.xpath(".//fieldset[2]/div/div/input");
    private By enterButton = By.className("button_button__33qZ0");
    private By takeBurger = By.xpath("/html/body/div/div/main/section[1]/h1");
    private By checkLoginPage = By.xpath(".//div/h2[text() = 'Вход']");



    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }
    @Step("Открыть страницу Вход")
    public LoginPage openLoginPage()
    {
        driver.get(HOME_PAGE+LOGIN_PAGE);
        return this;
    }

    @Step("Проверка что открылась страница входа в личный кабинет")
    public LoginPage checkOpenLoginPage()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(checkLoginPage));
        String receivedTextFromPersonalAccountPage = driver.findElement(checkLoginPage).getText();
        assertThat("\nОшибка!\nСтраница не открылась!", receivedTextFromPersonalAccountPage, containsString(TEXT_IN_PERSONAL_ACCOUNT_PAGE));
        return this;
    }

    @Step("Ввод данных в поле Email {email}")
    public LoginPage putDataToFieldEmail(String email)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(inputEmailField));
        driver.findElement(inputEmailField).sendKeys(email);
        return this;
    }

    @Step("Ввод данных в поле Пароль {password}")
    public LoginPage putDataToFieldPassword(String password)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(inputEmailField));
        driver.findElement(inputPasswordField).sendKeys(password);
        return this;
    }

    @Step("Клик кнопка войти")
    public LoginPage clickLoginButton()
    {new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.elementToBeClickable(enterButton));
        driver.findElement(enterButton).click();
        return this;
    }

    @Step("Получение accessToken")
    public String takeAccessTokenFromLocalStorage()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(takeBurger));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return (String) js.executeScript("return window.localStorage.getItem('accessToken');");
    }
}
