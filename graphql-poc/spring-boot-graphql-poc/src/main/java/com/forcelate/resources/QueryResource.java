package com.forcelate.resources;

import com.forcelate.services.GraphQLService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/query")
public class QueryResource {

    // GraphQL
    private final GraphQL graphQL;

    @Autowired
    public QueryResource(GraphQLService graphQLService) throws IOException {
        this.graphQL = graphQLService.getGraphQL();
    }

    @PostMapping
    public ResponseEntity query(@RequestBody String query) {
        LOGGER.debug("GraphQL client requested json by query = {}", query);
        ExecutionResult executionResult = graphQL.execute(query);
        LOGGER.debug("Execution result by query = {}. Result = {}", query, executionResult);
        List<GraphQLError> executionErrors = executionResult.getErrors();
        if (executionErrors.isEmpty()) {
            return ok(executionResult.getData());
        } else {
            LOGGER.error("Errors during processing client query = {}. Errors = {}", query, executionErrors);
            return badRequest().body(executionErrors);
        }
    }
}
