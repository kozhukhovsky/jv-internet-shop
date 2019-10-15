package me.kozhukhovsky.internetshop.web.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.model.User;
import me.kozhukhovsky.internetshop.service.UserService;

@WebFilter(filterName = "authorizationFilter")
public class AuthorizationFilter implements Filter {
    @Inject
    private static UserService userService;

    private Map<String, String> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/admin/users", "ADMIN");
        protectedUrls.put("/servlet/admin/createItem", "ADMIN");
        protectedUrls.put("/servlet/admin/deleteItem", "ADMIN");
        protectedUrls.put("/servlet/admin/deleteUser", "ADMIN");
        protectedUrls.put("/servlet/admin/items", "ADMIN");
        protectedUrls.put("/servlet/bucket", "USER");
        protectedUrls.put("/servlet/addItemToBucket", "USER");
        protectedUrls.put("/servlet/removeItemFromBucket", "USER");
        protectedUrls.put("/servlet/completeOrder", "USER");
        protectedUrls.put("/servlet/orders", "USER");
        protectedUrls.put("/servlet/createBucket", "USER");
        protectedUrls.put("/servlet/deleteOrder", "USER");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            processUnAuthenticated(req, resp);
            return;
        }

        String requestedUrl = req.getRequestURI().replace(req.getContextPath(), "");
        String roleName = protectedUrls.get(requestedUrl);
        if (roleName == null) {
            processAuthenticated(filterChain, req, resp);
        }

        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("MATE")) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null) {
            processUnAuthenticated(req, resp);
            return;
        } else {
            Optional<User> user = userService.getByToken(token);
            if (user.isPresent()) {
                if (verifyRole(user.get(), roleName)) {
                    processAuthenticated(filterChain, req, resp);
                    return;
                } else {
                    processDenied(req, resp);
                    return;
                }
            } else {
                processUnAuthenticated(req, resp);
                return;
            }
        }

    }

    private boolean verifyRole(User user, String roleName) {
        return user.getRoles().stream().anyMatch(r -> r.getName().equals(roleName));
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    private void processUnAuthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private void processAuthenticated(FilterChain filterChain, HttpServletRequest req,
            HttpServletResponse resp) throws IOException, ServletException {
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
