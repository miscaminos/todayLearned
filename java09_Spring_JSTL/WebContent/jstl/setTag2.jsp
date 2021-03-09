<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="map" value="<%=new java.util.HashMap() %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set target="${map}" property="favorite1" value="LAUV"/>
<c:set target="${map}" property="favorite2" value="ANNE MARIE"/>
<c:set target="${map}" property="favorite3" value="ED SHEERAN"/>
<!-- Hashmap의 key가 property, value가 value -->
<h2>
변수 map에 저장된 favorite1 값: ${ map.favorite1 }<br>
변수 map에 저장된 favorite2 값: ${ map.favorite2 }<br>
변수 map에 저장된 favorite3 값: ${ map.favorite3 }<br>
</h2>
</body>
</html>