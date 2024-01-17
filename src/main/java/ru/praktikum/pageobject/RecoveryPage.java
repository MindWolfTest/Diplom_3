package ru.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.praktikum.constants.TimeForWait.WAITING_TIME;
import static ru.praktikum.constants.URL.*;

public class RecoveryPage
{
    private final WebDriver driver;
    private By enterButton = By.className("Auth_link__1fOlj");

    public RecoveryPage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Открыть страницу восстановления пароля")
    public RecoveryPage openRecoveryPage()
    {
        driver.get(HOME_PAGE+RECOVERY_PASSWORD_PAGE);
        return this;
    }

    @Step("Клик по кнопке войти")
    public RecoveryPage clickEnterButton()
    {
        new WebDriverWait(driver, Duration.ofSeconds(WAITING_TIME))
                .until(ExpectedConditions.elementToBeClickable(enterButton))
                .click();
        return this;
    }
}
