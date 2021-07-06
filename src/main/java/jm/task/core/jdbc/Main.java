package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Rik", "Sand", (byte) 20);
        userService.saveUser("Viktor", "Loginov", (byte) 23);
        userService.saveUser("Piter", "Egrik", (byte) 24);
        userService.saveUser("Irina", "Robertson", (byte) 25);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
