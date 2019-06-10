package com.gmail.a.a.kravchenko;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class QuestionServlet extends HttpServlet {
    private Shape colors = new Color();
    private Shape drinks = new Drink();
    private Shape musicTime = new MusicTime();
    private Anketa anketa = new Anketa();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("utf-8");
        anketa.setName(req.getParameter("name"));
        anketa.setSurname(req.getParameter("surname"));
        anketa.setAge(req.getParameter("age"));
        anketa.setAnswer(0, toAnalyze("drink", drinks, req));
        anketa.setAnswer(1, toAnalyze("color", colors, req));
        anketa.setAnswer(2, toAnalyze("musicTime", musicTime, req));
        HttpSession session = req.getSession(false);
        if (session.getAttribute("sessionKey")!=null) {
            session.setAttribute("data", anketa);
        }
        req.setAttribute("data", anketa);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/database");
        requestDispatcher.forward(req, resp);
    }

    public static final String toAnalyze(String key, Shape values, HttpServletRequest req) {
        for (int i = 0; i < values.getSize(); i++) {
            if (values.getValue(i).equals(req.getParameter(key))) {
                return values.getValue(i);
            }
        }
        return "Не задано";
    }

    public static final String getTemplates(Anketa anketa) {
        return "<!DOCTYPE HTML>" +
                "<html>" +
                "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"utf-8\">" +
                "    <title>Answer</title>" +
                "</head>" +
                "<body>" +
                "<p>" +
                "    Имя: " + anketa.getName() + "<br>" +
                "    Фамилия: " + anketa.getSurname() + "<br>" +
                "    Возраст: " + anketa.getAge() +
                "</p>" +
                "<p>" +
                "<ul>" +
                "    <li>Любимый напиток: " + anketa.getAnswer(1) + "</li>" +
                "    <li>Любимый цвет: " + anketa.getAnswer(2) + "</li>" +
                "    <li>Как часто слушаете музыку: " + anketa.getAnswer(3) + "</li>" +
                "</p>" +
                "<footer><a href=\"index.jsp\">На главную</a></footer>" +
                "</body>" +
                "</html>";
    }
}
