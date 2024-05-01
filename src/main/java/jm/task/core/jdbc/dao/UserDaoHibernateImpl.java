package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.query.NativeQuery;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS RIXIOTABLE (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(20), " +
                "last_name VARCHAR(20), " +
                "age TINYINT)";
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("Table created successfully");
        }
        catch (HibernateException e) {
            System.out.println("Error at creating table");
        }
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS RIXIOTABLE";
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("Table dropped successfully");
        }
        catch (HibernateException e) {
            System.out.println("Error at dropping table");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User saved successfully");
        }
        catch (HibernateException e) {
            System.out.println("Error at saving user");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("User removed successfully");
        }
        catch (HibernateException e) {
            System.out.println("Error at removing user");
        }
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT id, name, last_name, age FROM RIXIOTABLE";
        List<User> users = null;
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
            users = nativeQuery.list();
            transaction.commit();
            System.out.println("Users retrieved successfully");
        } catch (HibernateException e) {
            System.out.println("Error at retrieving users");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String query = "DELETE FROM RIXIOTABLE";
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(query).executeUpdate();
            transaction.commit();
            System.out.println("Table cleaned successfully");
        }
        catch (HibernateException e) {
            System.out.println("Error at cleaning table");
        }
    }
}
