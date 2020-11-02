import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logoutServlet", urlPatterns = {"/logoutServlet"})
public class logoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 销毁session
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession(false);    // 防止创建session
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<body>");
        if (session == null) {
            out.print("<script> alert(\"尚未登陆！\") </script>");
            out.print("</body>");
            out.print("</html>");
            response.sendRedirect("loginServlet");
        }
        else {
            out.print("<script> alert(\"退出登录成功！\") </script>");
            out.print("</body>");
            out.print("</html>");
            session.removeAttribute("user");   // 删除登录记录
        }
    }
}
