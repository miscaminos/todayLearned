<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>맛있는 과일 사진</h2>
  <p>이미지 파일을 보여줍니다.</p>            
  <img src="${pageContext.request.contextPath }/images/apple.png" class="img-rounded" alt="Apple" width="304" height="236">
<br>
<br>
<h2>JSTL결과 출력</h2>
<c:set var="fruit" value="매일아침 사과1개씩!"/>
<br>
<h3>JSTL 출력: ${fruit}</h3>
</div>
 
</body>
</html>