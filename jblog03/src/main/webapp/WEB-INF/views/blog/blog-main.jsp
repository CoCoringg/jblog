<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title }</h4>
					<p>
						${fn:replace(post.contents, newLine, "<br/>") }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="post" varStatus='status'>
						<li><a href="${pageContext.request.contextPath}/${blogVo.id }/${categoryIndex }/${postList.size()-(status.index+1) }">${post.title }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="categoryVo" varStatus='status'>
					<c:choose>
						<c:when test="${categoryVo.postCount == 0}">
							<li><a>${categoryVo.name }</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/${blogVo.id }/${status.index }">${categoryVo.name }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>