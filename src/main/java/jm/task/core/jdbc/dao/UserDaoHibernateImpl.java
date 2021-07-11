package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String table = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(45) NOT NULL,"
                + "lastName VARCHAR(45) NOT NULL,"
                + "age INT(3) NOT NULL, PRIMARY KEY (id))";
        try (SessionFactory sf = Util.getSessionFactory();
             Session session = sf.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(table).executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("createUsersTable error");
        }

    }

    @Override
    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users;";
        try (SessionFactory sf = Util.getSessionFactory();
             Session session = sf.openSession())  {
            session.beginTransaction();
            session.createSQLQuery(drop).executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("dropUsersTable error");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sf = Util.getSessionFactory();
             Session session = sf.openSession())  {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("saveUser error");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sf = Util.getSessionFactory();
             Session session = sf.openSession())  {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("removeUserById error");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (SessionFactory sf = Util.getSessionFactory();
             Session session = sf.openSession())  {
            session.beginTransaction();
            result = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("getAllUsers error");
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        String delete = "TRUNCATE TABLE users";
        try (SessionFactory sf = Util.getSessionFactory();
             Session session = sf.openSession())  {
            session.beginTransaction();
            session.createSQLQuery(delete).executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.out.println("cleanUsersTable error");
        }
    }
}
