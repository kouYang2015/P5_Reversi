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
		min-width: 80px;
	}
	
	
	
	</style>
  </head>
  	<body>
  		<h1>Reversi</h1>
  		<div class="board">
	  		<table class="spaces">
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
  		
  	
    
  	</body>
</html>
