package servlet;

import manager.AuthorManager;
import model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private AuthorManager authorManager = new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Author author = authorManager.getAuthorByEmailAndPassword(email,password);
        if(author==null){
            req.setAttribute("msg","Author does not exist");
            req.getRequestDispatcher("/WEB-INF/login.jsp");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("author",author);
            resp.sendRedirect("/");

        }



    }
}
