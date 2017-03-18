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
			
			<br>
			<h2><rb:lang message="vac_caption" locale="${locale}" /></h2>
			<table class="table" width=100%>
			<tr>
				<td align="right"><b><rb:lang message="vac_name" locale="${locale}" />:</b> </td>
				<td>${vacancy.name }</td>
			</tr>
			<tr>	
				<td align="right"><b><rb:lang message="vac_date" locale="${locale}" />:</b></td>
				<td> ${vacancy.date }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="vac_info" locale="${locale}" />:</b> </td> 
				<td>${vacancy.info }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="min_salary" locale="${locale}" />:</b></td>
				<td> ${vacancy.minSalary }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="max_salary" locale="${locale}" />:</b></td> 
				<td>${vacancy.maxSalary }</td>
			</tr>
			</table>
			<br>
			
			<form action="ControllerServlet" method="post">
		 		<input type="hidden" name="action" value="goFirstPage" >
		 		<input type="hidden" name="user_role" value="${user.role}">
		 		<input type="submit" class="btn" value="<rb:lang message="go_back" locale="${locale}" />"/>
		 	</form>
		</div>
			

		<div class="side_bar">
			<div class="redirect_vac">
			<form action="ControllerServlet" method="post">
				<input type="hidden" name="action" value="redirectVacancy"/>
				<input type="hidden" name="vac_id" value="${vacancy.id}"/>
				<p><rb:lang message="reassign_form" locale="${locale}" /></p>
				<br>
				<ul>
				<c:forEach var="rec" items="${rec_list}">
					<li><input type="radio" name="rec_id"   value="${rec.id}"><c:out value="${rec.name}"></c:out></li>				
				</c:forEach>
				</ul>
				<br>
				<input type="submit" class="btn" name="submit" value=<rb:lang message="assignVac" locale="${locale}" /> />
			</form>
			</div>
			
		</div>
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>

</body>
</html>