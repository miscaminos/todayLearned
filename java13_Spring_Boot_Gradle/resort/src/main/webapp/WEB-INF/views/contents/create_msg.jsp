<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
</head> 
<body>

  <DIV class='title_line'>
    알림
  </DIV>

  <ASIDE class="aside_right">
    <A href="./create?cateno=${param.cateno }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid1?cateno=${param.cateno }">갤러리형</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
   
<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.cnt == 1 }">
          <LI class='li_none'>
            <span class='span_success'>새로운 컨텐츠를 등록했습니다.</span>
          </LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>
            <span class='span_fail'>새로운 컨텐츠 등록에 실패했습니다.</span>
          </LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <c:choose>
          <c:when test="${param.cnt == 1 }">
            <button type='button' 
                         onclick="location.href='./create?cateno=${param.cateno}'"
                         class="btn btn-info">새로운 컨텐츠 등록</button>
          </c:when>
          <c:otherwise>
            <button type='button' 
                         onclick="history.back();"
                         class="btn btn-info">다시 시도</button>
          </c:otherwise>
        </c:choose>
        
        <br>            
        <button type='button' 
                    onclick="location.href='./list_by_cateno?cateno=${param.cateno}'"
                    class="btn btn-info">유형 1: 목록(list_by_cateno)</button><br>
                          
        <button type='button' 
                    onclick="location.href='./list?cateno=${param.cateno}'"
                    class="btn btn-info">유형 2: 목록 + 검색 + 페이징 + 회원 Join(list) 기본 구현 목록</button><br>       
        <button type='button' 
                    onclick="location.href='./list_by_cateno_grid?cateno=${param.cateno}'"
                    class="btn btn-info">유형 3: 목록 + Grid 형태의 이미지 출력(list)</button><br>       
      </LI>
     </UL>
  </fieldset>
 
</DIV>

</body>
 
</html>
   