package ru.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.praktikum.constants.ApiConst.AUTHORIZATION;
import static ru.praktikum.constants.URL.DELETE_USER_API;

public class DeleteUserApi
{
    @Step("Удаление пользователя с токеном: \n{accessToken}")
    public Response deleteUser(String accessToken)
    {
        return given()
                .header(AUTHORIZATION, accessToken)
                .delete(DELETE_USER_API);
    }
}
