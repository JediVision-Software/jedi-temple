package com.jedivision.client;

import com.jedivision.dto.TaskDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "task-service", fallback = TaskClientFallback.class)
public interface TaskClient {
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/all", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<TaskDTO> findAllUsersTask(@RequestParam("username") String username);

    @RequestMapping(method = RequestMethod.GET, value = "/count", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    int findCountOfTaskByUsernameAndStatus(@RequestParam("username") String username, @RequestParam("status")  boolean status);
}
