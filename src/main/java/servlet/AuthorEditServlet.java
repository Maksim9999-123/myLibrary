package servlet;

import manager.AuthorManager;
import model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/author/edit")
public class AuthorEditServlet extends HttpServlet {

    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        Author author = authorManager.getAuthorById(authorId);
        request.setAttribute("author", author);
        request.getRequestDispatcher("/WEB-INF/editAuthor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        Author author = Author.builder()
                .id(authorId)
                .name(name)
                .surname(surname)
                .email(email)
                .age(age)
                .build();
        authorManager.updateAuthor(author);

        resp.sendRedirect("/get/author");
    }

}

