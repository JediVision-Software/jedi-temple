package com.forcelate.resource;

import com.forcelate.entity.User;
import com.forcelate.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class BaseResource {

	private UserService userService;

	@Autowired
	public BaseResource(UserService userService) {
		this.userService = userService;
	}

    @GetMapping("/message")
    @ResponseStatus(value = HttpStatus.OK)
    public String message() {
        LOGGER.info("API message GET method executed successfully");
        return "Success";
    }

	@GetMapping(value="/user")
	public List<User> listUser(){
		LOGGER.info("API user GET method executed successfully");
		return userService.findAll();
	}

	@PostMapping(value = "/user")
	public User create(@RequestBody User user){
		LOGGER.info("API user POST method executed successfully");
		return userService.save(user);
	}

	@DeleteMapping(value = "/user/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String delete(@PathVariable(value = "id") Long id){
		LOGGER.info("API user DELETE method executed successfully");
		userService.delete(id);
		return "Success";
	}
}
