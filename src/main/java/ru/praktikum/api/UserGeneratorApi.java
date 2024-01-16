package ru.praktikum.api;

import com.github.javafaker.Faker;

public class UserGeneratorApi
{
    public static UserModel createCurrentRandomUser()
    {
        Faker faker = new Faker();

        return UserModel.builder()
                .email(faker.internet().safeEmailAddress())
                .password(faker.internet().password(6, 8))
                .name(faker.name().firstName())
                .build();
    }
}
