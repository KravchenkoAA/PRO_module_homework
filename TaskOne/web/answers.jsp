<%@ page import="com.gmail.a.a.kravchenko.Database" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>Статистика ответов</title>
</head>
<body>
<%
    Database statistic = (Database) session.getAttribute("statistic");
    String[] keysDatabase;
    if (statistic == null && (session.getAttribute("sessionKey")==null)) {
        statistic = new Database();
        keysDatabase = statistic.toGetCounters();
    } else {
        if ("tempKey".equals(session.getAttribute("sessionKey"))) {
//            session.invalidate();
        }
        keysDatabase = statistic.toGetCounters();
    }
%>
<div align="center"><h4>Статистика</h4></div>
<ul>
    <li><%=keysDatabase[0]%> : <%=statistic.getDatabase().get(keysDatabase[0])%>
    </li>
    <li><%=keysDatabase[1]%> : <%=statistic.getDatabase().get(keysDatabase[1])%>
    </li>
    <li><%=keysDatabase[2]%> : <%=statistic.getDatabase().get(keysDatabase[2])%>
    </li>
    <li><%=keysDatabase[3]%> : <%=statistic.getDatabase().get(keysDatabase[3])%>
    </li>
    <li><%=keysDatabase[4]%> : <%=statistic.getDatabase().get(keysDatabase[4])%>
    </li>
    <li><%=keysDatabase[5]%> : <%=statistic.getDatabase().get(keysDatabase[5])%>
    </li>
    <li><%=keysDatabase[6]%> : <%=statistic.getDatabase().get(keysDatabase[6])%>
    </li>
    <li><%=keysDatabase[7]%> : <%=statistic.getDatabase().get(keysDatabase[7])%>
    </li>
    <li><%=keysDatabase[8]%> : <%=statistic.getDatabase().get(keysDatabase[8])%>
    </li>
</ul>
<a href="answer.jsp">Посмотреть свои ответы</a><br><br>
<footer>
    <a href="index.jsp">На главную</a>
</footer>
</body>
</html>