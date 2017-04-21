package com.jedivision.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jedivision.entity.Task;
import com.jedivision.repository.TaskRepository;
import com.jedivision.service.TaskService;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @HystrixCommand
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    @HystrixCommand
    public void delete(int id) {
        taskRepository.delete(id);
    }

    @Override
    @HystrixCommand
    public List<Task> findAll() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    @HystrixCommand
    public List<Task> findByUsernameAndStatus(String username, boolean status) {
        return taskRepository.findTaskByUsernameAndStatus(username, status);
    }

    @Override
    @HystrixCommand
    public List<Task> findByUsername(String username) {
        return taskRepository.findTaskByUsername(username);
    }
}
