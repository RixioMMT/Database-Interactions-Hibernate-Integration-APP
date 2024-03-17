package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/rixio";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, "root", "mysqlapesta123");
            System.out.println("Connection success");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection error");
        }
        return connection;
    }

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Map<String, String> settings = new HashMap<>();
            settings.put("hibernate.connection.driver_class", driver);
            settings.put("hibernate.connection.url", url);
            settings.put("hibernate.connection.username", "root");
            settings.put("hibernate.connection.password", "mysqlapesta123");
            settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
            settings.put("hibernate.current_session_context_class", "thread");
            ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(settings).build();
            Metadata metadata = new MetadataSources(registry).addAnnotatedClass(User.class).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
            System.out.println("Connection success");
        }
        catch (HibernateException e) {
            System.out.println("Connection error");
        }
        return sessionFactory;
    }
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}

