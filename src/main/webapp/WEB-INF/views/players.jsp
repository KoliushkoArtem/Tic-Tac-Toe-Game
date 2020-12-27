<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<html>
<head>
    <title>Player VS Player</title>
</head>
<body>
<c:choose>
    <c:when test="${game.player1win || game.player2win || game.draw}">
        <c:choose>
            <c:when test="${game.player1win}">
                <h1>Player 1 WIN<br>Congratulations</h1>
            </c:when>
            <c:when test="${game.player2win}">
                <h1>Player 2 WIN<br>Congratulations</h1>
            </c:when>
            <c:when test="${game.draw}">
                <h1>There is no winner<br><br></h1>
            </c:when>
        </c:choose>
        <div>
            <table class="game_field_table">
                <tr>
                    <th>
                        <form method="get" action="${pageContext.request.contextPath}/game/players">
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
                                             width="150"
                                             height="150">
                                    </c:when>
                                    <c:when test="${game.cells[(tr + th).intValue()].equals(game.player2)}">
                                        <img alt="x" src="${pageContext.request.contextPath}/image/o.png"
                                             width="150"
                                             height="150">
                                    </c:when>
                                </c:choose>
                            </th>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            <table class="game_field_table">
                <tr>
                    <td class="cell_300"><h1>Player 1</h1></td>
                    <td class="cell_300"><h1>Player 2</h1></td>
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
                                        <form method="post" action="${pageContext.request.contextPath}/game/players">
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