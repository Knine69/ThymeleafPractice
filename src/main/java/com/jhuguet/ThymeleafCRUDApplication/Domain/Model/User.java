package com.jhuguet.ThymeleafCRUDApplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    long id;
    String firstName;
    String lastName;
    String email;
}
