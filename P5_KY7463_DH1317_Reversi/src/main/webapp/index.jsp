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
							<c:when
								test="${game.occupiedSpaces[stat.index] eq 'BLACK'}">
								<img class="disk" src="images/blackDisk.png" />
							</c:when>
							<c:when
								test="${game.occupiedSpaces[stat.index] eq 'WHITE'}">
								<img class="disk" src="images/whiteDisk.png" />
							</c:when>
							<c:when
								test="${game.legalMoves.contains(stat.index)}">
								<form action="<c:url value='ReversiServlet'/>" class="form" method="post">
									<c:choose>
										<c:when
											test="${game.currentPlayer eq 'BLACK' && sessionScope.helpBlack eq true}">
											<button class="formButton" type="submit" name="loc"
												value="${stat.index}">
												<img class="legalMove" src="images/yellowDisk.png" />
											</button>
										</c:when>
										<c:when
											test="${game.currentPlayer eq 'WHITE' && sessionScope.helpWhite eq true}">
											<button class="formButton" type="submit" name="loc"
												value="${stat.index}">
												<img class="legalMove" src="images/yellowDisk.png" />
											</button>
										</c:when>
										<c:otherwise>
											<button class="emptyFormButton" type="submit" name="loc"
												value="${stat.index}"></button>
										</c:otherwise>
									</c:choose>
								</form>
							</c:when>
							<c:otherwise><c:if test="${sessionScope.helpLocations eq true}">${stat.index}</c:if></c:otherwise>
						</c:choose>
					</td>
						<c:choose>
							<c:when test="${stat.count % 8 == 0 }"></tr></c:when>
						</c:choose>
				</c:forEach>
		</table>
	</div>

	<div>
		<c:choose>
			<c:when test="${game.over == true}">
				<c:choose>
					<c:when test="${game.winner eq 'WHITE'}">
						<h1 class="winner">White Wins</h1>
					</c:when>
					<c:when test="${game.winner eq 'BLACK'}">
						<h1 class="winner">Black Wins</h1>
					</c:when>
					<c:when test="${game.winner == null}">
						<h1 class="winner">Its A Tie</h1>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<h1>Turn</h1>
			</c:otherwise>
		</c:choose>


		<table class="infoTable">
			<c:choose>
				<c:when test="${game.currentPlayer eq 'BLACK'}">
					<tr>
						<td><img class="disk" src="images/blackDisk.png" />
							<h3>Blacks Turn</h3>
							<h4>Score:<c:out value="${game.countBlack}" /></h4>
						</td>
						<td><img class="disk" src="images/whiteDisk.png" />
							<h3>-</h3>
							<h4>Score:<c:out value="${game.countWhite}" /></h4>
						</td>
					</tr>
				</c:when>

				<c:when test="${game.currentPlayer eq 'WHITE'}">
					<tr>
						<td><img class="disk" src="images/blackDisk.png" />
							<h3>-</h3>
							<h4>Score:<c:out value="${game.countBlack}" /></h4>
						</td>

						<td><img class="disk" src="images/whiteDisk.png" />
							<h3>Whites Turn</h3>
							<h4>Score:<c:out value="${game.countWhite}" /></h4>
						</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
	</div>
	<div class="optionsContainer">
		<form action="<c:url value='ReversiServlet'/>" method="post">
			<table class="infoTable">
				<tr>
					<td>
						<button class="optionsButton" type="submit" name="helpBlack"
							value="true">
								<c:choose>
									<c:when test= "${sessionScope.helpBlack eq true}">Turn off Black Hints</c:when>
									<c:otherwise>Turn on Black Hints</c:otherwise>
								</c:choose>
							</button>
					</td>
					<td>
						<button class="optionsButton" type="submit" name="quit">New Game!</button>
					</td>
					<td>
						<button class="optionsButton" type="submit" name="helpLocations" value="true">
							<c:choose>
								<c:when test= "${sessionScope.helpLocations eq true}">Turn off Space Index</c:when>
								<c:otherwise>Turn on Space Index</c:otherwise>
							</c:choose>
						</button>
					</td>
					<td>
						<button class="optionsButton" type="submit" name="helpWhite"
							value="true">
								<c:choose>
									<c:when test= "${sessionScope.helpWhite eq true}">Turn off White Hints</c:when>
									<c:otherwise>Turn on White Hints</c:otherwise>
								</c:choose>
							</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
