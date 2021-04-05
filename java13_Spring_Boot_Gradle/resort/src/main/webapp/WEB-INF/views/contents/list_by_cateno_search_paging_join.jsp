<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 
</head>
 
<body>

  <DIV class="title_line">
    ${cateVO.name}
  </DIV>

  <ASIDE class="aside_right">
    <A href="./create?cateno=${cateVO.cateno }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid?cateno=${cateVO.cateno }">갤러리형</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
    
  <DIV style="text-align: right;">  
    <form name='frm' id='frm' method='get' action='./list'>
      <input type='hidden' name='cateno' value='${cateVO.cateno }'>
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' 
                     style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list?cateno=${cateVO.cateno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
      
   
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 45%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>파일</th>
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>회원</th>
          <th style='text-align: center;'>IP</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="contents_UsersVO" items="${list }">
          <c:set var="title" value="${contents_UsersVO.title }" />
          <c:set var="contentsno" value="${contents_UsersVO.contentsno }" />
          <c:set var="file1" value="${contents_UsersVO.file1 }" />
          <c:set var="thumb1" value="${contents_UsersVO.thumb1 }" />
          <c:set var="mid" value="${contents_UsersVO.mid }" />
          <c:set var="ip" value="${contents_UsersVO.ip }" />
          <c:set var="rdate" value="${contents_UsersVO.rdate }" />
                    
          <tr> 
            <td style='vertical-align: middle; text-align: center;'>${rdate.substring(0, 10)}</td>
            <td style='vertical-align: middle; text-align: center;'>
              <c:choose>
                <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                  <a href="./read?contentsno=${contentsno}&word=${param.word }&nowPage=${nowPage}"><IMG src="${pageContext.request.contextPath }./storage/main_images/${thumb1 }" style="width: 120px; height: 80px;"></a> 
                </c:when>
                <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                  ${file1}
                </c:otherwise>
              </c:choose>
            </td>  
            <td style='vertical-align: middle;'>
              <a href="./read?contentsno=${contentsno}&word=${param.word }&nowPage=${nowPage}">${title}</a> 
            </td> 
            <td style='vertical-align: middle; text-align: center;'>${mid}</td>
            <td style='vertical-align: middle; text-align: center;'>${ip}</td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <DIV class='bottom_menu'>${paging }</DIV>
  </div>
 

</body>
 
</html>


