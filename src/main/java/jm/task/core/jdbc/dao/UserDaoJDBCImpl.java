package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        String table = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(45) NOT NULL,"
                + "lastName VARCHAR(45) NOT NULL,"
                + "age INT(3) NOT NULL, PRIMARY KEY (id))";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(table);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                System.out.println("При попытке роллбэка произошла ошибка");
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS users;");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                System.out.println("При попытке роллбэка произошла ошибка");
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        String parameter = "INSERT INTO users (name, lastName, age) VALUES(?,?,?)";
        try (PreparedStatement preStmt = connection.prepareStatement(parameter)) {
            preStmt.setString(1, name);
            preStmt.setString(2, lastName);
            preStmt.setInt(3, age);
            preStmt.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                System.out.println("При попытке роллбэка произошла ошибка");
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        String delete = "delete from users where id == id VALUES (?)";
        try (PreparedStatement preStmt = connection.prepareStatement(delete)) {
            preStmt.setLong(1, id);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                System.out.println("При попытке роллбэка произошла ошибка");
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        List<User> result = new ArrayList<>();
        String query = "select * from users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                User user = new User(set.getString("name"),
                        set.getString("lastName"),
                        set.getByte("age"));
                user.setId(set.getLong("id"));
                result.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                System.out.println("При попытке роллбэка произошла ошибка");
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        String delete = "TRUNCATE TABLE users";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(delete);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                System.out.println("При попытке роллбэка произошла ошибка");
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
