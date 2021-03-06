<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
			<c:import url="/WEB-INF/views/includes/admin-header.jsp"></c:import>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
				<form:form
					modelAttribute="postVo"
					action="${pageContext.request.contextPath}/${authUser.id }/admin/write" 
					method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<form:input type="text" size="60" path="title" />
				      			<select name="category">
				      				<c:forEach items="${categorySelect }" var="categoryVo">
				      					<option value="${categoryVo.no }">${categoryVo.name }</option>
				      				</c:forEach>
				      			</select>
				      			<p style="color:red;"><form:errors path="title" /></p>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td>
			      				<form:textarea path="contents" />
			      				<p style="color:red;"><form:errors path="contents" /></p>
			      			</td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form:form>
			</div>
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>