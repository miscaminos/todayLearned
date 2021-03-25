<%@ page contentType="text/html; charset=UTF-8" %>
 
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
 
</head> 
<body>
 
  <DIV class='title_line'>
    회원 삭제
  </DIV>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list'>목록</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
 
 
  <DIV class='message'>
    <FORM name='frm' method='POST' action='./delete'>
      '${usersVO.name }(${usersVO.id })' 회원을 삭제하면 복구 할 수 없습니다.<br><br>
      정말로 삭제하시겠습니까?<br><br>         
      <input type='hidden' name='usersno' value='${usersVO.usersno}'>     
          
      <button type="submit">삭제</button>
      <button type="button" onclick="location.href='./list'">취소(목록)</button>
   
    </FORM>
  </DIV>
 
</body>
 
</html>