package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikum.pageobject.LoginPage;
import ru.praktikum.pageobject.RecoveryPage;
import ru.praktikum.pageobject.RegistrationPage;

public class LoginPageTest extends BeforeAndAfter
{
    @Test
    @DisplayName("Переход на страницу входа со страницы регистрации")
    @Description("Переход на страницу входа со страницы регистрации")
    public void enterLoginPageFromRegistrationPageTest()
    {
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.openRegistrationPage()
                .clickEnterButton();
        LoginPage objCheckOpen = new LoginPage(driver);
        objCheckOpen.checkOpenLoginPage();
    }

    @Test
    @DisplayName("Переход на страницу входа со страницы восстановления пароля")
    @Description("Переход на страницу входа со страницы восстановления пароля")
    public void enterLoginPageFromRecoveryPasswordPageTest()
    {
        RecoveryPage objRecoveryPage = new RecoveryPage(driver);
        objRecoveryPage.openRecoveryPage()
                .clickEnterButton();
        LoginPage objCheckOpen = new LoginPage(driver);
        objCheckOpen.checkOpenLoginPage();
    }
}
