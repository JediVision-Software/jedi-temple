package com.forcelate.fetchers;

import com.forcelate.db.Db;
import com.forcelate.domain.Book;
import com.forcelate.domain.User;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserNestedDataFetcher implements DataFetcher<User> {

    private final Db db;

    @Autowired
    public UserNestedDataFetcher(Db db) {
        this.db = db;
    }

    @Override
    public User get(DataFetchingEnvironment environment) {
        Book book = environment.getSource();
        Long userId = book.getUserId();
        LOGGER.debug("Fetching nested user by userId = {}", userId);
        return db.findUserById(userId);
    }
}
