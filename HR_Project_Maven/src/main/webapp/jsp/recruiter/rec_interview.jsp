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
				<td align="right"><b><rb:lang message="date" locale="${locale}" />:</b> </td>
				<td>${interview.date }</td>
			</tr>
			<tr>	
				<td align="right"><b><rb:lang message="candidate_name" locale="${locale}" />:</b></td>
				<td> ${candidate.firstName } ${candidate.lastName }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="rec_phone" locale="${locale}" />:</b> </td> 
				<td>${candidate.phone }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="rec_email" locale="${locale}" />:</b></td>
				<td> ${candidate.email }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="int_status" locale="${locale}" />:</b></td>
				<td> ${interview.status }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="int_number" locale="${locale}" />:</b></td>
				<td> ${interview.number }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="int_comment" locale="${locale}" />:</b></td> 
				<td>${interview.comment }</td>
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
		
			<div class="update_interview_div">
			
				<form action="ControllerServlet" id="update_int_form" method="post">
					<input type="hidden" name="action" value="updateInterview"/>
					<input type="hidden" name="int_id" value="${interview.date}"/>
					<input type="hidden" name="cand_id" value="${candidate.id}"/>
					<p><rb:lang message="update_int_form" locale="${locale}" /></p>
					<ul>
					<li><label for="int_date"><rb:lang message="date" locale="${locale}" />:</label></li>
					<li><input type=date name="int_date"/> </li>
					<li><label for="int_time"><rb:lang message="time" locale="${locale}" />:</label></li>
					<li><input type="time" name="int_time" />
					<li><label for="int_status"><rb:lang message="status" locale="${locale}" />:</label></li>
					<li><select name="int_status">
						<option><rb:lang message="assigned_status" locale="${locale}" /></option>
						<option><rb:lang message="positive_status" locale="${locale}" /></option>
						<option><rb:lang message="negative_status" locale="${locale}" /></option>
					</select></li>
					<li><label for="int_number"><rb:lang message="number" locale="${locale}" />:</label></li>
					<li><select name="int_number">
						<option><rb:lang message="first_number" locale="${locale}" /></option>
						<option><rb:lang message="second_number" locale="${locale}" /></option>
					</select></li>
					<li><label for="int_comment"><rb:lang message="int_comment" locale="${locale}" />:</label></li>
					<li><textarea name="int_comment"></textarea></li>
					<li><input type="submit" class="btn" value=<rb:lang message="update_interview" locale="${locale}" />></li>
					</ul>
				</form>
			</div>
		</div>		
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>
</body>
</html>