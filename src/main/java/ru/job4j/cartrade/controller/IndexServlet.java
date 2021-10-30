package ru.job4j.cartrade.controller;

import ru.job4j.cartrade.model.Advertisement;
import ru.job4j.cartrade.repository.AdvRepositoryImpl;
import ru.job4j.cartrade.service.AdsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
            List<Advertisement> ads = AdvRepositoryImpl.instOf().findAllAds();
            req.setAttribute("advertisements", ads);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AdsService service = new AdsService();
        List<Advertisement> ads = service.findByFilter(
                Integer.parseInt(req.getParameter("mark")),
                Integer.parseInt(req.getParameter("model")),
                Integer.parseInt(req.getParameter("minPrice")),
                Integer.parseInt(req.getParameter("maxPrice")));
        req.setAttribute("advertisements", ads);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
