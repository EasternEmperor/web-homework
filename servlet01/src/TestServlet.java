import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestServlet",urlPatterns = {"/Test"})
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        super.doGet(req, resp);
        resp.setContentType("text/html; charset = GBK");
        try {
            resp.getWriter().write("My First Servlet.");
        }
        catch(Exception e){
            System.out.println("Wrong!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        super.doPost(req, resp);
    }
}
