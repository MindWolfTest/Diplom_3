package ru.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.praktikum.constants.URL.REGISTER_USER_API;

public class CreateUserApi
{
    @Step("Создание клиента {user}")
    public Response createUserApi(UserModel user)
    {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(REGISTER_USER_API);
    }
}
