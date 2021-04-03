<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
 
<script type="text/javascript">
  $(function(){
 
  });
</script>
</head> 
 
<body>
 
  <DIV class='title_line'>
    회원
  </DIV>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list'>목록</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
  
 
  <table class="table table-striped" style='width: 100%;'>
  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 30%;'/>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>ID</TH>
    <TH class='th'>성명</TH>
    <TH class='th'>전화번호</TH>
    <TH class='th'>주소</TH>
    <TH class='th'>등록일</TH>
    <TH class='th'>기타</TH>
  </TR>
 
  <c:forEach var="VO" items="${list }">
    <c:set var="usersno" value ="${VO.usersno}" /> 
  <TR>
    <TD class='td'>${usersno}</TD>
    <TD class='td'><A href="./read?usersno=${usersno}">${VO.id}</A></TD>
    <TD class='td'><A href="./read?usersno=${usersno}">${VO.name}</A></TD>
    <TD class='td'>${VO.tel}</TD>
    <TD class='td'>
      <c:choose>
        <c:when test="${VO.address1.length() > 15 }"> <!-- 긴 주소 처리 -->
          ${VO.address1.substring(0, 15) }...
        </c:when>
        <c:otherwise>
          ${VO.address1}
        </c:otherwise>
      </c:choose>
    </TD>
    <TD class='td'>${VO.rdate.substring(0, 10)}</TD> <!-- 년월일 -->
    <TD class='td'>
      <A href="./passwd_update?usersno=${usersno}"><IMG src='./images/passwd.png' title='패스워드 변경'></A>
      <A href="./read?usersno=${usersno}"><IMG src='./images/update.png' title='수정'></A>
      <A href="./delete?usersno=${usersno}"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
    
  </TR>
  </c:forEach>
  
</TABLE>
 
<DIV class='bottom_menu'>
  <button type='button' onclick="location.href='./create'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
 
</body>
 
</html>

