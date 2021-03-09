<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html> 
<head><title>시간대 목록</title></head> 
<body> 
<!-- lists all the avilable time zone standards -->
<% 
    String[] ids = java.util.TimeZone.getAvailableIDs(); 
    for (int i = 0 ; i < ids.length ; i++) { 
        out.println(ids[i]+"<br/>"); 
    } 
%> 

</body> 
</html> 