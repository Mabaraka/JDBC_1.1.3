package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();

    public void createTable() {

        String query = "CREATE TABLE IF NOT EXISTS jdbc_users ("
                + "id BIGINT NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(45) NOT NULL,"
                + "lastName VARCHAR(45) NOT NULL,"
                + "age TINYINT NOT NULL, PRIMARY KEY (id))";

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void dropTable() {

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS jdbc_users;");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void save(String name, String lastName, byte age) {

        String query = "INSERT INTO jdbc_users (name, lastName, age) VALUES(?,?,?)";

        try (PreparedStatement preStmt = connection.prepareStatement(query)) {
            preStmt.setString(1, name);
            preStmt.setString(2, lastName);
            preStmt.setInt(3, age);
            preStmt.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void delete(long id) {

        String query = "delete from jdbc_users where id = (?)";

        try (PreparedStatement preStmt = connection.prepareStatement(query)) {
            preStmt.setLong(1, id);
            preStmt.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public List<User> getAll() {

        List<User> resultList = new ArrayList<>();
        String query = "select * from jdbc_users";

        try (Statement stmt = connection.createStatement()) {
            ResultSet usersDatabase = stmt.executeQuery(query);

            while (usersDatabase.next()) {
                User user = new User(
                        usersDatabase.getString("name"),
                        usersDatabase.getString("lastName"),
                        usersDatabase.getByte("age"));
                user.setId(usersDatabase.getLong("id"));
                resultList.add(user);
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }
        return resultList;

    }

    public void cleanTable() {

        String query = "TRUNCATE TABLE jdbc_users";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            e.printStackTrace();
        }

    }

}
