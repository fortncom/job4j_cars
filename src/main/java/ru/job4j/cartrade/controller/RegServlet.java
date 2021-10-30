package ru.job4j.cartrade.controller;

import ru.job4j.cartrade.repository.AdvRepository;
import ru.job4j.cartrade.repository.AdvRepositoryImpl;
import ru.job4j.cartrade.model.User;

import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AdvRepository store = AdvRepositoryImpl.instOf();
        User user = store.findUserByEmail(email);
        if (user == null) {
            store.save(User.of(name, email, password, store.findRoleById(1)));
            User newUser = store.findUserByEmail(email);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", newUser);
            resp.sendRedirect(req.getContextPath() + "/index.do");
        } else {
            req.setAttribute("error", "Данный email уже был зарегистрирован.");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}