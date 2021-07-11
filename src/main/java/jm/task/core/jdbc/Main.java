package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Rik", "Sand", (byte) 20);
        userService.saveUser("Viktor", "Login", (byte) 23);
        userService.saveUser("Pit", "Erik", (byte) 24);
        userService.saveUser("Irina", "Robertson", (byte) 25);
        List<User> list = userService.getAllUsers();
        System.out.println(list);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
