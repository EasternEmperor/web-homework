import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        // 接收数据
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        PrintWriter out = response.getWriter();
        // 直接判断
        if ("admin".equals(username) && "123456".equals(pwd)) {
            out.print("<html>");
            out.print("<body>");
            out.print("<script> alert(\"登录成功\") </script>");
            out.print("</body>");
            out.print("</html>");
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
        }
        else {
            response.sendRedirect("login.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}