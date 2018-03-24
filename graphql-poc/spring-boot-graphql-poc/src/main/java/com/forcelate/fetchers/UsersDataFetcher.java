package com.forcelate.fetchers;

import com.forcelate.db.Db;
import com.forcelate.domain.User;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UsersDataFetcher implements DataFetcher<List<User>> {

    private final Db db;

    @Autowired
    public UsersDataFetcher(Db db) {
        this.db = db;
    }

    @Override
    public List<User> get(DataFetchingEnvironment environment) {
        return db.findUsers();
    }
}
