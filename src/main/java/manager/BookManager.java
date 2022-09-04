package manager;


import dbpoolconnection.DBConnectionProvider;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private AuthorManager authorManager = new AuthorManager();
    private Connection conn = DBConnectionProvider.getProvider().getConnection();


    public void addBook(Book book) {
        String sql = "insert into book (title,decription,price,author_id) values(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                book.setId(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBook() {
        String sql = "select * from book";
        List<Book> resault = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Book book = Book.builder()
                        .id(rs.getInt(1))
                        .title(rs.getString(2))
                        .description(rs.getString(3))
                        .price(rs.getDouble(4))
                        .author(authorManager.getAuthorById(rs.getInt(5)))
                        .build();
                resault.add(book);
            }
            return resault;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteBook(int id) {
        String sql = "DELETE from book where id = " + id;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        try {
            Statement statement = conn.createStatement();
            String query = String.format("UPDATE book SET title = '%s', decription = '%s', price ='%s', WHERE id=" + book.getId(),
                    book.getTitle(), book.getDescription(), book.getPrice());
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM book WHERE id=" + id;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Book.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .price(resultSet.getDouble(4))
                        .author(authorManager.getAuthorById((resultSet.getInt(5))))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
}
