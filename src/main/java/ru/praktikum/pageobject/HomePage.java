package ru.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.praktikum.constants.ConstantsForHomePage.*;
import static ru.praktikum.constants.URL.HOME_PAGE;
import static ru.praktikum.constants.URL.ORDER_PAGE;

public class HomePage
{
    private final WebDriver driver;
    private By buttonOrderPage = By.xpath(".//li[2]/a/p[text() = 'Лента Заказов']");
    private By checkInOrderPage = By.className("OrderFeed_orderFeed__2RO_j");
    private By buttonBun = By.xpath(".//span[text() = 'Булки']");
    private By buttonSauces = By.xpath(".//span[text() = 'Соусы']");
    private By buttonFillings = By.xpath(".//span[text() = 'Начинки']");
    private By activeButtonBun = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc " +
            "pt-4 pr-10 pb-4 pl-10 noselect')]/span[text() = 'Булки']");
    private By activeButtonSauces = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc " +
            "pt-4 pr-10 pb-4 pl-10 noselect')]/span[text() = 'Соусы']");
    private By activeButtonFillings = By.xpath(".//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc " +
            "pt-4 pr-10 pb-4 pl-10 noselect')]/span[text() = 'Начинки']");
    private By buttonPersonalAccount = By.xpath(".//a/p[text() = 'Личный Кабинет']");
    private By buttonEnterAccount = By.className("button_button__33qZ0");
    private By checkInHomePage = By.xpath(".//section[1]/h1[text() = 'Соберите бургер']");

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Открыть главную страницу сайта")
    public HomePage openHomePage()
    {
        driver.get(HOME_PAGE);
        return this;
    }

    public HomePage openOrderPage()
    {
        driver.get(HOME_PAGE+ORDER_PAGE);
        return this;
    }
    @Step("Проверка что открылась страница Конструктора")
    public HomePage checkHomePage()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(checkInHomePage));
        String receivedTextFromHomePage = driver.findElement(checkInHomePage).getText();
        assertThat("\nОшибка!\nСтраница не открылась!", receivedTextFromHomePage, containsString(TEXT_IN_HOME_PAGE));
        return this;
    }

    @Step("Клик на кнопку Лента Заказов")
    public HomePage enterToOrderPageFromHeader()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonOrderPage))
                .click();
        return this;
    }

    @Step("Проверка что открылась страница с заказами")
    public HomePage checkOpenOrderPageButton()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(checkInOrderPage));
        String receivedTextFromOrderPage = driver.findElement(checkInOrderPage).getText();
        assertThat("\nОшибка!\nСтраница не открылась!", receivedTextFromOrderPage, containsString(TEXT_IN_ORDER_PAGE));
        return this;
    }

    @Step("Клик по кнопке Булки")
    public HomePage clickButtonBun()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonBun))
                .click();
        return this;
    }

    @Step("Проверка прокрутки Булки")
    public HomePage checkButtonBun()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeButtonBun));
        return this;
    }

    @Step("Клик по кнопке Соусы")
    public HomePage clickButtonSauces()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonSauces))
                .click();
        return this;
    }

    @Step("Проверка прокрутки Соусы")
    public HomePage checkButtonSauces()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeButtonSauces));
        return this;
    }

    @Step("Клик по кнопке Начинки")
    public HomePage clickButtonFillings()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonFillings))
                .click();
        return this;
    }

    @Step("Проверка прокрутки Начинки")
    public HomePage checkButtonFillings()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeButtonFillings));
        return this;
    }

    @Step("Клик по кнопке Личный Кабинет")
    public HomePage clickButtonPersonalAccount()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonPersonalAccount))
                .click();
        return this;
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public HomePage clickButtonEnterAccount()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonEnterAccount))
                .click();
        return this;
    }

}
