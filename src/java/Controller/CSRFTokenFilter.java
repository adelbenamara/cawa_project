package Controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * CSRF token validation filter
 */
public class CSRFTokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestMethod = httpRequest.getMethod();

        if ("POST".equalsIgnoreCase(requestMethod)) {
            String csrfToken = httpRequest.getParameter("csrfToken");
            HttpSession session = httpRequest.getSession(false);
            String storedToken = (String) session.getAttribute("csrfToken");

            if (csrfToken == null || !csrfToken.equals(storedToken)) {
                // CSRF token is missing or doesn't match the stored token
                // Handle the error, e.g., redirect to an error page or display an error message

              
                // Set an error message in the request attribute and forward to an error page
                request.setAttribute("error_message", "CSRF Token Validation Failed");
                request.getRequestDispatcher("error.jsp").forward(request, response);

                return;
            }
        }

        // Proceed with the request handling
        chain.doFilter(request, response);
    }

    // Other methods (init, destroy) can be implemented as needed

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code
    }

    @Override
    public void destroy() {
        // Cleanup code
    }
}
