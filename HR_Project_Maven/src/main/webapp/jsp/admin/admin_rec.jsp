<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITTT</title>
	<meta name="description" content="it, Information Technology, Telecommunication"/>
	<meta name="keywords" content="it, vacancies, job"/>
<!--  	<link rel="stylesheet" type="text/css" href="../css/style.css"/> -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/icon.png" type="image/png"/>
</head> 
<body>

	<jsp:include page="/jsp/fragment/header.jsp"></jsp:include>

	<div class="main_content">

		<div class="content">
			<p class=caption>Hello admin <p>
			<br>
			<h2><rb:lang message="rec_caption" locale="${locale}" /> </h2>
			<table class="table" width=100%>
			<tr>
				<td align="right"><b><rb:lang message="rec_name" locale="${locale}" />:</b> </td>
				<td>${recruiter.name }</td>
			</tr>
			<tr>	
				<td align="right"><b><rb:lang message="rec_phone" locale="${locale}" />:</b></td>
				<td> ${recruiter.phone }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="rec_email" locale="${locale}" />:</b> </td> 
				<td>${recruiter.email }</td>
			</tr>
			</table>
			<br>
			<c:if test="${could_be_deleted}">
				<form action="ControllerServlet" method="post">
		 		<input type="hidden" name="action" value="deleteRecruiter" >
		 		<input type="hidden" name="rec_id" value="${recruiter.id}">
		 		<input type="submit" class="btn" value="<rb:lang message="delete" locale="${locale}" />"/>
		 	</form>
			</c:if>
			<c:if test="${could_be_restored}">
				<form action="ControllerServlet" method="post">
		 		<input type="hidden" name="action" value="activateRecruiter" >
		 		<input type="hidden" name="rec_id" value="${recruiter.id}">
		 		<input type="submit" class="btn" value="<rb:lang message="restore" locale="${locale}" />"/>
		 	</form>
			</c:if>
			<br>
		 	
		 	<form action="ControllerServlet" method="post">
		 		<input type="hidden" name="action" value="goFirstPage" >
		 		<input type="hidden" name="user_role" value="${user.role}">
		 		<input type="submit" class="btn" value="<rb:lang message="go_back" locale="${locale}" />"/>
		 	</form>
		
		</div>	

		<div class="side_bar">
			
			<h2><rb:lang message="assigned_interview" locale="${locale}" />:</h2>
			<c:choose>
				<c:when test="${are_interviews}">
					<div class="error">
						<h2><rb:lang message="no_interview" locale="${locale}" /></h2>
					</div>
				</c:when>
				<c:otherwise>
					<ul>
						<c:forEach var="interview" items="${interviews}">
						<li><c:out value="${interview.date}"></c:out></li>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
			<br>
			<h2><rb:lang message="caption" locale="${locale}" /></h2>
			<c:choose>
				<c:when test="${are_vacancies}">
					<div class="error">
						<h2><rb:lang message="no_vac" locale="${locale}" /></h2>
					</div>
				</c:when>
				<c:otherwise>
					<ul>
					<c:forEach var="vac" items="${vacancies}">
					<li><a href="${base_url}?action=showVacancyAdmin&amp;vac_id=${vac.id}&amp;rec_id=${recruiter.id}">
						<c:out value="${vac.name} ${vac.date}"></c:out>
					</a></li>
					</c:forEach>
				</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>

</body>
</html>