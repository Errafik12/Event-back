package com.fr.event.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@Data
@ToString
public class User {

    public String firstName;
    public String name;
    public Date dateOfBirth;
    public String role;
    public String password;
}
