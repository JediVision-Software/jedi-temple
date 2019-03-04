package com.forcelate.tests.resource;

import com.forcelate.acceptance.domain.processing.CaseAnnotation;
import com.forcelate.configuration.runner.AbstractResourceRunner;
import com.forcelate.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;



import static com.forcelate.acceptance.domain.processing.HttpTypes.GET;
import static com.forcelate.acceptance.domain.processing.HttpTypes.POST;

@Slf4j
public class UserResource extends AbstractResourceRunner {

    private UserUtils userUtils = new UserUtils();


    @CaseAnnotation(endpoint = "/api/user/{userId}", httpType = GET, description = "Find User by id")
    @Test
    public void findOne() {
        userUtils.findOne(1L);
    }

    @CaseAnnotation(endpoint = "/api/user", httpType = GET, description = "Find all users")
    @Test
    public void findAll() {
        userUtils.findAll();
    }

    @CaseAnnotation(endpoint = "/api/user", httpType = POST, description = "Add new User")
    @Test
    public void add() {
        userUtils.add();
    }
}
