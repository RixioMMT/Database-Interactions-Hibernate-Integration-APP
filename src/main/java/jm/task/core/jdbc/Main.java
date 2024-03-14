package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User[] usersArray = new User[4];
        usersArray[0] = new User("Rixio", "Morales", (byte) 29);
        usersArray[1] = new User("Tianet", "Torres", (byte) 62);
        usersArray[2] = new User("Maitte", "Prieto", (byte) 54);
        usersArray[3] = new User("Ivana", "Fernandez", (byte) 28);
        for(User user:usersArray) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User with name " + user.getName() + " added to the database");
        }
        List<User> usersList = userService.getAllUsers();
        for(User user : usersList) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
