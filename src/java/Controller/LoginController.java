/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import com.tickaroo.tikxml.converter.htmlescape.StringEscapeUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    private static final int MAX_LOGIN_ATTEMPTS = 3; // Maximum allowed login attempts
    private static final int LOCKOUT_DURATION = 30 * 1000; // Lockout duration in milliseconds

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/logout")) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = sanitizeInput(request.getParameter("user")); // Sanitize username input
        String password = sanitizeInput(request.getParameter("pass")); // Sanitize password input

        if (isUserLockedOut(request, username)) {
            HttpSession session = request.getSession();
            Long lockoutStartTime = (Long) session.getAttribute("lockoutStartTime");
            long elapsedTime = System.currentTimeMillis() - lockoutStartTime;
            long remainingTime = LOCKOUT_DURATION - elapsedTime;
            int remainingSeconds = (int) (remainingTime / 1000);
            request.setAttribute("errorMessage", "Compte verrouillé, réessayez après " + remainingSeconds + " secondes.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (isValidCredentials(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            resetFailedLoginAttempts(request, username);
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            incrementFailedLoginAttempts(request, username);

            if (isMaxLoginAttemptsExceeded(request, username)) {
                lockUserAccount(request, username);
                request.setAttribute("errorMessage", "Votre compte est verrouillé. Veuillez réessayer plus tard.");
            } else {
                request.setAttribute("errorMessage", "Identifiants invalides");
            }

            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    private boolean isValidCredentials(String username, String password) {
        // TODO: Implement your authentication logic here (e.g., check against database)
        // Return true if the credentials are valid, otherwise false
        return "admin".equals(username) && "admin".equals(password);
    }

    private void incrementFailedLoginAttempts(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
        if (failedAttempts == null) {
            failedAttempts = 1;
        } else {
            failedAttempts++;
        }
        session.setAttribute("failedAttempts", failedAttempts);
    }

    private boolean isMaxLoginAttemptsExceeded(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
        return failedAttempts != null && failedAttempts >= MAX_LOGIN_ATTEMPTS;
    }

    private void lockUserAccount(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.setAttribute("accountLocked", true);
        session.setAttribute("lockoutStartTime", System.currentTimeMillis());
    }

    private boolean isUserLockedOut(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        Boolean accountLocked = (Boolean) session.getAttribute("accountLocked");
        Long lockoutStartTime = (Long) session.getAttribute("lockoutStartTime");

        if (accountLocked != null && lockoutStartTime != null) {
            long elapsedTime = System.currentTimeMillis() - lockoutStartTime;
            if (elapsedTime < LOCKOUT_DURATION) {
                return true;
            } else {
                session.removeAttribute("accountLocked");
                session.removeAttribute("lockoutStartTime");
                session.removeAttribute("failedAttempts");
            }
        }

        return false;
    }

    private void resetFailedLoginAttempts(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.removeAttribute("failedAttempts");
    }

   private String sanitizeInput(String input) {
    // Use Java Encoder library for input sanitization XSS Atacks
    return   StringEscapeUtils.escapeHtml4(input);
}

}
