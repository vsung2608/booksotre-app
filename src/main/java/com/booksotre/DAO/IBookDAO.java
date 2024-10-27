package com.booksotre.DAO;

import java.util.List;

import com.booksotre.model.BookModel;

public interface IBookDAO {
    List<BookModel> findAll();

    List<BookModel> findByName(String name);

    void createBook(BookModel book);

    void updateBook(BookModel book);

    void deleteBook(BookModel book);

    int getQuantityById(int id);

    BookModel findOne(String isbn);
}
