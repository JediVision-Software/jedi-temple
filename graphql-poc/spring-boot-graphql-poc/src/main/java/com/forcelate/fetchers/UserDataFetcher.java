package com.forcelate.fetchers;

import com.forcelate.db.Db;
import com.forcelate.domain.User;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserDataFetcher implements DataFetcher<User> {
    private static final String USER_ID_FIELD = "id";

    private final Db db;

    @Autowired
    public UserDataFetcher(Db db) {
        this.db = db;
    }

    @Override
    public User get(DataFetchingEnvironment environment) {
        Map<String, Object> arguments = environment.getArguments();
        Long userId = Long.valueOf(String.valueOf(arguments.get(USER_ID_FIELD)));
        LOGGER.debug("Fetching user by id = {}", userId);
        return db.findUserById(userId);
    }
}
