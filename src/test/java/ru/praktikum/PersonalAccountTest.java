package ru.praktikum;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.praktikum.api.CreateUserApi;
import ru.praktikum.api.DeleteUserApi;
import ru.praktikum.api.UserModel;
import ru.praktikum.pageobject.HomePage;
import ru.praktikum.pageobject.LoginPage;
import ru.praktikum.pageobject.PersonalAccountPage;

import static ru.praktikum.api.UserGeneratorApi.createCurrentRandomUser;
import static ru.praktikum.constants.URL.HOME_PAGE;
import static ru.praktikum.driver.WebDriverCreator.createWebDriver;

public class PersonalAccountTest
{
    private WebDriver driver;
    Faker faker = new Faker();
    private String email = faker.internet().safeEmailAddress();
    private String password = faker.internet().password(6, 8);
    private String name = faker.name().firstName();
    protected String accessToken;

    @Before
    public void setUp()
    {
        driver = createWebDriver();
        RestAssured.baseURI = HOME_PAGE;
        UserModel user = createCurrentRandomUser(email, password, name);
        CreateUserApi createUser = new CreateUserApi();
        createUser.createUserApi(user);
    }

    @Test
    @DisplayName("Вход пользователем")
    @Description("Вход пользователем")
    public void loginUserTest()
    {
        LoginPage objEnterToAccount = new LoginPage(driver);
        objEnterToAccount
                .openLoginPage()
                .checkOpenLoginPage()
                .putDataToFieldEmail(email)
                .putDataToFieldPassword(password)
                .clickLoginButton();

        accessToken = objEnterToAccount.takeAccessTokenFromLocalStorage();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Переход из личного кабинета в конструктор через кнопку Конструктор")
    public void checkMoveFromPersonalAccountToHomePageTest()
    {
        LoginPage objEnterToAccount = new LoginPage(driver);
        objEnterToAccount
                .openLoginPage()
                .checkOpenLoginPage()
                .putDataToFieldEmail(email)
                .putDataToFieldPassword(password)
                .clickLoginButton();

        accessToken = objEnterToAccount.takeAccessTokenFromLocalStorage();

        HomePage objMoveToPersonalAccount = new HomePage(driver);
        objMoveToPersonalAccount
                .clickButtonPersonalAccount();

        PersonalAccountPage objMoveToConstructor = new PersonalAccountPage(driver);
        objMoveToConstructor
                .checkPersonalAccountPage()
                .clickConstructor();
        objMoveToPersonalAccount.checkHomePage();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Переход из личного кабинета в конструктор через Логотип")
    public void checkMoveFromPersonalAccountToHomePageFromLogoTest()
    {
        LoginPage objEnterToAccount = new LoginPage(driver);
        objEnterToAccount
                .openLoginPage()
                .checkOpenLoginPage()
                .putDataToFieldEmail(email)
                .putDataToFieldPassword(password)
                .clickLoginButton();

        accessToken = objEnterToAccount.takeAccessTokenFromLocalStorage();

        HomePage objMoveToPersonalAccount = new HomePage(driver);
        objMoveToPersonalAccount
                .clickButtonPersonalAccount();

        PersonalAccountPage objMoveToConstructor = new PersonalAccountPage(driver);
        objMoveToConstructor
                .checkPersonalAccountPage()
                .clickLogo();
        objMoveToPersonalAccount.checkHomePage();
    }

    @Test
    @DisplayName("Выход пользователем")
    @Description("Выход пользователем")
    public void checkExitUserTest()
    {
        LoginPage objEnterToAccount = new LoginPage(driver);
        objEnterToAccount
                .openLoginPage()
                .checkOpenLoginPage()
                .putDataToFieldEmail(email)
                .putDataToFieldPassword(password)
                .clickLoginButton();

        accessToken = objEnterToAccount.takeAccessTokenFromLocalStorage();

        HomePage objMoveToPersonalAccount = new HomePage(driver);
        objMoveToPersonalAccount
                .clickButtonPersonalAccount();

        PersonalAccountPage objMoveToConstructor = new PersonalAccountPage(driver);
        objMoveToConstructor
                .checkPersonalAccountPage()
                .clickExitButton();

        LoginPage objLogin = new LoginPage(driver);
        objLogin.checkOpenLoginPage();
    }

    @After
    public void tearDown()
    {
        driver.close();
        DeleteUserApi deleteUserApi = new DeleteUserApi();
        RestAssured.baseURI = HOME_PAGE;
        deleteUserApi.deleteUser(accessToken);
    }
}
