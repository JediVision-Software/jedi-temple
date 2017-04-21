package com.jedivision.resource;

import com.jedivision.entity.Task;
import com.jedivision.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskResource {

    private final TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public void save(@RequestBody Task task) {
        taskService.save(task);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void delete(@PathVariable Integer id) {
        taskService.delete(id);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int countFinishedTasksOfUser(@RequestParam String username, @RequestParam boolean status) {
        return taskService.findByUsernameAndStatus(username, status).size();
    }

    @RequestMapping(value = "/tasks/all", method = RequestMethod.GET)
    public List<Task> countFinishedTasksOfUser(@RequestParam String username) {
        return taskService.findByUsername(username);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index() {
        taskService.save(Task.builder()
            .title("Task1")
            .username("User1")
            .status(false)
            .build());
        taskService.save(Task.builder()
            .title("Task2")
            .username("User2")
            .status(false)
            .build());
        taskService.save(Task.builder()
            .title("Task3")
            .username("User2")
            .status(false)
            .build());
    }
}
