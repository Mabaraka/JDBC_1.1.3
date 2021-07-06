package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String myTableName = "CREATE TABLE IF NOT EXISTS anyTable ("
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(45) NOT NULL,"
                + "lastName VARCHAR(45) NOT NULL,"
                + "age INT(3) NOT NULL, PRIMARY KEY (id))";
        try (Statement stmt = Util.settingConnection().createStatement()) {
            stmt.executeUpdate(myTableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement stmt = Util.settingConnection().createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS anyTable;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String parameter = "INSERT INTO anyTable (name, lastName, age) VALUES(?,?,?)";
        try (PreparedStatement preStmt = Util.settingConnection().prepareStatement(parameter)) {
            preStmt.setString(1, name);
            preStmt.setString(2, lastName);
            preStmt.setInt(3, age);
            preStmt.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String delete = "delete from anyTable where id == id VALUES (?)";
        try (PreparedStatement preStmt = Util.settingConnection().prepareStatement(delete)) {
            preStmt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String query = "select * from anyTable";
        try (Statement stmt = Util.settingConnection().createStatement()) {
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                int index = 0;
                User user = new User(set.getString("name"),
                        set.getString("lastName"),
                        set.getByte("age"));
                user.setId(set.getLong("id"));
                result.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        String delete = "delete from anyTable";
        try (Statement stmt = Util.settingConnection().createStatement()) {
            stmt.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
