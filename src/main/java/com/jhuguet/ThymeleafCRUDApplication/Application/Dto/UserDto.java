package com.jhuguet.ThymeleafCRUDApplication.Application.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    long id;
    String firstName;
    String lastName;
    String email;
}
