package com.booksotre.service;

import java.util.List;

import com.booksotre.model.BookModel;

public interface IBookService {
    List<BookModel> findAll();

    List<BookModel> findByTitle(String title);

    void createBook(BookModel book);

    void updateBook(BookModel book);

    void deleteBook(BookModel book);

    String getStatus(int bookId);

    BookModel findOne(String isbn);
}
