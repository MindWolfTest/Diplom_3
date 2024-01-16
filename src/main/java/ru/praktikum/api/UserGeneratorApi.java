package ru.praktikum.api;

public class UserGeneratorApi
{
    public static UserModel createCurrentRandomUser(String email, String password, String name)
    {
        return UserModel.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
