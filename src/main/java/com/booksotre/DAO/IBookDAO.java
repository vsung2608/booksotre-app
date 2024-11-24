package com.booksotre.DAO;

import java.util.List;

import com.booksotre.model.BookModel;

public interface IBookDAO {
    List<BookModel> findAll();

    List<BookModel> findByName(String name);

    List<BookModel> findByCategory(int category);

    void createBook(BookModel book);

    void updateBook(BookModel book);

    void deleteBook(BookModel book);

    int getQuantityById(int id);

    BookModel findOne(String isbn);

    BookModel findById(int id);

    void setQuantityBook(int id, int quantity);

    List<BookModel> getHistoryBookByCustomerId(int customerId);

    List<BookModel> getHotProduct();

    List<BookModel> getBookByLimit(int limit, int offset);
}
