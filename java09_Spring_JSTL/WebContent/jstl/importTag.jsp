<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:import url="url.jsp" var="urlEx" /> 
<c:import url="http://127.0.0.1:9000/jstl_test/jstl/header.jsp" var="head"> 
    <c:param name="id" value="tree" /> 
</c:import>
<c:import url="footer.jsp" var="foot" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>import와 url 태그</title>
</head>
<body>
${head} 

${urlEx} 

${foot} 

</body>
</html>