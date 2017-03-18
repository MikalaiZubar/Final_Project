<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITTT registration</title>
	<meta name="description" content="it, Information Technology, Telecommunication"/>
	<meta name="keywords" content="it, vacancies, job"/>
	<link rel="stylesheet" type="text/css" href="../css/style.css"/> 
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/icon.png" type="image/png"/>
</head> 
<body>

	<jsp:include page="/jsp/fragment/header.jsp"></jsp:include>

	<div class="main_content">

		<div class="content">
			<div>
			<br>
			<h2><rb:lang message="caption" locale="${locale}" /> ${recruiter.name} </h2>
			<br>
			<c:if test="${is_empty_list == false}" >
				<div class="error">
				<h2><rb:lang message="no_vac_found" locale="${locale}" /></h2>
				</div>
			</c:if>
			<c:if test="${is_empty_list == true}">
			<table class="table" width="100%">
			<tr align="center" width=100%>
				<td><b>ID</b></td>
				<td><b><rb:lang message="vac_name" locale="${locale}" /></b></td>
				<td><b><rb:lang message="vac_date" locale="${locale}" /></b></td>
				<td><b><rb:lang message="vac_status" locale="${locale}" /></b></td>
			</tr>
			<c:forEach var="vac" items="${rec_vacancies}">
			<tr align="center">
				<td><c:out value="${vac.id}"></c:out></td>
				<td><a href="/HR_Project/ControllerServlet?action=showRecVacancy&amp;vac_id=${vac.id}"><c:out value="${vac.name}"></c:out></a></td>
				<td><c:out value="${vac.date}"></c:out></td>
				<td><c:out value="${vac.status}"></c:out></td>
			</tr>
			</c:forEach>
			</table>
			</c:if>
			</div>
				
		</div>
		
		<div class="side_bar">
			<div class="add_vacancy_div">
			
				<form action="/HR_Project/ControllerServlet" id="add_vac_form" method="post">
					<input type="hidden" name="action" value="addNewVacancy"/>
					<input type="hidden" name="rec_id" value="${recruiter.id}"/>
					<p><rb:lang message="add_vac_form" locale="${locale}" /></p>
					<p><rb:lang message="name" locale="${locale}" />:</p>
					<input type="text" name="vac_name" required="required"/>
					<p><rb:lang message="min_salary" locale="${locale}" />:</p>
					<input  type="text" name="min_salary" required="required"/>
					<p><rb:lang message="max_salary" locale="${locale}" />:</p>
					<input  type="text" name="max_salary"/>
					<p><rb:lang message="vac_info" locale="${locale}" />:</p>
					<textarea name="vac_info"></textarea>
					<input type="submit" class="btn" value=<rb:lang message="add_vacancy" locale="${locale}" />>
				</form>
				
			</div>
		</div>			
				
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>

</body>
</html>