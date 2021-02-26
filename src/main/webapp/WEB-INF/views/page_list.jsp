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
  <script  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script  src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <title>글 목록</title>
  <style>
  h1 {
    padding: 70px 70px;
    text-align: center;
  }
  </style>
</head>
<body>
  <h1>글 목록</h1>
  <div class="container">
    <table class="table">
    <thead class="thead-dark">
      <tr>
	    <th scope="col">글 번호</th>
	      <th scope="col">작성자</th>
	      <th scope="col">글 제목</th>
	      <th scope="col">작성 일자</th>
	      <th scope="col">조회수</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${list}" var="list">
	    <tr>
	      <th scope="row">${list.bId}</th>
	      <td>${list.bName}</td>
	      <td><c:forEach begin="1" end="${list.bIndent}">[Re]</c:forEach><a href="${contextPath}/board/${list.bId}">${list.bTitle}</a></td>
	      <td>${list.bDate}</td>
	      <td>${list.bHit}</td>
	    </tr>
	  </c:forEach>
	  </tbody>
	  <tr>
	    <td><button type="button" class="btn btn-dark" onclick="location.href='${contextPath}/board/write'">글 작성</button></td>
	  </tr>
    </table>
    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-center">
        <c:if test="${pageMaker.prev}">
        <li class="page-item"><a class="page-link" href="board${pageMaker.makeQuery(pageMaker.startPage - 1)}">Previous</a></li>
        </c:if>
        <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
        <li class="page-item"><a class="page-link" href="board${pageMaker.makeQuery(idx)}">${idx}</a></li>
        </c:forEach>
        <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
        <li class="page-item"><a class="page-link" href="board${pageMaker.makeQuery(pageMaker.endPage + 1)}">Next</a></li>
        </c:if>
      </ul>
    </nav>
  </div>
</body>
</html>