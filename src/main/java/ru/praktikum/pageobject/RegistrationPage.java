package ru.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.praktikum.constants.RegistrationPageConst.TEXT_INCORRECT_PASSWORD;
import static ru.praktikum.constants.URL.HOME_PAGE;
import static ru.praktikum.constants.URL.REGISTRATION_PAGE;

public class RegistrationPage
{
    private final WebDriver driver;

    private By fieldName = By.xpath(".//label[text() = 'Имя']");

    private By inputName = By.xpath(".//fieldset[1]/div/div/input");
    private By fieldEmail = By.xpath(".//label[text() = 'Email']");
    private By inputEmail = By.xpath(".//fieldset[2]/div/div/input");
    private By fieldPassword = By.xpath(".//label[text() = 'Пароль']");
    private By inputPassword = By.xpath(".//fieldset[3]/div/div/input");
    private By buttonRegistration = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private By textIncorrectPassword = By.xpath(".//fieldset[3]/div/p[text() = 'Некорректный пароль']");
    private By enterButton = By.className("Auth_link__1fOlj");


    public RegistrationPage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Открыть страницу регистрации")
    public RegistrationPage openRegistrationPage()
    {
        driver.get(HOME_PAGE + REGISTRATION_PAGE);
        return this;
    }

    @Step("Клик на поле Имя")
    public RegistrationPage clickFieldName()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(fieldName))
                .click();
        return this;
    }
    @Step("Вввод имени {name}")
    public RegistrationPage putDataToFieldName(String name)
    {
        driver.findElement(inputName).sendKeys(name);
        return this;
    }

    @Step("Клик на поле Email")
    public RegistrationPage clickFieldEmail()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(fieldEmail))
                .click();
        return this;
    }
    @Step("Вввод Email {email}")
    public RegistrationPage putDataToFieldEmail(String email)
    {
        driver.findElement(inputEmail).sendKeys(email);
        return this;
    }

    @Step("Клик на поле Пароль")
    public RegistrationPage clickFieldPassword()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(fieldPassword))
                .click();
        return this;
    }
    @Step("Вввод Пароль {password}")
    public RegistrationPage putDataToFieldPassword(String password)
    {
        driver
                .findElement(inputPassword)
                .sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку Зарегистрироваться")
    public RegistrationPage clickRegistrationButton()
    {
        driver
                .findElement(buttonRegistration)
                .click();
        return this;
    }

    @Step("Проверка на некорректный пароль")
    public RegistrationPage checkForIncorrectPassword()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textIncorrectPassword));
        String receivedTextIncorrectPassword = driver.findElement(textIncorrectPassword).getText();
        assertThat("\nОшибка!\nСообщение о некорректном пароле не появилось!", receivedTextIncorrectPassword,
                containsString(TEXT_INCORRECT_PASSWORD));
        return this;
    }

    @Step("Клик по кнопке войти")
    public RegistrationPage clickEnterButton()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(enterButton))
                .click();

        return this;
    }

}
