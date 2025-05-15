package com.JDBC.JB2405;

import com.JDBC.JB2405.Entity.User;
import com.JDBC.JB2405.Service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Jb2405Application implements CommandLineRunner {
	@Autowired
	UserDao userDao;
	public static void main(String[] args) {
		SpringApplication.run(Jb2405Application.class, args);
	}




	@Override
	public void run(String... args) throws Exception {
		User u = new User();
		u.setId(new Random().nextInt(100));
		u.setName("John Doe");
		u.setCity("New York");
		u.setEmail("test10@gmail.com");
		userDao.saveUser(u);
		// Example usage
		User user = userDao.getUser(2);
		System.out.println("User: " + user.getName() + ", City: " + user.getCity() + ", Email: " + user.getEmail());
		userDao.updateUser(user);
		userDao.deleteUser(1);
		userDao.searchUser("John");
		List<User> alluser= userDao.getallUser();
		for (User user1 : alluser) {
			System.out.println("User: " + user1.getName() + ", City: " + user1.getCity() + ", Email: " + user1.getEmail());
		}

	}
}
