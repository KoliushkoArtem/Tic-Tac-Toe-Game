<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<html>
<head>
    <title>Player VS Algorithm</title>
</head>
<body>
<c:choose>
    <c:when test="${game.player1win || game.player2win || game.draw}">
        <c:choose>
            <c:when test="${game.player1win}">
                <h1>You WIN<br>If you see this message, it's mean that developer is screwing up;)</h1>
            </c:when>
            <c:when test="${game.player2win}">
                <h1>MiniMax WIN<br>It should be happened</h1>
            </c:when>
            <c:when test="${game.draw}">`
                <h1>There is no winner<br>You are pretty good in this game ;)</h1>
            </c:when>
        </c:choose>
        <div>
            <table class="game_field_table">
                <tr>
                    <th>
                        <form method="get" action="${pageContext.request.contextPath}/game/algorithm">
                            <button type="submit" class="button_600">New Game</button>
                        </form>
                    </th>
                    <th>
                        <form method="get" action="${pageContext.request.contextPath}/">
                            <button type="submit" class="button_600">Main Page</button>
                        </form>
                    </th>
                </tr>
            </table>
        </div>
        <br>
        <div>
            <table class="game_field_table">
                <c:forEach var="tr" begin="0" end="6" step="3">
                    <tr>
                        <c:forEach var="th" begin="1" end="3">
                            <th>
                                <c:choose>
                                    <c:when test="${game.cells[(tr + th).intValue()].equals(game.player1)}">
                                        <img alt="x" src="${pageContext.request.contextPath}/image/x.png"
                                             width="100"
                                             height="100">
                                    </c:when>
                                    <c:when test="${game.cells[(tr + th).intValue()].equals(game.player2)}">
                                        <img alt="x" src="${pageContext.request.contextPath}/image/o.png"
                                             width="100"
                                             height="100">
                                    </c:when>
                                </c:choose>
                            </th>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br><br>
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
        <div>
            <table class="main_page_table">
                <tr>
                    <th class="game_statistic_total">Total games with an Algorithm played</th>
                <tr>
                    <td class="game_statistic_total">${statistic.totalGames}</td>
                </tr>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            <table class="game_field_table">
                <tr>
                    <td class="cell_300"><h1>You</h1></td>
                    <td class="cell_300"><h1>MiniMax</h1></td>
                </tr>
                <tr>
                    <th><img alt="x" src="${pageContext.request.contextPath}/image/x.png" width="70" height="70"></th>
                    <th><img alt="o" src="${pageContext.request.contextPath}/image/o.png" width="70" height="70"></th>
                </tr>
            </table>
        </div>
        <br><br><br>
        <div>
            <table class="game_field_table">
                <c:forEach var="tr" begin="0" end="6" step="3">
                    <tr>
                        <c:forEach var="th" begin="1" end="3">
                            <th>
                                <c:choose>
                                    <c:when test="${game.cells[(tr + th).intValue()].equals(game.player1)}">
                                        <img alt="x" src="${pageContext.request.contextPath}/image/x.png"
                                             width="150"
                                             height="150">
                                    </c:when>
                                    <c:when test="${game.cells[(tr + th).intValue()].equals(game.player2)}">
                                        <img alt="x" src="${pageContext.request.contextPath}/image/o.png"
                                             width="150"
                                             height="150">
                                    </c:when>
                                    <c:otherwise>
                                        <form method="post" action="${pageContext.request.contextPath}/game/algorithm">
                                            <input type="hidden" name="cell" value="${tr + th}">
                                            <button type="submit" class="game_type_button"></button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </th>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>