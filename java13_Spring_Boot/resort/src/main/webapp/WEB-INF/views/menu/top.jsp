<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DIV class='container_main' style='width: 100%;'> 
  <!-- 화면 상단 메뉴 -->
  <DIV class='top_img'>
    <DIV class='top_menu_label'>Resort 0.1 영화와 여행이있는 리조트</DIV>
    <NAV class='top_menu'>
      <span style='padding-left: 0.5%;'></span>
      <A class='menu_link'  href='/' >힐링 리조트</A><span class='top_menu_sep'>&nbsp;</span>
      <A class='menu_link'  href='/categrp/list'>카테고리 그룹</A><span class='top_menu_sep'>&nbsp;</span>    
      <A class='menu_link'  href='/users/list'>회원 목록</A><span class='top_menu_sep'>&nbsp;</span>
      
      <c:choose>
        <c:when test="${sessionScope.id == null}">
          <A class='menu_link'  href='${root}/users/login' >Login</A><span class='top_menu_sep'> </span>
        </c:when>
        <c:otherwise>
          ${sessionScope.id } <A class='menu_link'  href='${root}/users/logout' >Logout</A><span class='top_menu_sep'> </span>
        </c:otherwise>
      </c:choose>      
    </NAV>
  </DIV>
  
  <!-- 화면을 2개로 분할하여 좌측은 메뉴, 우측은 내용으로 구성 -->  
  <DIV class="row" style='margin-top: 2px;'>
    <DIV class="col-sm-3 col-md-2"> <!-- 메뉴 출력 컬럼 -->
      <img src='/menu/images/myimage.png' style='width: 100%;'>
      <div style='margin-top: 5px;'>
        <img src='/menu/images/myface.png'>힐링 리조트
      </div>
      <!-- Spring 출력 카테고리 그룹 / 카테고리 -->
      <%-- <jsp:include page="/cate/list_index_left.do" flush='false' /> // ERROR --%>
      <c:import url="/cate/list_index_left" />  
    </div>
      
    <DIV class="col-sm-9 col-md-10 cont">  <!-- 내용 출력 컬럼 -->  
   
<DIV class='content'>
 
   
   
   