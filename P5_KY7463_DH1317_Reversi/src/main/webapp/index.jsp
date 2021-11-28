<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">


<title>Reversi</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style>
table {
	height: 600px;
	width: 600px;
}

body {
	background-color: rgb(255, 156, 119);
}

h1 {
	text-align: center;
}

.board {
	background-color: rgb(0, 128, 64);
	min-height: 820px;
	max-height: 820px;
	width: 800px;
	margin: 0 auto;
}

.spaces {
	width: 800px;
	height: 725px;
	max-height: 725px;
	text-align: center;
	align-items: center;
}

.spaces tr, .spaces td {
	border: 1px solid black;
	padding: 5px;
	max-width: 80px;
	max-height: 80px;
	
	
}

.disk {
	border-radius: 50%;
	width: 75px;
	height: 75px;
}

.legalMove {
	border-radius: 50%;
	width: 70px;
	height: 70px;
}

.form{
	align-items: center;
}
.formButton {
	opacity: 0.35;
	border-radius: 55%;
	width: 70px;
	height: 70px;
	top: 50%;
	
}

.player{
	text-align: center;
	margin: auto;
	width: 50%;
}

.playerTable, tr, td{
	margin: auto;
	width: 300px;
	text-align: center;
	height: 100px;

}
.newGame{
	background-color: rgb(0, 128, 64); 
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    

}
.newGameContainer{
	margin: auto;
	text-align: center;
	padding: 20px;
}

.winner{
	text-shadow: 0 0 3px #FF0000, 0 0 5px #0000FF;
}
</style>
</head>
<body>
	<h1>Reversi</h1>
	<div class="board">
		<table class="spaces">
			<tr>
				<c:forEach begin="0" end="63" var="space" varStatus="stat">
					<td><c:choose>
							<c:when test="${game.occupiedSpaces[(stat.index).intValue()] eq 'BLACK'}"> 
							<img class="disk" src="images/blackDisk.png" />
							</c:when>
							<c:when test="${game.occupiedSpaces[(stat.index).intValue()] eq 'WHITE'}">
								<img class="disk" src="images/whiteDisk.png" />
							</c:when>
							<c:when test="${game.findLegalMove().contains((stat.index).intValue())}">
								<form class="form" action="ReversiServlet">
									<button class="formButton" type="submit" name="loc" value="${stat.index}">
										<img class="legalMove" src="images/yellowDisk.png" />
									</button>
								</form>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose></td>
						
					<c:choose>
						<c:when test="${stat.count == 64 }"></c:when>
						<c:when test="${stat.count % 8 == 0 }"> </tr> <tr> </c:when>
					</c:choose>	
				</c:forEach>
			</tr>
		</table>
	</div>
	<div>

	
	
	
	<c:choose>
		<c:when test="${game.isOver() == true}"> 
			<c:choose>
				<c:when test="${game.getWinner() eq 'WHITE'}"><h1 class="winner">White Wins</h1> </c:when>
				<c:when test="${game.getWinner() eq 'BLACK'}"><h1 class="winner">Black Wins </h1> </c:when>
				<c:when test="${game.getWinner() == null}"><h1 class="winner">Its A Tie</h1> </c:when>
			</c:choose>
		</c:when>
		<c:otherwise><h1>Turn</h1></c:otherwise>
	</c:choose>

		
		<table class="playerTable">
				<c:choose>
					<c:when test="${game.getCurrentPlayer() eq 'BLACK' }">
						<tr>
							<td>
								<img class="disk" src="images/blackDisk.png" />
								<h3>Blacks Turn</h3>
								<h4>Score: <c:out value="${game.countBlack()}"/></h4>
							</td>
							
							<td>
								<img class="disk" src="images/whiteDisk.png" />
								<h3>-</h3>
								<h4>Score: <c:out value="${game.countWhite()}"/></h4>
							</td>
						</tr>
					</c:when>
					
					<c:when test="${game.getCurrentPlayer() eq 'WHITE' }">
						<tr>
							<td>
								<img class="disk" src="images/blackDisk.png" />
								<h3>-</h3>
								<h4>Score: <c:out value="${game.countBlack()}"/></h4>
							</td>
							
							<td>
								<img class="disk" src="images/whiteDisk.png" />
								<h3>Whites Turn</h3>
								<h4>Score: <c:out value="${game.countWhite()}"/></h4>
							</td>
						</tr>
					
					</c:when>
				</c:choose>
		</table>
	</div>
	<div class="newGameContainer">
		<form  action="ReversiServlet">
			<button class="newGame" type="submit" name="quit">New Game!</button>
		</form>
	</div>
	

</body>
</html>
