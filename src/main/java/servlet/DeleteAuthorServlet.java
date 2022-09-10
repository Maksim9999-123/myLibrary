package servlet;

import manager.AuthorManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteAuthor")
public class DeleteAuthorServlet extends HttpServlet {
    AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int author = Integer.parseInt(req.getParameter("authorId"));
        authorManager.deleteAuthor(author);
        resp.sendRedirect("/add/Author");
    }
}
