<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	table{
	  	border: 1px solid black;
	  	height: 600px;
	  	width: 600px;
	}
	
	
	
	body {
		background-color: rgb(255, 156, 119);
	}
	
	h1{
		text-align: center;
	}
	
	.board{
		background-color: rgb(0, 128, 64);
		height: 800px;
		width: 800px;
		margin: 0 auto;
		
		
	}
	.spaces{
		width: 100%;
		height: 100%;
		text-align: center;
	}
	.spaces tr, .spaces td{
		border: 1px solid black;
		padding: 5px;
		width: 80px;
		height: 80px;
	}
	
	.disk{
	border-radius: 50%;
	width: 75px;
	height: 75px;
	
	}
	
	.legalMove{
		
		border-radius: 50%;
		width: 75px;
		height: 75px;
	}
	.formButton{
		opacity: 0.35;
		border-radius: 50%;
		width: 75px;
		height: 75px;
	}
	
	
	
	</style>
  </head>
  	<body>
  		<h1>Reversi</h1>
  		<div class="board">
	  		<table class="spaces">
	  			<tr>
	  				<td>
	  					<img class="disk" src="images/blackDisk.png"/>
	  					
	  				</td>
	  				<td>
	  					<img class="disk" src="images/whiteDisk.png"/>
	  				</td>
	  				<td >
	  					<form action="ReversiServlet" >
 							 	<button type="submit" class="formButton"><img class="legalMove" src="images/whiteDisk.png"/></button>
 						</form>
	  				</td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  			</tr>
	  			<tr>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  			</tr>
	  			<tr>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				
	  			</tr>
	  			<tr>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  			</tr>
	  			<tr>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				
	  			</tr>
	  			<tr>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  			</tr>
	  			<tr>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				
	  			</tr>
	  			<tr>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  				<td></td>
	  			</tr>
	  		</table>
  		</div>

		<%-- 
		<tr>
			<c:forEach items="${ }" var="space" varStarus="stat">
				<td>
					<c:choose>
 						<c:when test="${if its black @ stat.count}"> <img class="disk" src="images/blackDisk.png"/> </c:when>
 						<c:when test="${if its white @ stat.count}"> <img class="disk" src="images/whiteDisk.png"/> </c:when>
 						<c:when test="${if its in legal moves @ stat.count}">
 							<form action="ReversiServlet"> 
 							
 								### We need to find a way to pass in stat.count into the form so we place a disk at the right location.
 								### we can probably use a value tag inside the button to pass to the servlet.
 								
 							 	<button type="submit"><img class="legalMove" src="images/whiteDisk.png"/></button>
 							</form>
 						</c:when>
 						<c:otherwise><td></td></c:otherwise>
 					</c:choose>
 				</td>
 				
 					<c:if test="${stat.count % 8 == 0 }"> </tr> <tr> </c:if>
 					
 			</c:forEach>
		</tr>
		
		--%>
    
  	</body>
</html>
