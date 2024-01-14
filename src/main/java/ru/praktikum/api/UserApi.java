package ru.praktikum.api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class UserApi
{
    private String email;
    private String password;
    private String name;
}
