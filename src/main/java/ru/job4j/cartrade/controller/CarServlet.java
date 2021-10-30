package ru.job4j.cartrade.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cartrade.repository.AdvRepositoryImpl;
import ru.job4j.cartrade.model.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CarServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        List<Car> cars = AdvRepositoryImpl.instOf().findAllCars();
        resp.setContentType("application/json; charset=utf-8");
        String json = GSON.toJson(cars);
        OutputStream output = resp.getOutputStream();
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
