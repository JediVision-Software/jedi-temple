package com.forcelate.fetchers;

import com.forcelate.db.Db;
import com.forcelate.domain.Book;
import com.forcelate.domain.User;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.forcelate.fetchers.Fields.USER_ID_FIELD;

@Slf4j
@Service
public class UserDataFetcher implements DataFetcher<User> {

    private final Db db;

    @Autowired
    public UserDataFetcher(Db db) {
        this.db = db;
    }

    @Override
    public User get(DataFetchingEnvironment environment) {
        Map<String, Object> arguments = environment.getArguments();

        System.out.println("====");
        System.out.println(arguments);
        System.out.println("====");
        Book source = environment.getSource();
        System.out.println(source);
        System.out.println("====");

        Long userId = Long.parseLong(String.valueOf(arguments.get(USER_ID_FIELD)));
        LOGGER.debug("Fetching user by id = {}", userId);
        return db.findUserById(userId);
    }
}
