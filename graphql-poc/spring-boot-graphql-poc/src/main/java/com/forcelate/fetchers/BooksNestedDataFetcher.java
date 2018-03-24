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
import java.util.Map;

@Slf4j
@Service
public class BooksNestedDataFetcher implements DataFetcher<List<Book>> {

    private final Db db;

    @Autowired
    public BooksNestedDataFetcher(Db db) {
        this.db = db;
    }

    @Override
    public List<Book> get(DataFetchingEnvironment environment) {
        User user = environment.getSource();
        List<Long> bookIds = user.getBookIds();
        LOGGER.debug("Fetching nested books by bookIds = {}", bookIds);
        return db.findBooksByIds(bookIds);
    }
}
