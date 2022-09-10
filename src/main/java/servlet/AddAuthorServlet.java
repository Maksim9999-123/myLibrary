package servlet;

import manager.AuthorManager;

import model.Author;
import model.AuthorRole;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet(urlPatterns = "/add/Author")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddAuthorServlet extends HttpServlet {

    private static final String IMAGE_PATH = "C:\\Users\\PC user\\Desktop\\";

    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addAuthor.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (authorManager.getAuthorByEmail(email) != null) {
            req.setAttribute("msg","Author akready exists");
            req.getRequestDispatcher("/WEB-INF/addAuthor.jsp").forward(req,resp);
        } else {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");
            int age = Integer.parseInt(req.getParameter("age"));
            Part profilPicPart = req.getPart("profilePic");
            AuthorRole authorRole = AuthorRole.valueOf(req.getParameter("author_role"));
            String fileName = null;
            if (profilPicPart != null) {
                long nanoTime = System.nanoTime();
                fileName = nanoTime + "_" + profilPicPart.getName();
                profilPicPart.write(IMAGE_PATH + fileName);
            }

            Author author = Author.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .age(age)
                    .profilePic(fileName)
                    .authorRole(authorRole)
                    .password(password)
                    .build();
            authorManager.addAuthor(author);
            resp.sendRedirect("/login");
        }
    }


}
