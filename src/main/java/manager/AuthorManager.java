package manager;

import com.mysql.cj.jdbc.ConnectionWrapper;
import dbpoolconnection.ConnectionPool;
import dbpoolconnection.DBConnectionProvider;
import model.Author;
import model.AuthorRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {
    private ConnectionPool pool = new ConnectionPool();
    // private Connection conn = DBConnectionProvider.getProvider().getConnection();

    private Connection conn = null;

    public void addAuthor(Author author) {
        String sql = "INSERT INTO `author` (`name`,`surname`,`email`,`age`,profile_pic,password,author_role) " +
                "VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conn = pool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.setString(5, author.getProfilePic());
            ps.setString(6, author.getPassword());
            ps.setString(7, author.getAuthorRole().name());

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
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author author = Author.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .age(resultSet.getInt(5))
                        .profilePic(resultSet.getString(6))
                        .password(resultSet.getString(7))
                        .authorRole(AuthorRole.valueOf(resultSet.getString(8)))
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
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return Author.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .email(rs.getString(4))
                        .age(rs.getInt(5))
                        .profilePic(rs.getString(6))
                        .password(rs.getString(7))
                        .authorRole(AuthorRole.valueOf(rs.getString(8)))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateAuthor(Author author) {
        String sql = "update author set name = ?, surname = ?, email = ?, age = ?,profile_pic = ?,password = ?, author_role = ? where id = ?";
        try {
            Connection conn = pool.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setInt(4, author.getAge());
            preparedStatement.setString(5, author.getProfilePic());
            preparedStatement.setString(6, author.getPassword());
            preparedStatement.setString(7, author.getAuthorRole().name());
            preparedStatement.setInt(8, author.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteAuthor(int id) {
        String sql = "DELETE from author where id = " + id;
        try {
            Connection conn = pool.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Author getAuthorByEmailAndPassword(String email,String password) {
        String sql = "SELECT * FROM author WHERE email = ? and password = ? ";
        try {
            Connection conn = pool.getConnection();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1,email);
            pstatement.setString(2,password);
            ResultSet rs = pstatement.executeQuery();
            if (rs.next()) {
                return Author.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .email(rs.getString(4))
                        .age(rs.getInt(5))
                        .profilePic(rs.getString(6))
                        .password(rs.getString(7))
                        .authorRole(AuthorRole.valueOf(rs.getString(8)))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Author getAuthorByEmail(String email) {
        String sql = "SELECT * FROM author WHERE email = ? ";
        try {
            Connection conn = pool.getConnection();
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1,email);
            ResultSet rs = pstatement.executeQuery();
            if (rs.next()) {
                return Author.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .email(rs.getString(4))
                        .age(rs.getInt(5))
                        .profilePic(rs.getString(6))
                        .password(rs.getString(7))
                        .authorRole(AuthorRole.valueOf(rs.getString(8)))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
