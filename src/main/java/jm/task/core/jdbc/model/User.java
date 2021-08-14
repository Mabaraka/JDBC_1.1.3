package jm.task.core.jdbc.model;

import lombok.*;

@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Byte age;

    public User(String firstName, String lastName, Byte age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
