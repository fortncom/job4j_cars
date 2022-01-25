package ru.job4j.cartrade.controller;

import ru.job4j.cartrade.model.Advertisement;
import ru.job4j.cartrade.model.User;
import ru.job4j.cartrade.service.AdsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdvServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        AdsService service = new AdsService();
        Advertisement adv = service.findAdvById(id);
        req.setAttribute("adv", adv);
        req.getRequestDispatcher("adv/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        boolean status = req.getParameter("status") != null;
        int markId = Integer.parseInt(req.getParameter("mark"));
        int modelId = Integer.parseInt(req.getParameter("model"));
        String advPhoto = req.getParameter("advPhoto");
        User user = (User) req.getSession().getAttribute("user");
        AdsService service = new AdsService();
        Advertisement advertisement = Advertisement.of(
                description, status, price, null, null, user);
        advertisement.setId(id);
        service.saveAdv(advertisement, markId, modelId, advPhoto);
        resp.sendRedirect(req.getContextPath() + "/index.do");
    }
}
