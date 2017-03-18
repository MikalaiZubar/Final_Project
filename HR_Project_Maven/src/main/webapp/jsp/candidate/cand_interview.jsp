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
  	<link rel="stylesheet" type="text/css" href="../css/style.css"/> 
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/icon.png" type="image/png"/>
</head>
<body>
	<jsp:include page="/jsp/fragment/header.jsp"></jsp:include>
	
	<div class="main_content">

		<div class="content">
			<br>
			<h2><rb:lang message="int_caption" locale="${locale}" /></h2>
			<br>
			<table class="table" width=100%>
			<tr>
				<td align="right"><b><rb:lang message="int_date" locale="${locale}" />:</b> </td>
				<td>${interview.date }</td>
			</tr>
			<tr>	
				<td align="right"><b><rb:lang message="int_status" locale="${locale}" />:</b></td>
				<td> ${interview.status }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="int_number" locale="${locale}" />:</b> </td> 
				<td>${interview.number }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="int_comment" locale="${locale}" />:</b></td>
				<td> ${interview.comment }</td>
			</tr>
			
			</table>
			<br>
			<br>
			
			<form action="ControllerServlet" method="post">
		 		<input type="hidden" name="action" value="goFirstPage" >
		 		<input type="hidden" name="user_role" value="${user.role}">
		 		<input type="submit" class="btn" value="<rb:lang message="go_back" locale="${locale}" />"/>
		 	</form>
		<!-- 	<button class="btn" onclick="history.back()"><rb:lang message="go_back" locale="${locale}" /></button>  -->
		</div>
		
		
		<div class="side_bar">
			<br>
			<h2><rb:lang message="rec_caption" locale="${locale}" /></h2>
			<br>
			<table class="table" width=100%>
			<tr>
				<td align="right"><b><rb:lang message="rec_name" locale="${locale}" />:</b> </td>
				<td>${recruter.name }</td>
			</tr>
			<tr>	
				<td align="right"><b><rb:lang message="rec_phone" locale="${locale}" />:</b></td>
				<td> ${recruter.phone }</td>
			</tr>
			<tr>
				<td align="right"><b><rb:lang message="rec_email" locale="${locale}" />:</b> </td> 
				<td>${recruter.email }</td>
			</tr>
			</table>
		</div>		
	</div>
	
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>


</body>
</html>