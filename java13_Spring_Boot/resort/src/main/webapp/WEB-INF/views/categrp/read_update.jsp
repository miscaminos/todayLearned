<%@ page contentType="text/html; charset=UTF-8" %>
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
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
 
  <DIV class='title_line'>카테고리 그룹 > ${categrpVO.name } 조회(수정)</DIV>
 
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_update' id='frm_update' method='POST' action='./update'>
      <input type='hidden' name='categrpno' id='categrpno' value='${categrpVO.categrpno }'>
        
      <label>그룹 이름</label>
      <input type='text' name='name' value="${categrpVO.name }" required="required" style='width: 25%;'>
 
      <label>순서</label>
      <input type='number' name='seqno' value="${categrpVO.seqno }" required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
      <label>형식</label>
      <select name='visible'>
        <option value='Y' ${categrpVO.visible == 'Y' ? "selected='selected'":"" }>Y</option>
        <option value='N' ${categrpVO.visible == 'N' ? "selected='selected'":""}>N</option>
      </select>
       
      <button type="submit" id='submit'>저장</button>
      <button type="button" onclick="location.href='./list'">취소</button>
    </FORM>
  </DIV>
 
  
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 40%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>    
    <col style='width: 20%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class="th_bs">순서</TH>
    <TH class="th_bs">대분류명</TH>
    <TH class="th_bs">등록일</TH>
    <TH class="th_bs">출력</TH>
    <TH class="th_bs">기타</TH>
  </TR>
  </thead>
  
  <tbody>
  <c:forEach var="categrpVO" items="${list}">
    <c:set var="categrpno" value="${categrpVO.categrpno }" />
    <TR>
      <TD class="td_bs">${categrpVO.seqno }</TD>
      <TD class="td_bs_left">${categrpVO.name }</TD>
      <TD class="td_bs">${categrpVO.rdate}</TD>
      <TD class="td_bs">${categrpVO.visible }</TD>
      <TD class="td_bs">
        <A href="./read_update?categrpno=${categrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
        <A href="./read_delete?categrpno=${categrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
        <A href="./update_seqno_up?categrpno=${categrpno }" title="우선순위 상향"><span class="glyphicon glyphicon-arrow-up"></span></A>
        <A href="./update_seqno_down?categrpno=${categrpno }" title="우선순위 하향"><span class="glyphicon glyphicon-arrow-down"></span></A>         
       </TD>   

    </TR>   
  </c:forEach> 
  </tbody>
 
</TABLE>
 
</body>
 
</html> 
 
 
 