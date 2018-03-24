package com.forcelate.services;

import com.forcelate.db.Db;
import com.forcelate.domain.Book;
import com.forcelate.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;

@Service
public class DbService {

    private final Db db;

    @Autowired
    public DbService(Db db) {
        this.db = db;
    }

    @PostConstruct
    public void initialize() {
        // books
        Book book1 = Book.builder()
                .id(1L)
                .title("A")
                .title("A")
                .build();

        Book book2 = Book.builder()
                .id(2L)
                .title("A")
                .title("A")
                .build();

        Book book3 = Book.builder()
                .id(3L)
                .title("A")
                .title("A")
                .build();

        db.addBook(book1);
        db.addBook(book2);
        db.addBook(book3);
        // users
        User user1 = User.builder()
                .id(1L)
                .username("admin")
                .age(35)
                .rank(100)
                .bookIds(Arrays.asList(book1.getId(), book2.getId()))
                .build();

        User user2 = User.builder()
                .id(2L)
                .username("user")
                .age(20)
                .rank(50)
                .bookIds(Collections.singletonList(book3.getId()))
                .build();

        db.addUser(user1);
        db.addUser(user2);
    }
}
