<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <!-- Bootstrap -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <!-- Ajax -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <title>글 작성</title>
  <style>
  h1 {
    padding: 70px 70px;
    text-align: center;
  }
  </style>
  <script>
	$(document).ready(function() {
	
		$("#writeForm").submit(function(event) {
			
			event.preventDefault();
			
			var bName = $("#bName").val();
			var bTitle = $("#bTitle").val();
			var bContent = $("#bContent").val();
			
			var data = {
					bName: bName,
					bTitle: bTitle,
					bContent: bContent
			};
			
			$.ajax({
				type: 'POST',
				url: $(this).attr('action'),
				cache: false,
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data),
				success: function(result) {
					if (result == 'SUCCESS') {
						$(location).attr('href', '${contextPath}/board');
					}
				},
				error: function(e) {
					console.log(e);
				}
			});
		});
	});
  </script>
</head>
<body>
  <div class="container">
    <h1>글 작성</h1>
    <form id="writeForm" action="${contextPath}/board/write">
	  <div class="form-group">
	    <label class="col-xs-2">작성자</label>
	    <input type="text" id="bName" class="form-control">
	  </div>
	  <div>
	    <label class="col-xs-2">제목</label>
	    <input type="text" id="bTitle" class="form-control">
	  </div>
	  <div>	
	    <label class="col-xs-2">내용</label>
	    <textarea name="Description_Loss" rows="10" cols="100" class="form-control" id="bContent"></textarea>
	  </div>
	  <div>
	    <input type="submit" class="btn btn-dark" value="작성"> &nbsp; 
	    <button type="button" class="btn btn-dark" onclick="location.href='${contextPath}/board'">목록</button>
	  </div>
    </form>
  </div>
</body>
</html>