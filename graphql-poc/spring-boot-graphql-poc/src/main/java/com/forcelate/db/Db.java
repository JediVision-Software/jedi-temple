package com.forcelate.db;

import com.forcelate.domain.Book;
import com.forcelate.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Db {
    private List<User> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
    }

    public User findUserById(Long userId) {
        return users.stream()
                .filter(item -> userId.equals(item.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<User> findUsers() {
        return users;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public Book findBookById(Long bookId) {
        return books.stream()
                .filter(item -> bookId.equals(item.getId()))
                .findFirst()
                .orElse(null);
    }
}
