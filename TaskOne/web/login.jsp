<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//RU">
<html>
<head>
    <title>Вход в личный кабинет</title>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
</head>
<body>
<% String login = (String) session.getAttribute("sessionKey");%>
<%if (login == null || "".equals(login)) { %>
<form action="/login" method="post">
    Введите имя:
    <input type="text" name="login"><br>
    Введите пароль:
    <input type="password" name="password"><br>
    <input type="submit" value="Вход"><br><br><br><br><br>
</form>
<% } else { %>
<%--<%String sessionKey = (String) session.getAttribute("sessionKey");%>--%>
<%--<%session.setAttribute("sessionKey", sessionKey);%>--%>
<h4>Вы успешно вошли в систему</h4>
<a href="anketa.jsp">Пройти тестирование</a><br>
<a href="answer.jsp">Посмотреть свои ответы</a><br>
<a href="/database?stat=stable">Посмотреть статистику</a><br><br>
<a href="/login?temp=exit">Выйти</a><br><br><br><br>
<% };%>
<footer>
    <a href="index.jsp">На главную</a>
</footer>
</body>
</html>
