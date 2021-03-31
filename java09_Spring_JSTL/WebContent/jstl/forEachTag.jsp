<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Map mapData = new HashMap();
	mapData.put("name","Lauv");
	mapData.put("today", new Date());
%>
<c:set var="intArray" value="<%=new int[]{20,30,40,50}%>"/>
<c:set var="map" value="<%=mapData %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>1부터 100까지 홀수의 합</h4>
<c:forEach var="i" begin="1" end="100" step="2">
	<c:set var="sum" value="${sum+i }"/>
</c:forEach>
결과 = ${sum }
<br>

<h4>구구단:4단</h4>
<ul>
<c:forEach var="i" begin="1" end="9">
	<li>4*${i } = ${4*i }</li>
</c:forEach>
</ul>
<br>

<h4> int형 배열</h4>
<c:forEach var="i" items="${intArray }" begin="2" end="4">
	[${i }]
</c:forEach>

<h4>Map</h4>
<c:forEach var="i" items="${map }">
	${i.key }=${i.value }<br>
</c:forEach>
	
</body>
</html>