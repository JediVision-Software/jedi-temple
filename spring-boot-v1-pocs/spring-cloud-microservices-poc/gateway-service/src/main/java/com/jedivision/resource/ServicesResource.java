package com.jedivision.resource;

import com.jedivision.clients.TaskClient;
import com.jedivision.clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicesResource {
    private final TaskClient taskClient;
    private final UserClient userClient;

    @Autowired
    public ServicesResource(TaskClient taskClient, UserClient userClient) {
        this.taskClient = taskClient;
        this.userClient = userClient;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index() {
        taskClient.init();
        userClient.init();
    }
}
