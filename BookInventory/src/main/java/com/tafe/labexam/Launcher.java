package com.tafe.labexam;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class BookRepo {

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS books (id INT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255))";
    private static final String INSERT_BOOK_SQL = "INSERT INTO books (id, title, author) VALUES (?, ?, ?)";

    public void createDBAndTable() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_SQL);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoTable(Book book) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_SQL);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:testdb", "tafes", "0904tafesoftaware");
    }
}
