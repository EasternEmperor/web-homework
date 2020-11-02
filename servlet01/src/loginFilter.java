import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;

@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"},
            initParams = {  //配置Filter初始化参数
                @WebInitParam(name = "encoding", value = "GBK"),
                @WebInitParam(name = "loginPage", value = "login.html")
            })
public class loginFilter implements Filter {
    //FilterConfig可用于访问Filter的配置信息
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取filter的配置参数
        String encoding = config.getInitParameter("encoding");
        String loginPage = config.getInitParameter("loginPage");

        // 设置request编码用的字符集
        servletRequest.setCharacterEncoding(encoding);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取客户请求的页面
        String requestUrl = request.getRequestURI();
        // 判断用户是否登录
        HttpSession session = request.getSession();
        // 没有登陆，且访问页面不是登录页面
        if (session.getAttribute("user") == null && (!requestUrl.contains(loginPage) && !requestUrl.contains("loginServlet"))) {
            // 重定向到登陆页面
            response.sendRedirect("login.html");
        }
        else {
            // 放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        this.config = null;
    }
}
