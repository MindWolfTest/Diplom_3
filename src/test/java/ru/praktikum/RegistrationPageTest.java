package ru.praktikum;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.junit.Test;

import ru.praktikum.api.DeleteUserApi;
import ru.praktikum.api.UserModel;
import ru.praktikum.pageobject.HomePage;
import ru.praktikum.pageobject.LoginPage;
import ru.praktikum.pageobject.PersonalAccountPage;
import ru.praktikum.pageobject.RegistrationPage;

import static ru.praktikum.api.UserGeneratorApi.createCurrentRandomUser;
import static ru.praktikum.constants.URL.HOME_PAGE;

public class RegistrationPageTest extends BeforeAndAfter
{
    Faker faker = new Faker();

    @Test
    @DisplayName("Регистрация пользователя с не корректным паролем")
    @Description("Регистрация пользователя с не корректным паролем")
    public void putIncorrectDataForRegistration()
    {
        RegistrationPage objPutIncorrectDataForRegistration = new RegistrationPage(driver);

        objPutIncorrectDataForRegistration
                .openRegistrationPage()
                .putDataToFieldName(faker.name().firstName())
                .putDataToFieldEmail(faker.internet().safeEmailAddress())
                .putDataToFieldPassword(faker.internet().password(4, 5))
                .clickRegistrationButton()
                .checkForIncorrectPassword();
    }

    @Test
    @DisplayName("Регистрация пользователя с корректными данными")
    @Description("Регистрация пользователя с корректными данными")
    public void putCorrectDataForRegistration()
    {
        UserModel userModel = createCurrentRandomUser();
        RegistrationPage objPutCorrectDataForRegistration = new RegistrationPage(driver);

        objPutCorrectDataForRegistration
                .openRegistrationPage()
                .putDataToFieldName(userModel.getName())
                .putDataToFieldEmail(userModel.getEmail())
                .putDataToFieldPassword(userModel.getPassword())
                .clickRegistrationButton();

        LoginPage objCheckRegistration = new LoginPage(driver);
        objCheckRegistration.checkOpenLoginPage();

        LoginPage objEnterToAccount = new LoginPage(driver);
        objEnterToAccount.putDataToFieldEmail(userModel.getEmail())
                .putDataToFieldPassword(userModel.getPassword())
                .clickLoginButton();

        String accessToken = objEnterToAccount.takeAccessTokenFromLocalStorage();

        HomePage objClickToPersonalAccount = new HomePage(driver);
        objClickToPersonalAccount
                .checkHomePage()
                .clickButtonPersonalAccount();

        PersonalAccountPage objCheckPersonalAccountPage = new PersonalAccountPage(driver);
        objCheckPersonalAccountPage.checkPersonalAccountPage();

        DeleteUserApi deleteUserApi = new DeleteUserApi();
        RestAssured.baseURI = HOME_PAGE;
        deleteUserApi.deleteUser(accessToken);
    }
}
