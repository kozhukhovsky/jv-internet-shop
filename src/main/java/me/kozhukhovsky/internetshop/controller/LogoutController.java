package me.kozhukhovsky.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession(true).invalidate();

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                cookie.setMaxAge(0);
                cookie.setValue("");
                resp.addCookie(cookie);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
