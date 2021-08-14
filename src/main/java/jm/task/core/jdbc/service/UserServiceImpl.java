package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.*;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserDao userDao = new UserDaoJDBCImpl();

    public void createTable() {
        userDao.createTable();
    }

    public void dropTable() {
        userDao.dropTable();
    }

    public void save(String name, String lastName, byte age) {
        userDao.save(name, lastName, age);
    }

    public void delete(long id) {
        userDao.delete(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void cleanTable() {
        userDao.cleanTable();
    }
}
