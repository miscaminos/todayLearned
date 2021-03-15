<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi_bbs.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">

<script type="text/javascript">
    function updateM(){
      var url = "update";
      url += "?bbsno=${dto.bbsno}";
      url += "&oldfile=${dto.filename}";
      
      location.href=url;
    }
    function deleteM(){
      var url = "delete";
      url += "?bbsno=${dto.bbsno}";
      url += "&oldfile=${dto.filename}";
      //?앞에까지가 URL이고, 그 다음은 parameter, URL안에 bbsno와 oldfile 넣고 CONTROLLER 호출 (MVC 패턴에서는 항상 요청 들어오면 controller 호출)
      location.href=url; 
    }
    function delete_Ajax(){
        var url = "delete_Ajax";
        url += "?bbsno=${dto.bbsno}";
        url += "&oldfile=${dto.filename}";
        
        location.href=url;
    }
    function replyM(){
        var url = "reply";
        url += "?bbsno=${dto.bbsno}";
        
        location.href=url;
      }
    
    function listM(){
        var url = "list";
        //request.getParam으로 nowpage를 받았기때문에 표기방식
        url += "?nowPage=${param.nowPage}";
        url += "&col=${param.col}";
        url += "&word=${param.word}";
        location.href=url;
      }
  </script>

</head>
<body>
	<div class="container">

		<h2>조회</h2>
		<div class="panel panel-default">
			<div class="panel-heading">작성자</div>
			<div class="panel-body">${dto.wname}</div>

			<div class="panel-heading">제목</div>
			<div class="panel-body">${dto.title}</div>

			<div class="panel-heading">내용</div>
			<div class="panel-body">${dto.content}</div>

			<div class="panel-heading">파일</div>
			<div class="panel-body">${dto.filename}</div>

			<div class="panel-heading">조회수</div>
			<div class="panel-body">${dto.viewcnt}</div>

			<div class="panel-heading">등록일</div>
			<div class="panel-body">${dto.wdate}</div>
		</div>

		<div>
			<button type="button" class="btn" onclick="location.href='./create'">등록</button>
			<button type="button" class="btn" onclick="updateM()">수정</button>
			<button type="button" class="btn" onclick="deleteM()">삭제</button>
			<button type="button" class="btn" onclick="replyM()">답변</button>
			<button type="button" class="btn" onclick="listM()">목록</button>
			<button type="button" class="btn" onclick="delete_Ajax()">삭제(ajax)</button>
		</div>
		<br>
		<!-- 댓글목록 using Ajax and RESTful -->
		<div class='row'><!-- 댓글 목록 시작. 'row'나 "row"나 single, double quotation 모두 가능. -->
			<div class="col-lg-12">
				<!-- panel start-->
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> 댓글
						<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
					</div>
					<div class="panel-body">
						<ul class="chat list-group">
						<!--  chat, list-group 2가지 클래스가 선언된것임. 띄어쓰기로 나뉨. -->
							<li class="left clearfix" data-rno="12">
								<div>
									<div class="header">
										<strong class="primary-font">user1</strong> <small
											class="pull-right text-muted">2019-05-12</small>
									</div>
									<p>Good job!</p>
								</div>
							</li>
						</ul><!-- ul end  -->
					</div>
					<div class="panel-footer"><!-- 댓글 paging 역할 --></div>
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

<!-- 댓글처리 관련 js 파일 추가 -->

<!-- ajax 요청 부분 -->
<!-- 비동기 통신(Ajax)은 javascript로 처리한다: breply.js -->	
<script type="text/javascript" 
src="${pageContext.request.contextPath}/js/breply.js"></script>
<!--참고: ${pageContext.request.contextPath}까지가 webapp 폴더 경로이다.-->

<!-- 페이지 로딩시 댓글 목록 처리-->
 <!-- jstl내부 javascript에서 사용가능. el표현방식{bbsno}로 출력한것을 var bbsno에 넣는다. -->
  <script type="text/javascript">
  var bbsno = "${dto.bbsno}";
  var sno = "${sno}";
  var eno = "${eno}";
 <!-- 댓글용 paging, 게시판 검색 -->
  var nPage = "${nPage}";
  var nowPage = "${param.nowPage}";
  var colx = "${param.col}";
  var wordx = "${param.word}";
  </script>
  
<!-- 비동기 통신 후, 받은 data를 가지고 화면에 출력되게 하는 js 코드: replyprocess.js -->
<script type="text/javascript" 
src="${pageContext.request.contextPath}/js/replyprocess.js"></script>
	
</body>
</html>
