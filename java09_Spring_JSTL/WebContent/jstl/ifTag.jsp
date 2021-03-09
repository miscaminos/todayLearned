<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="true">무조건 수행<br></c:if>
<!-- browser에 "http://localhost:9000/jstl_test/jstl/ifTag.jsp?name=tree" 주소입력 -->
<c:if test="${param.name == 'tree' }">
name 파라미터 값이 ${param.name }입니다.</c:if>
<!-- brwoser에 "http://localhost:9000/jstl_test/jstl/ifTag.jsp?name=tree&age=20" 입력.(age는 >18입력) -->
<c:if test="${18<param.age }">
당신의 나이는 18세 이상입니다.
</c:if>

</body>
</html>