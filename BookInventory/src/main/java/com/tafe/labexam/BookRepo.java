import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookRepo {
    private static final String DB_URL = "jdbc:h2:mem:bookdb";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS books (id INT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255))";
    private static final String INSERT_BOOK_SQL = "INSERT INTO books (id, title, author) VALUES (?, ?, ?)";

    public void createDBAndTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            try (PreparedStatement stmt = conn.prepareStatement(CREATE_TABLE_SQL)) {
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoTable(Book book) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(INSERT_BOOK_SQL)) {
            stmt.setInt(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}