package ru.praktikum.api;

public class UserGeneratorApi
{
    public static UserApi createCurrentRandomUser(String email, String password, String name)
    {
        return UserApi.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
