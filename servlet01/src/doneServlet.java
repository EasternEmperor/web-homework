import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "doneServlet", urlPatterns = {"/doneServlet"})
public class doneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s1 = (String) request.getAttribute("a");
        String s2 = (String) request.getAttribute("b");
        int a = Integer.parseInt(s1);
        int b = Integer.parseInt(s2);
        int sum = a + b;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(a + " plus " + b + " is "+sum);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
