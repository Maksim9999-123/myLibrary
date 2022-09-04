package servlet;

import manager.BookManager;
import model.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/get/book")
public class GetBookServlet extends HttpServlet {

    BookManager bookManager = new BookManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> all = bookManager.getAllBook();
        req.setAttribute("books",all);
        req.getRequestDispatcher("/WEB-INF/getBook.jsp").forward(req, resp);
    }
}

