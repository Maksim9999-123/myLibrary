package filter;

import model.Author;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/add/Book/**")
public class AuthorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Author author = (Author) req.getSession().getAttribute("author");
        if(author==null){
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("/");
        }else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
    }
}
