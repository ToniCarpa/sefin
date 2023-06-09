package servlet;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "register", urlPatterns = {"/register.do", "/register"})
public class Register extends HttpServlet {
    private PostService postService;

    public Register() throws ClassNotFoundException {
        super();
        this.postService = new PostService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("mail") != "" && request.getParameter("pass") != "") {
            String name = request.getParameter("name");
            String password = request.getParameter("pass");
            String email = request.getParameter("mail");
            String linkdn = request.getParameter("link");
            String gitlab = request.getParameter("git");

            if (postService.newUserNew(request, name, password, email, linkdn, gitlab)) {
                request.setAttribute("postList", this.postService.listPosts(request));
                getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
            }
        } else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    /*
    if(request.getParameter("mail")!=""&&request.getParameter("pass")!=""){
        postService.newUserNew(request);
        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    } else{
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
     */
}