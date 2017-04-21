package com.jedivision.resource;

import com.jedivision.client.TaskClient;
import com.jedivision.dto.TaskDTO;
import com.jedivision.entity.User;
import com.jedivision.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserResource {

    private final UserService userService;
    private final TaskClient taskClient;

    @Autowired
    public UserResource(UserService userService, TaskClient taskClient) {
        this.userService = userService;
        this.taskClient = taskClient;
    }

    @RequestMapping(method = RequestMethod.GET)
    private List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable String id) {
        return userService.findOne(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST )
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(@RequestParam String id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/count/finished", method = RequestMethod.GET)
    public int countOfFinishedTask(@RequestParam String username) {
       return taskClient.findCountOfTaskByUsernameAndStatus(username, true);
    }

    @RequestMapping(value = "/count/unfinished", method = RequestMethod.GET)
    public int countOfUnfinishedTask(@RequestParam String username) {
        return taskClient.findCountOfTaskByUsernameAndStatus(username, false);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<TaskDTO> findUsersTasks(@RequestParam String username) {
        return taskClient.findAllUsersTask(username);
    }

    @RequestMapping(value = "/ex", method = RequestMethod.GET)
    public String throwEx() {
       return userService.throwException();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index() {
        userService.save(User.builder()
            .phoneNumber("07865874")
            .username("User1")
            .build());
        userService.save(User.builder()
            .phoneNumber("07887879")
            .username("User2")
            .build());
    }

}
