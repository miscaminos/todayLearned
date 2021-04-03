<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
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
  <DIV class="title_line">
    『${contentsVO.title}』 메인 이미지 변경 및 삭제
  </DIV>
  
  <ASIDE class="aside_right">
    <A href="./create?cateno=${cateVO.cateno }">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list?cateno=${cateVO.cateno }">기본 목록형</A>        
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid?cateno=${cateVO.cateno }">갤러리형</A>
  </ASIDE> 
 
  <div class='menu_line'></div>
  
  <DIV style='width: 100%;'>
    <H4>새로운 메인 이미지 등록</H4>
    <%-- 이미지가 존재하는 경우 새로운 이미지 등록 폼 출력 --%>
    <FORM name='frm' method='POST' action='./img_update' class="form-horizontal"
                enctype="multipart/form-data">
               
      <!-- FK memberno 지정 -->
      <input type='hidden' name='memberno' id='memberno' value='1'>
      <!-- FK categrpno 지정 -->
      <input type='hidden' name='cateno' id='cateno' value="${param.cateno }">
      
      <input type='hidden' name='contentsno' id='contentsno' value="${param.contentsno }">
      <input type='hidden' name='nowPage' id='nowPage' value="${param.nowPage }">
      
      <div class="form-group">   
        <div class="col-md-12">
          <%-- 실제 컬럼명: file1, Spring File 객체 대응: file1MF --%>
          <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
        </div>
      </div>
      
      <div class="form-group">   
        <div class="col-md-12">
          <input type='password' class="form-control" name='passwd'  value='1234' placeholder="패스워드" style='width: 20%;'>
        </div>
      </div>
      
      <button type="submit" class="btn btn-info">새로운 메인 이미지 등록</button>
      <button type="button" 
                  onclick="location.href='./read?contentsno=${param.contentsno}'" 
                  class="btn btn-info">취소[조회]</button>
    </FORM>
    <HR>
    <H4>메인 이미지 삭제</H4>

    <%-- 이미지가 존재하는 경우 이미지를 출력하고 삭제 버튼 표시 --%>
    <c:set var="file1" value="${contentsVO.file1 }" />
    <c:if test="${file1.endsWith('jpg') || file1.endsWith('png') || file1.endsWith('gif')}">
      <FORM name='frm' method='POST' action='./img_delete' class="form-horizontal">
        <!-- FK memberno 지정 -->
        <input type='hidden' name='memberno' id='memberno' value='1'>
        <!-- FK categrpno 지정 -->
        <input type='hidden' name='cateno' id='cateno' value="${param.cateno }">
        <input type='hidden' name='contentsno' id='contentsno' value="${param.contentsno }">
        <input type='hidden' name='nowPage' id='nowPage' value="${param.nowPage }">

        <IMG src="/contents/storage/main_images/${file1 }" style="width: 50%; margin-bottom: 10px;">
        
        <div class="form-group">   
          <div class="col-md-12">
            <input type='password' class="form-control" name='passwd'  value='1234' placeholder="패스워드" style='width: 20%;'>
          </div>
        </div>
        
        <button type="submit" class="btn btn-info">메인 이미지 삭제</button>
        <button type="button" 
                    onclick="location.href='./read?contentsno=${param.contentsno}'" 
                    class="btn btn-info">취소[조회]</button>
      </FORM> 
    </c:if> 
    

  </DIV>
  <br>
  

</body>
 
</html>
 
  