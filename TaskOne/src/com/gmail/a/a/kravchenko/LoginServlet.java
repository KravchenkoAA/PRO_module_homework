package com.gmail.a.a.kravchenko;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tempLogin = "user";
        String tempPassword = "password";
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "utf-8"), true);
        if (tempLogin.equals(login) && tempPassword.equals(password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("sessionKey", tempLogin);
            out.println(getTemplates("Вы успешно вошли в систему<br><br><a href=\"anketa.jsp\">Пройти тестирование</a><br>" +
                    "<a href=\"answer.jsp\">Посмотреть свои ответы</a><br><br><a href=\"/login?temp=exit\">Выйти</a><br><br><br><br>"));
        } else {
            out.println(getTemplates("Неправильно введен логин или пароль. <a href=\"login.jsp\">Повторить попытку</a><br><br><br>"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String temp = req.getParameter("temp");
        HttpSession session = req.getSession(false);
        if ("exit".equals(temp) && (session != null)) {
            session.removeAttribute("sessionKey");
            resp.sendRedirect("login.jsp");
        }
    }

    private static String getTemplates(String result) {
        return "<!DOCTYPE HTML>" +
                "<html>" +
                "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"utf-8\">" +
                "    <title>Login</title>" +
                "</head>" +
                "<body>" +
                result +
                "</body>" +
                "<footer><a href=\"index.jsp\">На главную</a></footer>" +
                "</body>" +
                "</html>";
    }
}
