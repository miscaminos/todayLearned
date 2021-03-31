<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
    request.setAttribute("name","Java 개발자");   
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>
	request의 name 속성(스크립틀릿 방식: 출력): <%=request.getAttribute("name") %><br>
	request의 name 속성(스크립틀릿 방식: assign & print): 
	<% String name = (String)request.getAttribute("name"); 
		out.print(name);
	%>
	<br>
	request의 name 속성(el방식: requestScope.name):${requestScope.name}<br>
	request의 name 속성(el방식: name):${name}
</h2>
</body>
</html>