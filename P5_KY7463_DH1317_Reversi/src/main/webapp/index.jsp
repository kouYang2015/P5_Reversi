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
<link href="styles.css" rel="stylesheet">

<title>Reversi</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

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
