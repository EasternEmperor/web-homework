import javax.lang.model.type.NullType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addServlet", urlPatterns = {"/addServlet"})
public class addServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        if (a != null && b != null && a.matches("[0-9]+") && b.matches("[0-9]+")) {
            request.setAttribute("a", a);
            request.setAttribute("b", b);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/doneServlet"); //转发数据
            requestDispatcher.forward(request, response);
        }
        else {
            response.getWriter().append("参数错误！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
