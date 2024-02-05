package ru.praktikum;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import ru.praktikum.api.CreateUserApi;
import ru.praktikum.api.DeleteUserApi;
import ru.praktikum.api.UserModel;
import ru.praktikum.pageobject.HomePage;
import ru.praktikum.pageobject.LoginPage;
import ru.praktikum.pageobject.PersonalAccountPage;


import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikum.api.UserGeneratorApi.createCurrentRandomUser;
import static ru.praktikum.constants.ApiConst.*;
import static ru.praktikum.constants.URL.HOME_PAGE;
import static ru.praktikum.driver.WebDriverCreator.createWebDriver;

public class PersonalAccountTest
{
    private WebDriver driver;
    UserModel user;
    protected String accessToken;

    @Before
    public void setUp()
    {
        driver = createWebDriver();
        RestAssured.baseURI = HOME_PAGE;
        user = createCurrentRandomUser();

        CreateUserApi createUser = new CreateUserApi();
        Response response = createUser.createUserApi(user);
        response.then().assertThat().statusCode(SC_OK).and().body(SUCCESS, equalTo(TRUE));
        accessToken = response.path(ACCESS_TOKEN);
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
                .putDataToFieldEmail(user.getEmail())
                .putDataToFieldPassword(user.getPassword())
                .clickLoginButton();
        HomePage homePage =new HomePage(driver);
        homePage
                .checkHomePage()
                .clickButtonPersonalAccount();

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.checkPersonalAccountPage();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Переход из личного кабинета в конструктор через кнопку Конструктор")
    public void checkMoveFromPersonalAccountToHomePageTest()
    {
        HomePage objEnterToAccount = new HomePage(driver);
        objEnterToAccount.openHomePage().checkHomePage();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem(ACCESS_TOKEN, accessToken);

        objEnterToAccount.clickButtonPersonalAccount();

        objEnterToAccount.clickButtonPersonalAccount();

        PersonalAccountPage objMoveToConstructor = new PersonalAccountPage(driver);
        objMoveToConstructor.checkPersonalAccountPage().clickConstructor();
        objEnterToAccount.checkHomePage();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Переход из личного кабинета в конструктор через Логотип")
    public void checkMoveFromPersonalAccountToHomePageFromLogoTest()
    {
        HomePage objEnterToAccount = new HomePage(driver);
        objEnterToAccount.openHomePage().checkHomePage();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem(ACCESS_TOKEN, accessToken);

        objEnterToAccount.clickButtonPersonalAccount();

        PersonalAccountPage objMoveToConstructor = new PersonalAccountPage(driver);
        objMoveToConstructor.checkPersonalAccountPage().clickLogo();

        objEnterToAccount.checkHomePage();
    }

    @Test
    @DisplayName("Выход пользователем")
    @Description("Выход пользователем")
    public void checkExitUserTest()
    {
        HomePage objEnterToAccount = new HomePage(driver);
        objEnterToAccount.openHomePage().checkHomePage();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem(ACCESS_TOKEN, accessToken);

        objEnterToAccount.clickButtonPersonalAccount();

        PersonalAccountPage objExit = new PersonalAccountPage(driver);
        objExit.checkPersonalAccountPage().clickExitButton();

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
