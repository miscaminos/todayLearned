<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:url var="url1" value="../shopping.do" /> 
<c:url var="url2" value="/shopping.do" > 
  <c:param name="Add" value="isdn-001" /> 
</c:url> 
<c:url var="url3" value="http://localhost:8000/jstl_test/setTag.jsp" /> 
<ul> 
    <li>URL1 = ${url1} 
    <li>URL2 = ${url2} 
    <li>URL3 = ${url3} 
</ul> 