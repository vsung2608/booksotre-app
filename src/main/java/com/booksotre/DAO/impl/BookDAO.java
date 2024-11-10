package com.booksotre.DAO.impl;

import java.util.List;

import com.booksotre.DAO.IBookDAO;
import com.booksotre.mapper.impl.BookMapper;
import com.booksotre.model.BookModel;

public class BookDAO extends AbstractDAO<BookModel> implements IBookDAO {

    @Override
    public List<BookModel> findAll() {
        String query =
                "SELECT book_id, category_id, title, isbn, author, publisher, price, quantity, image, description FROM Book;";
        return query(query, new BookMapper());
    }

    @Override
    public List<BookModel> findByName(String name) {
        String query =
                """
                        SELECT book_id, category_id, title, isbn, author, publisher, price, quantity, image, description
                        FROM Book
                        WHERE title LIKE ?
                        """;
        return query(query, new BookMapper(), "%" + name + "%");
    }

    @Override
    public List<BookModel> findByCategory(int category) {
        String query =
                """
                        SELECT book_id, category_id, title, isbn, author, publisher, price, quantity, image, description
                        FROM Book
                        WHERE category_id = ?
                        """;
        return query(query, new BookMapper(), category);
    }

    @Override
    public void createBook(BookModel book) {
        String query =
                """
                        INSERT INTO Book(title, isbn, author, publisher, price, quantity, image, description)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                        """;
        insert(
                query,
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPrice(),
                book.getQuantity(),
                book.getImage(),
                book.getDescription());
    }

    @Override
    public void updateBook(BookModel book) {
        String query =
                """
                        UPDATE Book
                        SET title = ?, isbn = ?, author = ?, publisher = ?, price = ?, quantity = ?, image = ?, description = ?
                        WHERE book_id = ?;
                        """;
        update(
                query,
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPrice(),
                book.getQuantity(),
                book.getImage(),
                book.getDescription(),
                book.getBookId());
    }

    @Override
    public void deleteBook(BookModel book) {
        String query = "DELETE FROM Book WHERE book_id = ?;";
        delete(query, book.getBookId());
    }

    @Override
    public int getQuantityById(int id) {
        String query = "SELECT quantity FROM Book WHERE book_id = ?;";
        return count(query, id);
    }

    @Override
    public BookModel findOne(String isbn) {
        String query =
                """
                SELECT book_id, category_id, title, isbn, author, publisher, price, quantity, image, description
                FROM Book
                WHERE isbn = ?
                """;
        List<BookModel> list = query(query, new BookMapper(), isbn);
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public BookModel findById(int id) {
        String query = """
                SELECT book_id, category_id, title, isbn, author, publisher, price, quantity, image, description
                FROM Book
                WHERE book_id = ?
                """;

        List<BookModel> list = query(query, new BookMapper(), id);
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public void setQuantityBook(int id, int quantity) {
        String query = "UPDATE Book SET quantity = quantity - ? WHERE book_id = ?;";
        update(query, quantity, id);
    }

    @Override
    public List<BookModel> getHistoryBookByCustomerId(int customerId) {
        String query = """
                        SELECT *
                        FROM Book
                        WHERE book_id IN (SELECT book_id
                                          FROM Order_detail od INNER JOIN
                                                (SELECT order_id
                                                FROM Orders
                                                WHERE customer_id = ?) o ON od.order_id = o.order_id);
                        """;
        return query(query, new BookMapper(), customerId);
    }

    @Override
    public List<BookModel> getHotProduct() {
        String query = """
                SELECT b.book_id as book_id, category_id, title, isbn, author, publisher, price, quantity, image, description
                FROM Book b INNER JOIN (
                                SELECT book_id
                                FROM order_detail
                                GROUP BY book_id
                                ORDER BY SUM(quantity) DESC
                                LIMIT 6) t ON b.book_id = t.book_id;
                """;
        return query(query, new BookMapper());
    }
}
