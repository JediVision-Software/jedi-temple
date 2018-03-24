package com.forcelate.fetchers;

import com.forcelate.db.Db;
import com.forcelate.domain.Book;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.forcelate.fetchers.Fields.BOOK_ID_FIELD;

@Slf4j
@Service
public class BookDataFetcher implements DataFetcher<Book> {

    private final Db db;

    @Autowired
    public BookDataFetcher(Db db) {
        this.db = db;
    }

    @Override
    public Book get(DataFetchingEnvironment environment) {
        Map<String, Object> arguments = environment.getArguments();
        Long userId = Long.parseLong(String.valueOf(arguments.get(BOOK_ID_FIELD)));
        LOGGER.debug("Fetching book by id = {}", userId);
        return db.findBookById(userId);
    }
}
