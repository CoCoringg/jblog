<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${blogVo.title }</h1>
	<ul>
		<c:choose>
			<c:when test="${not empty authUser && id == authUser.id}">
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a
					href="${pageContext.request.contextPath}/${id }/admin/basic">블로그
						관리</a></li>
			</c:when>
			<c:when test="${not empty authUser }">
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:otherwise>
		</c:choose>


	</ul>
</body>
</html>