package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createTable();

        userService.save("Rik", "Sand", (byte) 20);
        userService.save("Viktor", "Login", (byte) 23);
        userService.save("Pit", "Erik", (byte) 24);
        userService.save("Irina", "Robertson", (byte) 25);

        List<User> list = userService.getAll();
        System.out.println(list);

        userService.cleanTable();

        userService.dropTable();
    }
}
