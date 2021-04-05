<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 글 + 이미지 조회하는 경우 참고  --%>

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
<c:set var="cateno" value="${cateVO.cateno}" />
<c:set var="contentsno" value="${contentsVO.contentsno }" />


  <DIV class="title_line">
    ${contentsVO.title}
  </DIV>

  <ASIDE class="aside_right">
    <A href="./create?cateno=${cateno }">글 등록</A>
    <c:choose>
      <c:when test="${contentsVO.file1.trim().length() > 0 }">
        <span class='menu_divide' > | </span> 
        <A href='./img_update?cateno=${cateno }&contentsno=${contentsno}'>이미지 변경/삭제</A>     
      </c:when>
      <c:otherwise>
        <span class='menu_divide' > | </span> 
        <A href='./img_create?cateno=${cateno }&contentsno=${contentsno}'>이미지 등록</A>     
      </c:otherwise>
    </c:choose>    
    <span class='menu_divide' > | </span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' > | </span> 
    <A href='./update?cateno=${cateno }&contentsno=${contentsno}'>수정</A>
    <span class='menu_divide' > | </span> 
    <A href='./delete?cateno=${cateno }&contentsno=${contentsno}'>삭제</A>
    <span class='menu_divide' >│</span>
    <A href="./list?cateno=${cateVO.cateno }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_cateno_grid?cateno=${cateVO.cateno }">갤러리형</A>
    
  </ASIDE> 
  
  <div class='menu_line'></div>

  <FORM name='frm' method="get" action='./update'>
      <input type="hidden" name="contentsno" value="${contentsno}">
      <fieldset class="fieldset">
        <ul>
          <li class="li_none" style='border-bottom: solid 1px #AAAAAA;'>
            <span>${contentsVO.title}</span>
            (<span>${contentsVO.rdate.substring(0, 16)}</span>)
          </li>
          <li class="li_none">
            <c:set var="file1" value="${contentsVO.file1.toLowerCase() }" />
            <c:if test="${file1.endsWith('jpg') || file1.endsWith('png') || file1.endsWith('gif')}">
              <DIV style="width: 50%; float: left; margin-right: 10px;">
                <IMG src="/contents/storage/main_images/${contentsVO.file1 }" style="width: 100%;">
              </DIV> 
            </c:if> 
            <DIV>${contentsVO.content }</DIV>
          </li>
          <li class="li_none">
            <DIV style='text-decoration: none;'>
              검색어(키워드): ${contentsVO.word } IP: ${contentsVO.ip }
            </DIV>
          </li>
          <li class="li_none">
            <DIV>
              <c:if test="${contentsVO.file1.trim().length() > 0 }">
                첨부 파일: <A href='/contents/fileDown?dir=/contents/storage/main_images&filename=${contentsVO.file1}&downname=${contentsVO.file1}'>${contentsVO.file1}</A> (${contentsVO.size1_label})  
              </c:if>
            </DIV>
          </li>   
        </ul>
      </fieldset>
  </FORM>
  <br>
  
  <div class='row'> <!-- 댓글 목록 시작 -->
 <div class="col-lg-12">
 <!-- panel start-->
<div class="panel panel-default">
 
<div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> 댓글
        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New
                Reply</button>
</div>
 <div class="panel-body">
         <ul class="chat list-group">
                <li class="left clearfix" data-rno="12">
                        <div>
                                <div class="header">
                                        <strong class="primary-font">user1</strong> <small
                                                class="pull-right text-muted">2019-05-12</small>
                                </div>
                                <p>Good job!</p>
 
                        </div>
                </li>
        </ul>
        <!-- ul end  -->
</div>
<div class="panel-footer"></div>
</div><!-- panel end-->
</div><!--  col-lg-12 end -->
</div><!-- row end -->
</div><!-- container end -->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal"
        aria-hidden="true">&times;</button>
      <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
    </div>
    <div class="modal-body">
      <div class="form-group">
        <label>내용</label> 
        <textarea cols="10" rows="3" class="form-control" name='content'>New Reply!!!!</textarea> 
      </div>      
    </div>
<div class="modal-footer">
<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
</div>          </div>
  <!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- 댓글처리,서버와 비동기 통신 -->
<script type="text/javascript" 
        src="${pageContext.request.contextPath}/js/breply.js"></script>
<!-- 페이지 로딩시 댓글 목록 처리-->
 <!-- jstl내부 javascript에서 사용가능 -->
  <script type="text/javascript">
  var contentsno = "${contentsVO.contentsno}"; 
  var sno = "${sno}";
  var eno = "${eno}";
 <!-- 댓글용 paging, 게시판 검색 -->
  var nPage = "${nPage}";
  var nowPage = "${param.nowPage}";
  var colx = "${param.col}";
  var wordx = "${param.word}";
  <!-- 세션값 아용-->
 var session_id = '${sessionScope.id}';
  </script>
 <!-- 비동기통신 요청후 전달되는 데이터를 화면에 출력게 하는 코드 --> 
 <script type="text/javascript" 
         src="${pageContext.request.contextPath}/js/replyprocess.js"></script>

</body>

</html>

