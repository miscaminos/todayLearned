<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<hr size="1"> 
<c:if test="${param.id != null}"> 
[HEADER] ${param.id} 님의 방문을 환영합니다. 
<hr size="1"> 
</c:if> 