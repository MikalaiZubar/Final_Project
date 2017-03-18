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
			<h2><rb:lang message="vac_caption" locale="${locale}" /></h2>
			
			<table class="table" width=100%>
			<tr>
				<td align="right"><b><rb:lang message="vac_name" locale="${locale}" />:</b> </td>
				<td width=70%>${the_vacancy.name }</td>
			</tr>
			<tr>	
				<td align="right"><b><rb:lang message="vac_date" locale="${locale}" />:</b></td>
				<td> ${the_vacancy.date }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="vac_info" locale="${locale}" />:</b> </td> 
				<td>${the_vacancy.info }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="min_salary" locale="${locale}" />:</b></td>
				<td> ${the_vacancy.minSalary }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="max_salary" locale="${locale}" />:</b></td> 
				<td>${the_vacancy.maxSalary }</td>
			</tr>
			</table>
			<br>
			<br>
						
			<c:if test="${can_assign == true}">
				<div>
				<form action="ControllerServlet" method="post">
					<input type="hidden" name="action" value="assignAnInterview"/>
					<input type="hidden" name="vac_id" value="${the_vacancy.id}"/>
					<input type="submit" class="btn" value="<rb:lang message="add_interview" locale="${locale}"/>"/>
				
				</form>
				</div>
			</c:if>	
			<br>
			<div>
				<form action="ControllerServlet" method="post">
					<input type="hidden" name="action" value="closeVacancy"/>
					<input type="hidden" name="vac_id" value="${the_vacancy.id}"/>
					<input type="submit" class="btn" value="<rb:lang message="close_vacancy" locale="${locale}"/>"/>
				</form>
			</div>
			<br>
			<form action="ControllerServlet" method="post">
		 		<input type="hidden" name="action" value="goFirstPage" >
		 		<input type="hidden" name="user_role" value="${user.role}">
		 		<input type="submit" class="btn" value="<rb:lang message="go_back" locale="${locale}" />"/>
		 	</form>
				
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
					<table width=100%>
					<tr align="center">
						<td><b><rb:lang message="vacancy_id" locale="${locale}" /></b></td>
						<td><b><rb:lang message="interview_date" locale="${locale}" /></b></td>
						<td><b><rb:lang message="candidate_id" locale="${locale}" /></b></td>
					</tr>
					<c:forEach var="interview" items="${interviews_on_vac}">
					<tr align="center">
						<td><c:out value="${interview.vacancyId}"></c:out></td>
						<td><a href="${base_url}?action=showRecInterview&amp;date=${interview.date}&amp;cand_id=${interview.candidateId}"><c:out value="${interview.date}"></c:out></a></td>
						<td><c:out value="${interview.candidateId}"></c:out></td>
					</tr>
					</c:forEach>
					</table>
				</div>
			</c:if>
			<br>
			<br>
				<c:if test="${no_candidates == true}">
			<div class=error>
				<h2><rb:lang message="no_candidates" locale="${locale}" /></h2>
			</div>
			</c:if>
			<c:if test="${no_candidates == false}">
				<div>
				<h2><rb:lang message="assigned_candidates" locale="${locale}" /></h2>
				<br>
					<table width=100%>
					<tr align="center">
						<td><b><rb:lang message="candidate_id" locale="${locale}" /></b></td>
						<td><b><rb:lang message="candidate_name" locale="${locale}" /></b></td>
						<td><b><rb:lang message="candidate_surname" locale="${locale}" /></b></td>
					</tr>
					<c:forEach var="candidate" items="${cand_for_vac}">
					<tr align="center">
						<td><c:out value="${candidate.id}"></c:out></td>
						<td><c:out value="${candidate.firstName}"></c:out></td>
						<td><c:out value="${candidate.lastName}"></c:out></td>
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