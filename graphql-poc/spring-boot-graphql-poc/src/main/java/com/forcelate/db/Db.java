package com.forcelate.db;

import com.forcelate.domain.Book;
import com.forcelate.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Db {
    private List<User> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        // books
        Book book1 = Book.builder()
                .id(1L)
                .title("The Hunger Games")
                .author("Suzanne Collins")
                .build();

        Book book2 = Book.builder()
                .id(2L)
                .title("To Kill a Mockingbird")
                .author("Harper Lee")
                .build();

        Book book3 = Book.builder()
                .id(3L)
                .title("Pride and Prejudice")
                .author("Jane Austen")
                .build();

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

        // books: update user reference
        book1.setUserId(user1.getId());
        book2.setUserId(user1.getId());
        book3.setUserId(user2.getId());

        // books: save
        this.books.add(book1);
        this.books.add(book2);
        this.books.add(book3);

        // users: save
        this.users.add(user1);
        this.users.add(user2);
    }

    // Public API => @Repository (using Mongodb, MySQL or any database provider)
    public User findUserById(Long userId) {
        return this.users.stream()
                .filter(item -> userId.equals(item.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<User> findUsers() {
        return this.users;
    }

    public Book findBookById(Long bookId) {
        return this.books.stream()
                .filter(item -> bookId.equals(item.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<Book> findBooks() {
        return this.books;
    }

    public List<Book> findBooksByIds(List<Long> bookIds) {
        return this.books.stream()
                .filter(item -> bookIds.contains(item.getId()))
                .collect(Collectors.toList());
    }
}
