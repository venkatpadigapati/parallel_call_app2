package com.parallel.calls.proj2.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
	
	int i = 1;

	@Autowired
    private UserRepository userRepository;

	public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser createUser(@RequestBody AppUser user) {
    	user.setName(user.getName() + " " + i);
    	user.setEmail(user.getEmail() + " " + i);
    	i= i+1;
        return userRepository.save(user);
    }
    
    public List<AppUser> getUsersByIds(List<Long> ids) throws InterruptedException {
    	System.out.println("Processing batch with IDs: " + ids + " at " + System.currentTimeMillis());
        // Simulate some processing time without blocking the thread
        long processingTime = 4000; // 5 seconds
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < processingTime) {
            // Busy-wait to simulate work
        }
        System.out.println("Completed batch with IDs: " + ids + " at " + System.currentTimeMillis());
        return userRepository.findByIdIn(ids);
    }
    
    public void saveAllUsers(List<AppUser> users) {
       userRepository.saveAll(users);
    }

    public List<AppUser> generateUsers(int count) {
        List<AppUser> users = new ArrayList<>();
        IntStream.range(0, count).forEach(i -> {
            AppUser user = new AppUser();
            user.setName("User " + i);
            user.setEmail("user" + i + "@example.com");
            users.add(user);
        });
        return users;
    }

    public void insertBulkUsers(int count) {
        List<AppUser> users = generateUsers(count);
        saveAllUsers(users);
    }
}
