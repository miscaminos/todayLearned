<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi_bbs.jsp" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html> 
<html> 
<head>
  <title>글삭제</title>
  <meta charset="utf-8">
  <style type="text/css">
  #red{
    color:red;
  }
  </style>
  <script>
  //ajax 비동기 방식: page loading되면서 JSON data를 읽어온다. 삭제2 버튼을 눌렀을때 :
  	//삭제버튼의 id="btn1"지정필요
  	$(function(){
  		$('#btn1').on('click',function(){
  			var form={
  					bbsno: $('#bbsno').val(),
  					passwd: $('#passwd').val(),
  					filename: $('#oldfile').val()
  					
  					}//이렇게 중괄호에 넣는것이 JSON객체임.
  			alert(form.bbsno)
  			//비동기 통신
  	        $.ajax({
  	            url: "./delete_Ajax",
  	            type: "POST",
  	            //위에선 정의한 JSON data var=form을 객체형 -->문자열(JASON형식을 그대로 반영한)로 변환한다
  	            data: JSON.stringify(form),
  	            contentType: "application/json; charset=utf-8;",
  	            dataType: "json",
  	            //위에서 문자열로 변환한 data를 success를 통해 비동기식 요청한 곳으로 보낸다.
  	            success: function(data){//비유: "형 여기 콜라가져왔어. 계속 하던 청소해"
  	             
  	                $('#red').text(''); //red라는 id를 갖고있는 <p>태그에 뿌려준다.
  	                $('#red').text(data.str);
  	              	//$('#red').css('color',data.color);
  	            },
  	            //만약 제대로된 결과(success)처리가 안되면 
  	            error: function(request,status,error){
  	             alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
  	            }
  	        });
  		})// button event설정
  	}); //페이지 로딩
  </script>
</head>
<body> 
<div class="container">
		<c:choose>
			<c:when test="${flag }">
				<div class='well well-lg'>
					답변이 있는 글임으로 삭제할 수 없습니다.<br>
					<br>
					<button class='btn' onclick='history.back()'>다시시도</button>
					<br>
				</div>
			</c:when>
			<c:otherwise>
				<h1 class="col-sm-offset-2 col-sm-10">삭제</h1>
				<!--  비동기방식 구현을 위해 form 테그안에 action과 같은 동기식 응답요소는 제외한다 -->
<!-- 				<form class="form-horizontal"> -->
					<input type="hidden" name="bbsno" id="bbsno"value="${param.bbsno}"> 
					<input type="hidden" name="oldfile" id="oldfile"
						value="${param.oldfile}">
					<div class="form-group">
						<label class="control-label col-sm-2" for="passwd">비밀번호</label>
						<div class="col-sm-6">
							<input type="password" name="passwd" id="passwd"
								class="form-control">
						</div>
					</div>

					<p id="red" class="col-sm-offset-2 col-sm-6">삭제하면 복구할 수 없습니다</p>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button class="btn" id="btn1">삭제</button>
							<button type="reset" class="btn">취소</button>
						</div>
					</div>
<!-- 				</form> -->
			</c:otherwise>
		</c:choose>
	</div>
</body> 
</html>