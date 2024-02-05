package ru.praktikum;

import jdk.jfr.Description;
import org.junit.Test;
import ru.praktikum.pageobject.HomePage;
import io.qameta.allure.junit4.DisplayName;
import ru.praktikum.pageobject.LoginPage;

public class CheckButtonsAtHomePageTest extends BeforeAndAfter
{
    @Test
    @DisplayName("Переход на страницу с заказами с главной страницы")
    @Description("Переход на страницу с заказами с главной страницы")
    public void checkButtonOrderFromHomePage ()
    {
        HomePage objCheckButtonInHeader = new HomePage(driver);

        objCheckButtonInHeader
                .openHomePage()
                .enterToOrderPageFromHeader()
                .checkOpenOrderPageButton();
    }

    @Test
    @DisplayName("Клик по кнопке Булки")
    @Description("Клик по кнопке Булки")
    public void checkButtonBun()
    {
        HomePage objCheckButtonBun = new HomePage(driver);
        objCheckButtonBun
                .openHomePage()
                .clickButtonFillings()
                .clickButtonBun()
                .checkButtonBun();
    }

    @Test
    @DisplayName("Клик по кнопке Соусы")
    @Description("Клик по кнопке Соусы")
    public void checkButtonSauces()
    {
        HomePage objCheckButtonSauces = new HomePage(driver);
        objCheckButtonSauces
                .openHomePage()
                .clickButtonSauces()
                .checkButtonSauces();
    }

    @Test
    @DisplayName("Клик по кнопке Начинки")
    @Description("Клик по кнопке Начинки")
    public void checkButtonFillings()
    {
        HomePage objCheckButtonFillings = new HomePage(driver);
        objCheckButtonFillings
                .openHomePage()
                .clickButtonFillings()
                .checkButtonFillings();
    }

    @Test
    @DisplayName("Клик по кнопке Личный Кабинет")
    @Description("Клик по кнопке Личный Кабинет")
    public void checkButtonPersonalAccount()
    {
        HomePage objCheckButtonPersonalAccount = new HomePage(driver);
        objCheckButtonPersonalAccount
                .openHomePage()
                .clickButtonPersonalAccount();

        LoginPage loginPage =new LoginPage(driver);
        loginPage.checkOpenLoginPage();
    }

    @Test
    @DisplayName("Клик по кнопке Войти в аккаунт")
    @Description("Клик по кнопке Войти в аккаунт")
    public void checkButtonEnterAccount()
    {
        HomePage objCheckButtonPersonalAccount = new HomePage(driver);
        objCheckButtonPersonalAccount
                .openHomePage()
                .clickButtonEnterAccount();

        LoginPage loginPage =new LoginPage(driver);
        loginPage.checkOpenLoginPage();
    }

}
