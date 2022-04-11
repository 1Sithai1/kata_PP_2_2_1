package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        User user1 = new User("Vova", "Loker", "vov@mail.ru");
        User user2 = new User("Dima", "Savin", "diman@mail.ru");
        User user3 = new User("Kolya", "Solomon", "solo@mail.ru");
        User user4 = new User("Vanya", "Petrov", "petvan@mail.ru");
        Car car1 = new Car("Logan", 1);
        Car car2 = new Car("KIA", 2);
        Car car3 = new Car("Ford", 3);
        Car car4 = new Car("KIA", 4);

        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);
        car4.setUser(user4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        userService.addCar(car1);
        userService.addCar(car2);
        userService.addCar(car3);
        userService.addCar(car4);

//        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCars() != null) {
                System.out.println("Car = " + user.getCars().getModel());
                System.out.println("Series = " + user.getCars().getSeries());
            }
            System.out.println();
        }

        List<Car> owner = userService.getCarOwner("KIA", 4);
        for (int i = 0; i < owner.size();i++) {
            System.out.println(owner.get(i));
        }

        context.close();
    }
}
