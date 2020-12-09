<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<html>
<head>
    <title>Player VS Random</title>
</head>
<body>
<h1>Who should start the Game</h1>
<br><br><br><br><br>
<div>
    <table class="game_field_table">
        <tr>
            <th>
                <form method="get" action="${pageContext.request.contextPath}/game/random/user">
                    <button type="submit" class="button_600">You Starts</button>
                </form>
            </th>
            <th>
                <form method="get" action="${pageContext.request.contextPath}/game/random/random">
                    <button type="submit" class="button_600">Random Starts</button>
                </form>
            </th>
        </tr>
    </table>
</div>
</body>
</html>
