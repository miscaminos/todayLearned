<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:redirect url="../index.jsp">
	<c:param name="name" value="잔나비"/>
</c:redirect>
<!-- servlet에서 사용한 send redirect와 동일함. -->
<!-- 이방식으로 페이지 이동 시, parameter를 가지고가려면 url에 ?로 지정해서 redirect하면됨 -->

<!-- redirectTag.jsp 이파일을 Run on Server 실행을 하면, index.jsp로 redirect한다.-->
<!-- 즉, http://localhost:9000/jstl_test/index.jsp?name=잔나비 이 주소로 바로 redirect된다.-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>