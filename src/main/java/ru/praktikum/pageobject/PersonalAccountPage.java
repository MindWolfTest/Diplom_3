package ru.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.praktikum.constants.ProfileConst.TEXT_PROFILE_PAGE;
import static ru.praktikum.constants.TimeForWait.WAITING_TIME;

public class PersonalAccountPage
{
    private final WebDriver driver;
    private By buttonConstructor = By.className("AppHeader_header__link__3D_hX");
    private By buttonExit = By.className("Account_button__14Yp3");
    private By checkProfile = By.xpath(".//li[1]/a[text() = 'Профиль']");
    private By buttonLogo = By.xpath(".//nav/div/a");

    public PersonalAccountPage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Клик конструктор")
    public PersonalAccountPage clickConstructor()
    {
        new WebDriverWait(driver, Duration.ofSeconds(WAITING_TIME))
                .until(ExpectedConditions.elementToBeClickable(buttonConstructor))
                .click();
        return this;
    }

    @Step("Клик Лого")
    public PersonalAccountPage clickLogo()
    {
        new WebDriverWait(driver, Duration.ofSeconds(WAITING_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonLogo))
                .click();
        return this;
    }

    @Step("Клик Выход")
    public PersonalAccountPage clickExitButton()
    {
        new WebDriverWait(driver, Duration.ofSeconds(WAITING_TIME))
                .until(ExpectedConditions.elementToBeClickable(buttonExit))
                .click();
        return this;
    }

    @Step("Проверка что зашли в профиль пользователя")
    public PersonalAccountPage checkPersonalAccountPage()
    {
        new WebDriverWait(driver, Duration.ofSeconds(WAITING_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(checkProfile));
        String receivedTextFromPersonalAccountPage = driver.findElement(checkProfile).getText();
        assertThat("\nОшибка!\nСтраница не открылась!", receivedTextFromPersonalAccountPage, containsString(TEXT_PROFILE_PAGE));
        return this;
    }
}
