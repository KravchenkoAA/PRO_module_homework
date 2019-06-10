<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>Anketa</title>
</head>
<body>
<form action="/question" method="post">
    <p><input type="text" name="name">Введите имя<br></p>
    <p><input type="text" name="surname">Введите фамилию<br></p>
    <p><input type="text" name="age">Укажите свой возраст<br></p>
    <p>
    <h4>Какой напиток вам больше нравиться?</h4>
    <ol>
        <li>
            <input type="radio" id="drink1" name="drink" value="Чай">
            <label for="drink1">Чай</label>
        </li>
        <li>
            <input type="radio" id="drink2" name="drink" value="Кофе">
            <label for="drink2">Кофе</label>
        </li>
        <li>
            <input type="radio" id="drink3" name="drink" value="Пиво">
            <label for="drink3">Пиво</label>
    </ol>
    </p>
    <hr>

    <p>
    <h4>Какой цвет вам больше нравиться?</h4>
    <ol>
        <li>
            <input type="radio" id="color1" name="color" value="Зеленый">
            <label for="color1">Зеленый</label>
        </li>
        <li>
            <input type="radio" id="color2" name="color" value="Красный">
            <label for="color2">Красный</label>
        </li>
        <li>
            <input type="radio" id="color3" name="color" value="Голубой">
            <label for="color3">Голубой</label>
    </ol>
    </p>
    <hr>

    <p>
    <h4>Как часто вы слушаете музыку?</h4>
    <ol>
        <li>
            <input type="radio" id="musicTime1" name="musicTime" value="Часто">
            <label for="musicTime1">Часто</label>
        </li>
        <li>
            <input type="radio" id="musicTime2" name="musicTime" value="Иногда">
            <label for="musicTime2">Иногда</label>
        </li>
        <li>
            <input type="radio" id="musicTime3" name="musicTime" value="Никогда">
            <label for="musicTime3">Никогда</label>
    </ol>
    </p>
    <hr>
    <button type="submit">Отправить</button>
</form>
<footer><br><a href="index.jsp">На главную</a></footer>
</body>
</html>
