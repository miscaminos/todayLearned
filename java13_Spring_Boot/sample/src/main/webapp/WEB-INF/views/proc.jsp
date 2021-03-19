<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>proc.jsp</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body> 
 
<div class="container">
  <h2>메뉴명</h2>
  <div class="panel panel-default">
    <div class="panel-body">${param.menu }</div>
  </div>
  <h2>가격</h2>
  <div class="panel panel-default">
    <div class="panel-body">${param.price }</div>
  </div>
  <h2>수량</h2>
  <div class="panel panel-default">
    <div class="panel-body">${param.count }</div>
  </div>
  <h2>결제</h2>
  <div class="panel panel-default">
    <div class="panel-body">${payment }</div>
  </div>
</div>
</body>
</html>