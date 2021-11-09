package ru.job4j.cartrade.controller;

import ru.job4j.cartrade.repository.AdvRepository;
import ru.job4j.cartrade.repository.AdvRepositoryImpl;
import ru.job4j.cartrade.model.User;
import ru.job4j.cartrade.service.AdsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AdsService service = new AdsService();
        User user = service.findUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            req.setAttribute("error", "Не верный email или пароль.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.do");
        }
    }
}