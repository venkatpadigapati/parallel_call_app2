package com.parallel.calls.proj2.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users2")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<AppUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser appUser) {
        return userService.createUser(appUser);
    }
    
    @PostMapping("/getUsersByIds")
    public List<AppUser> getUsersByIds(@RequestBody List<Long> ids) throws InterruptedException {
        return userService.getUsersByIds(ids);
    }
    
    @PostMapping("/insertBulkUsers")
    public String insertBulkUsers() {
    	userService.insertBulkUsers(3000);
        return "1000 users inserted successfully!";
    }
}

