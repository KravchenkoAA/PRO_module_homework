package com.gmail.a.a.kravchenko;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class DatabaseServlet extends HttpServlet {
    private HashMap<String, Long> statistic = new HashMap<String, Long>();
    private static volatile Database database = new Database();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] keysDatabase = database.toGetCounters();
        Anketa data = (Anketa) req.getAttribute("data");

        for (int i = 0; i < data.getQuestions().size(); i++) {
            for (int j = 0; j < keysDatabase.length; j++) {
                if (keysDatabase[j].equals(data.getQuestions().get(i))) {
                    statistic.put(data.getQuestions().get(i), database.getDatabase().get(data.getQuestions().get(i)) + 1);
                }
            }
        }

        database.getDatabase().putAll(statistic);

        req.setAttribute("keysDatabase", database.toGetCounters());
        req.setAttribute("statistic", database);
        HttpSession session = req.getSession(false);
        if (session.getAttribute("sessionKey")==null) {
            session = req.getSession(true);
            session.setAttribute("statistic", database);
        } else {
            session.setAttribute("statistic", database);
        }

        resp.sendRedirect("results.jsp");
//        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/answers.jsp");
//        requestDispatcher.forward(req, resp);

//        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "utf-8"), true);
//        printWriter.println(getTemplates(database.getDatabase(), keysDatabase));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String[] keysDatabase = database.toGetCounters();
        session.setAttribute("statistic", database);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "utf-8"), true);
        printWriter.println(getTemplates(database.getDatabase(), keysDatabase));
    }

    private String getTemplates(HashMap<String, Long> statistic, String[] keysDatabase) {
        return "<!DOCTYPE HTML>" +
                "<html>" +
                "<head>" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"utf-8\">" +
                "    <title>Answers</title>" +
                "</head>" +
                "<body>" +
                "<div align=\"center\"><h4>Статистика</h4></div>" +
                "<ul>" +
                "<li>" + keysDatabase[0] + " : " + statistic.get(keysDatabase[0]) + "</li>" +
                "<li>" + keysDatabase[1] + " : " + statistic.get(keysDatabase[1]) + "</li>" +
                "<li>" + keysDatabase[2] + " : " + statistic.get(keysDatabase[2]) + "</li>" +
                "<li>" + keysDatabase[3] + " : " + statistic.get(keysDatabase[3]) + "</li>" +
                "<li>" + keysDatabase[4] + " : " + statistic.get(keysDatabase[4]) + "</li>" +
                "<li>" + keysDatabase[5] + " : " + statistic.get(keysDatabase[5]) + "</li>" +
                "<li>" + keysDatabase[6] + " : " + statistic.get(keysDatabase[6]) + "</li>" +
                "<li>" + keysDatabase[7] + " : " + statistic.get(keysDatabase[7]) + "</li>" +
                "<li>" + keysDatabase[8] + " : " + statistic.get(keysDatabase[8]) + "</li>" +
                "</ul>" +
                "<footer>" +
                "    <a href=\"answer.jsp\">Посмотреть свои ответы</a><br>" +
                "    <a href=\"index.jsp\">На главную</a>" +
                "</footer>" +
                "</body>" +
                "</html>";
    }

}