package org.example.sistemalogin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {

    private String name, surname, dni, mail, password;

    public User(String name, String surname, String dni, String mail, String password) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.mail = mail;
        this.password = password;
    }
}
