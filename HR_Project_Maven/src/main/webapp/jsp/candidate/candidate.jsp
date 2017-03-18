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
			<p class=caption>Hello candidate <c:out value="${candidate.firstName} ${candidate.lastName }"/> <p>
			<div>
			<br>
			<p class=caption><rb:lang message="vac_list" locale="${locale}" /></p>
			<br>
			<table class="table" width="100%">
			<tr align="center">
				<td><b>ID</b></td>
				<td><b><rb:lang message="vac_name" locale="${locale}" /></b></td>
				<td><b><rb:lang message="vac_date" locale="${locale}" /></b></td>
				<td><b><rb:lang message="min_salary" locale="${locale}" /></b></td>
			</tr>
			<c:forEach var="vac" items="${vacancies}">
			<tr align="center">
				<td><c:out value="${vac.id}"></c:out></td>
				<td><a href="${base_url}?action=showVacancy&amp;vac_id=${vac.id}"><c:out value="${vac.name}"></c:out></a></td>
				<td><c:out value="${vac.date}"></c:out></td>
				<td><c:out value="${vac.minSalary}"></c:out></td>
			</tr>
			</c:forEach>
			</table>
			</div>
		</div>
		
		
		<div class="side_bar">
			<div>
			<br>
			<c:if test="${is_empty == true}">
			<div class=error>
				<h2><rb:lang message="no_interview" locale="${locale}" /></h2>
			</div>
			</c:if>
			<c:if test="${is_empty == false}">
				<div>
				<h2><rb:lang message="assigned_interview" locale="${locale}" /></h2>
				<br>
					<table>
					<tr align="center">
						<td><b><rb:lang message="vacancy_id" locale="${locale}" /></b></td>
						<td><b><rb:lang message="interview_date" locale="${locale}" /></b></td>
					</tr>
					<c:forEach var="interview" items="${interviews}">
					<tr align="center">
						<td><c:out value="${interview.vacancyId}"></c:out></td>
						<td><a href="${base_url}?action=showInterview&amp;date=${interview.date}&amp;rec_id=${interview.recruterId}"><c:out value="${interview.date}"></c:out></a></td>
					</tr>
					</c:forEach>
					</table>
				</div>
			</c:if>
			</div>
			<div>
			<br>
			<br>
			<c:if test="${vcc_vacs_empty == true}">
			<div class=error>
				<h2><rb:lang message="no_assigned_vac" locale="${locale}" /></h2>
			</div>
			</c:if>
			<c:if test="${vcc_vacs_empty == false}">
				<div>
				<h2><rb:lang message="assigned_vac" locale="${locale}" /></h2>
				<br>
					<table>
					<tr align="center">
						<td><b>id</b></td>
						<td><b><rb:lang message="vac_name" locale="${locale}" /></b></td>
						<td><b><rb:lang message="date_of_sign" locale="${locale}" /></b></td>
						<td><b><rb:lang message="result" locale="${locale}" /></b></td>
					</tr>
					<c:forEach var="vcc" items="${assigned_vacancies}">
					<tr align="center">
						<td><c:out value="${vcc.vacancyId }"></c:out></td>
						<td><c:out value="${vcc.vacDateOfSign }"></c:out></td>
						<td><a href="${base_url}?action=showVacancy&amp;vac_id=${vcc.vacancyId}"><c:out value="${vcc.vacancyName}"></c:out></a> </td>
						<td><c:out value="${vcc.result}"></c:out></td>
					</tr>
					</c:forEach>
					</table>
				</div>
			</c:if>
			</div>
		</div>
				
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>

</body>
</html>