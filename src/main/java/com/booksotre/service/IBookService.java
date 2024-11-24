package com.booksotre.service;

import java.util.List;

import com.booksotre.model.BookModel;

public interface IBookService {
    List<BookModel> findAll();

    List<BookModel> findByTitle(String title);

    List<BookModel> findByCategory(int categoryId);

    void createBook(BookModel book);

    void updateBook(BookModel book);

    void deleteBook(BookModel book);

    String getStatus(int bookId);

    BookModel findOne(String isbn);

    BookModel findById(int bookId);

    void setQuantity(int id, int quantity);

    List<BookModel> getTopBook();

    List<BookModel> getByPaging(int limit, int offset);
}
