<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>메뉴 form</h2>
  <form class="form-horizontal" method="post" action="./calc">
    <div class="form-group">
      <label class="control-label col-sm-2" for="menu">메뉴명:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="menu" value="김밥" name="menu" autofocus="autofocus">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="price">가격:</label>
      <div class="col-sm-10">          
        <input type="number" class="form-control" id="price" value="3000" name="price">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="count">수량:</label>
      <div class="col-sm-10">          
        <input type="number" class="form-control" id="count" value="2" name="count">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">처리</button>
      </div>
    </div>
  </form>
</div>
</body>
</html>