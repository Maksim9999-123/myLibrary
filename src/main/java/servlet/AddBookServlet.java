package servlet;

import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.AuthorRole;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/add/Book")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddBookServlet extends HttpServlet {

    private static final String IMAGE_PATH = "C:\\Users\\PC user\\Desktop\\";
    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = (Author) req.getSession().getAttribute("author");
        if (author != null) {
            List<Author> all = authorManager.getAllAuthor();
            req.setAttribute("authors", all);
            req.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(req, resp);

        } else {
            resp.sendRedirect("/");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String descriprion = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        Part bookPicPart = req.getPart("bookPic");
        String fileName = null;
        if (bookPicPart != null) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + bookPicPart.getName();
            bookPicPart.write(IMAGE_PATH + fileName);
        }
        Book book = Book.builder()
                .title(title)
                .description(descriprion)
                .price(price)
                .author(authorManager.getAuthorById(authorId))
                .bookPic(fileName)
                .build();
        bookManager.addBook(book);
        resp.sendRedirect("/add/Book");
    }
}
