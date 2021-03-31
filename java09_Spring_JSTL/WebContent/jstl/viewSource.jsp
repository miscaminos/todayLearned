<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소스보기</title>
</head>
<body>
<%
FileReader reader = null;
try {
  String path = request.getParameter("path");
  reader = new FileReader(getServletContext().getRealPath(path));
 
%>
<pre>
  소스코드 = <%=path %><br>
<c:out value="<%=reader %>" escapeXml="true" />
     </pre>
 
<%     
}catch(IOException e){ 
out.print("에러:"+e.getMessage());
}finally{
if(reader !=null) reader.close();
}
%> 
</body>
</html>