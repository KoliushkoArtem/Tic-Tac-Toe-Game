<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<html>
<head>
    <title>Tic Tac Toe</title>
</head>
<body>
<h1>Welcome to Tic Tac Toe game center</h1>
<h2>Please choose your game type</h2>
<div>
    <table class="main_page_table">
        <tr>
            <th>
                <form method="get" action="${pageContext.request.contextPath}/game/players">
                    <button type="submit" class="game_type_button">Player<br><br>VS<br><br>Player</button>
                </form>
            <th>
                <form method="get" action="${pageContext.request.contextPath}/game/algorithm">
                    <button type="submit" class="game_type_button">Player<br><br>VS<br><br>Algorithm<br>(HARD MODE)
                    </button>
                </form>
            </th>
            <th>
                <form method="get" action="${pageContext.request.contextPath}/game/random">
                    <button type="submit" class="game_type_button">Player<br><br>VS<br><br>Random</button>
                </form>
            </th>
        </tr>
    </table>
</div>
<h1>Statistic for games with Algorithm</h1>
<div>
    <table class="main_page_table">
        <tr>
            <th class="game_statistic">Algorithm WIN</th>
            <th class="game_statistic">Player WIN</th>
            <th class="game_statistic">DRAW</th>
        </tr>
        <tr>
            <td class="game_statistic">${statistic.algorithmWin}</td>
            <td class="game_statistic">${statistic.playerWin}</td>
            <td class="game_statistic">${statistic.draw}</td>
        </tr>
    </table>
</div>
</body>
</html>
