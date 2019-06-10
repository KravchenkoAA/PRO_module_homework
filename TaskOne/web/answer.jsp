<%@ page import="com.gmail.a.a.kravchenko.Anketa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//RU">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>Индивидуальные результаты</title>
</head>
<body>
<p>
        <% Anketa anketa = (Anketa) session.getAttribute("data");%>
        <% String sessionKey = (String) session.getAttribute("sessionKey");%>
        <% if (sessionKey == null || "tempKey".equals(sessionKey)) { %>
<h4>Сначала нужно авторизироваться</h4>
<% } else { %>
<% if (anketa == null) {
    Anketa newAnketa = new Anketa();
    anketa = newAnketa;
    anketa.setName("Не задано");
    anketa.setSurname("Не задано");
    anketa.setAge("Не задано");
    anketa.setAnswer(0, "Не задано");
    anketa.setAnswer(1, "Не задано");
    anketa.setAnswer(2, "Не задано");
}
    ;%>
Имя: <%= anketa.getName()%><br>
Фамилия: <%=anketa.getSurname()%><br>
Возраст: <%=anketa.getAge()%><br>
<ul>
    <li>Любимый напиток: <%=anketa.getAnswer(0)%>
    </li>
    <li>Любимый цвет: <%=anketa.getAnswer(1)%>
    </li>
    <li>Как часто слушаете музыку: <%=anketa.getAnswer(2)%>
    </li>
</ul>
<%}%>
<footer>
    <a href="index.jsp">На главную</a><br>
</footer>
</body>
</html>