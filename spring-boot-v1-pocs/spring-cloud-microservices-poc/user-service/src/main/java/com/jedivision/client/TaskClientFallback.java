package com.jedivision.client;

import com.jedivision.dto.TaskDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskClientFallback implements TaskClient{
    @Override
    public List<TaskDTO> findAllUsersTask(@RequestParam("username") String username) {
        return new ArrayList<>();
    }

    @Override
    public int findCountOfTaskByUsernameAndStatus(@RequestParam("username") String username, @RequestParam("status") boolean status) {
        return 0;
    }
}
