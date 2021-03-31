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
				<form class="form-horizontal" action="delete" method="post">
					<input type="hidden" name="bbsno"
						value="${param.bbsno}"> <input
						type="hidden" name="oldfile"
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
							<button class="btn">삭제</button>
							<button class='btn' type="reset" onclick='history.back()'>취소</button>
						</div>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</body> 
</html>