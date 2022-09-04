package manager;

import dbpoolconnection.ConnectionPool;
import dbpoolconnection.DBConnectionProvider;
import model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {

    private Connection conn = DBConnectionProvider.getProvider().getConnection();


    public void addAuthor(Author author) {
        String sql = "INSERT INTO `author` (`name`,`surname`,`email`,`age`) " +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                author.setId(id);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Author> getAllAuthor() {
        String sql = "select * from author";
        List<Author> result = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author author = Author.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .age(resultSet.getInt(5))
                        .build();
                result.add(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public Author getAuthorById(int id) {
        String sql = "SELECT * FROM author WHERE id=" + id;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return Author.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .email(rs.getString(4))
                        .age(rs.getInt(5))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateAuthor(Author author) {
        try {
            Statement statement = conn.createStatement();
            String query = String.format("UPDATE user SET name = '%s', surname = '%s', email='%s',age='%s' WHERE id=" + author.getId(),
                    author.getName(), author.getSurname(), author.getEmail(), author.getAge());
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAuthor(int id) {
        String sql = "DELETE from author where id = " + id;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
