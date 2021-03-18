<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="util" uri="/ELFunctions" %>
<%@ include file="/ssi/ssi_bbs.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
   <script type="text/javascript">
     function read(bbsno){
       var url = "read";
       url += "?bbsno="+bbsno;
       url += "&col=${col}";
       url += "&word=${word}";
       url += "&nowPage=${nowPage}";
       location.href=url;
     }
     function fileDown(filename){
    	 var url = "./fileDown"; 
    	 url+= "?filename="+filename;
    	 url+= "&dir=/storage";
    	 location.href=url; //location.href: get방식으로 요청
    	 //MVC기반이기때문에 Controller가 요청을 받는다
     }
  </script>
</head>
<body>
<div class="container">
  <h2>게시판 목록</h2>
  <form class="form-inline" action="./list" method="post">
    <div class="form-group">
      <select class="form-control" name="col">
        <option value="wname"
        <c:if test="${col=='wname' }">selected</c:if>
        >성명</option>
        <option value="title"
        <c:if test="${col=='title' }">selected</c:if>
        >제목</option>
        <option value="content"
       <c:if test="${col=='content' }">selected</c:if>
        >내용</option>
        <option value="title_content"
         <c:if test="${col=='title_content' }">selected</c:if>
        >제목+내용</option>
        <option value="total"
        <c:if test="${col=='total' }">selected</c:if>
        >전체출력</option>
     </select>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" placeholder="Enter 검색어" name="word" value="${word}">
    </div>
    <button type="submit" class="btn btn-default" >검색</button>
    <button type="button" class="btn btn-default" onclick="location.href='./create'">등록</button>
  </form>
  <table class="table table-striped">
   <thead>
    <tr>
    <th>번호</th>
    <th>제목</th>
    <th>작성자</th>
    <th>grpno</th>
    <th>indent</th>
    <th>파일</th>
    </tr>
   </thead>
   <tbody>
 <c:choose>
  <c:when test="${empty list}">
   <tr><td colspan="6">등록된 글이 없습니다.</td>
  </c:when>
  <c:otherwise>
  <c:forEach var="dto" items="${list}">
   <tr>
    <td>${dto.bbsno}</td>
    <td>
      <c:forEach begin="1" end="${dto.indent }">&nbsp;&nbsp;</c:forEach>
      <c:if test="${dto.indent >0 }">
      	<img src='../images/re.jpg' >
      </c:if>
      
    <c:set var="rcount" value="${util:rcount(dto.bbsno, rmapper) }"/>
    
    <a href="javascript:read('${dto.bbsno}')">${dto.title}</a>
    
    <c:if test="${rcount>0}">
    	<span class= "badge">${rcount }</span>
    </c:if>
    
    <c:if test="${util:newImg(fn:substring(dto.wdate,0,10)) }">
    	<img src="../images/new.gif">
    </c:if>
    
    </td>
    <td>${dto.wname}</td>
    <td>${dto.grpno}</td>
    <td>${dto.indent}</td>
    <td>
    <c:choose>
    	<c:when test="${empty dto.filename}">파일없음</c:when>
    	<c:otherwise>
    	<a href="javascript:fileDown('${dto.filename}')">${dto.filename}</a>
    	</c:otherwise>
    </c:choose>
    </td>
   </tr>
   </c:forEach>
 </c:otherwise>
</c:choose>
   </tbody>
  </table>
  <div>${paging}</div>
</div>

</body>
</html>