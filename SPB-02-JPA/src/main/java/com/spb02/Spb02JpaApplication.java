package com.spb02;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.spb02.dao.UserRepository;
import com.spb02.entities.User;

@SpringBootApplication
public class Spb02JpaApplication {

	public static void main(String[] args) {
		
		ApplicationContext context=SpringApplication.run(Spb02JpaApplication.class, args);
		
		UserRepository userRepository = context.getBean(UserRepository.class);
		
		/*
		//Create -start
		//Create object of User
		User user1=new User();
		user1.setId(05);
		user1.setName("Shailoo");
		user1.setCity("Pune");
		
		User user2=new User();
		user2.setId(06);
		user2.setName("Gulab");
		user2.setCity("Delhi");
		
		User user3=new User();
		user3.setId(07);
		user3.setName("Ram");
		user3.setCity("Pune");
		
		//Saving Single user
		User saveSingleUser = userRepository.save(user1);
		System.out.println("Single user saved -> "+saveSingleUser);
		
		//Saving All users
		List<User> listOfUsers = List.of(user2,user3); Iterable<User> saveAllUsers =
		userRepository.saveAll(listOfUsers); System.out.println("All users saved");
		saveAllUsers.forEach(user->System.out.println(user));
		//Create -end
		*/
		
		/*
		//Update -start
		Optional<User> findById = userRepository.findById(4);
		System.out.println("Before update user details--> "+findById);
		User user = findById.get();
		user.setCity("Mumbai");
		User savedUser = userRepository.save(user);
		System.out.println("After update user details--> "+savedUser);
		//Update -end
		*/
		
		/*
		//Select -start
		//findById(id)
		Optional<User> findById = userRepository.findById(06);
		User user = findById.get();
		System.out.println("User using Id--> "+user);
		System.out.println();
		
		//findAll()
		Iterable<User> findAll = userRepository.findAll();
		Iterator<User> iterator = findAll.iterator();
		System.out.println("All users using Iterator--> ");
		while(iterator.hasNext())
		{
			User userItr = iterator.next();
			System.out.println(userItr);
		}
		System.out.println();
		System.out.println("All users using Stream API--> ");
		findAll.forEach(u->System.out.println(u));
		//Select -end
		 * 
		 */
		
		
		/*
		//Delete -start
		userRepository.deleteById(07);
		System.out.println("Deleted-------");				
		//Delete -end
		*/


		 
	}

}
