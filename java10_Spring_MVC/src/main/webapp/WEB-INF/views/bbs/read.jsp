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
      
      location.href=url; //?앞에까지가 URL이고, 그 다음은 parameter, URL안에 bbsno와 oldfile 넣고 CONTROLLER 호출 (MVC 패턴에서는 항상 요청 들어오면 controller 호출)
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
<div class="panel-body" style="height: 200px">${dto.content}</div>

<div class="panel-heading">파일</div>
<div class="panel-body">
${dto.filename}
</div>

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
</div>

</div>
</body> 
</html> 