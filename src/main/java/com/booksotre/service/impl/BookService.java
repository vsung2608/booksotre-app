package com.booksotre.service.impl;

import java.util.List;

import com.booksotre.DAO.IBookDAO;
import com.booksotre.DAO.impl.BookDAO;
import com.booksotre.model.BookModel;
import com.booksotre.service.IBookService;

public class BookService implements IBookService {

    private final IBookDAO bookDAO = new BookDAO();

    @Override
    public List<BookModel> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public List<BookModel> findByTitle(String title) {
        return bookDAO.findByName(title);
    }

    @Override
    public List<BookModel> findByCategory(int categoryId) {
        return bookDAO.findByCategory(categoryId);
    }

    @Override
    public void createBook(BookModel book) {
        bookDAO.createBook(book);
    }

    @Override
    public void updateBook(BookModel book) {
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(BookModel book) {
        bookDAO.deleteBook(book);
    }

    @Override
    public String getStatus(int bookId) {
        return (bookDAO.getQuantityById(bookId) > 0) ? "Còn hàng" : "Hết hàng";
    }

    @Override
    public BookModel findOne(String isbn) {
        return bookDAO.findOne(isbn);
    }

    @Override
    public BookModel findById(int bookId) {
        return bookDAO.findById(bookId);
    }

    @Override
    public void setQuantity(int id, int quantity) {
        bookDAO.setQuantityBook(id, quantity);
    }

    @Override
    public List<BookModel> getTopBook() {
        return bookDAO.getHotProduct();
    }

    @Override
    public List<BookModel> getByPaging(int limit, int offset) {
        return bookDAO.getBookByLimit(limit, offset);
    }
}
