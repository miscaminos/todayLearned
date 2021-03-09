<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl.jar, standard.jar에서 가져다가 쓰는것임. -->
<c:set var="num1" value="${20 }"/>
<c:set var="num2"> 10.5 </c:set>
<!-- 위와같이 두가지 방법이 있음 동일함. -->
<c:set var="today" name="today" value="<%=new java.util.Date() %>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
변수 num1 = ${num1} <br> 
변수 num2 = ${num2} <br> 
num1 + num2 = ${num1 + num2} <br> 
오늘은 ${today} 입니다. 
<c:remove var="num1" scope="page"/>
<!-- scope? 삭제할때에는 삭제하기전 어디까지 사용할지 지정한다. -->
<!-- scope=request인 경우 forward되어 request객체 소멸될때까지 사용가능, 그 후 삭제 -->
<button id="b2" onclick="location.assign('nextPage.jsp')">next</button>
</body>
</html>