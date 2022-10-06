package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterRequest implements Filter
{
    private FilterConfig config = null;

    public void init (FilterConfig config) throws ServletException
    {
        this.config = config;
    }

    public void doFilter (ServletRequest req, ServletResponse resp,
                          FilterChain chain) throws IOException, ServletException
    {
        if (config.getInitParameter("active").equalsIgnoreCase("true")) {
            int totalRequests;
            if (req.getServletContext().getAttribute("totalRequests") != null)
                totalRequests = (Integer) req.getServletContext().getAttribute("totalRequests");
            else
                totalRequests = 1;
            System.out.println(("method: " + ((HttpServletRequest)req).getMethod() + ", path: " +
                    ((HttpServletRequest)req).getRequestURI() + ", totalRequests: " + totalRequests));
            req.getServletContext().setAttribute("totalRequests", ++totalRequests);
        }
        chain.doFilter(req, resp);
    }

    public void destroy()
    {
        config = null;
    }
}
