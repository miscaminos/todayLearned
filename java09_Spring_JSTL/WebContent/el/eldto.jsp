<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jstl_test.el.*" %>
<!-- el can be used to directly access a member variable of a bean or a request object--> 
    <%
    	ELDTO dto = new ELDTO("Daniel Redcliff","Harry Potter");
    	request.setAttribute("dto",dto);
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>
EL 사용하지 않는 경우<br><br>
<%
	Object obj = request.getAttribute("dto");
	ELDTO eldto = (ELDTO)obj;
	out.print("영화명:"+eldto.getMovie()+"<br><br>");
	out.print("주 연:"+eldto.getName()+"<br><br>");
%>
<br>
EL 사용하는 경우<br><br>
영화명: ${requestScope.dto.movie}<br><br>
주 연: ${requestScope.dto.name}<br><br>
배 우(X): ${param.dto.name}<br><br>
dto(X): ${param.dto}<br><br>
Type2: 주 연:${dto.movie}=${dto.name}<br><br>
Type3: 주 연(X): ${requestScope.movie}<br><br> 
Type4: 주 연(X): ${movie}<br><br> 

</h2>
</body>
</html>