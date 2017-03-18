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
			<p class=rec_list><b><rb:lang message="rec_list" locale="${locale}" />:</b></p>
			<c:if test="${is_active_list == true}">
			<div>
			<ul class = reg_ul>
				<c:forEach var="rec" items="${active_list}">
				<li><a href="/HR_Project/ControllerServlet?action=showRecruiter&amp;rec_id=${rec.id}"><c:out value="${rec.id }. ${rec.name}"></c:out></a></li>
				</c:forEach>
			</ul>
			</div>
			</c:if>
			<c:if test="${is_active_list == false}">
				<div class=error>
				<h2><rb:lang message="no_active_rec" locale="${locale}" /></h2>
				</div>
			</c:if>
			<br>
			<p class=rec_list><b><rb:lang message="blocked_rec_list" locale="${locale}" />:</b></p>
			<c:if test="${is_blocked_list == true}">
			<div>
			<ul class = reg_ul>
				<c:forEach var="rec" items="${blocked_list}">
				<li><a href="/HR_Project/ControllerServlet?action=showRecruiter&amp;rec_id=${rec.id}"><c:out value="${rec.id }. ${rec.name}"></c:out></a>
				</c:forEach>
			</ul>
			</div>
			</c:if>
			<c:if test="${is_blocked_list == false}">
				<div class=error>
				<rb:lang message="no_blocked_rec" locale="${locale}" />
				</div>
			</c:if>
		</div>
			
			

		
		
		<div class="side_bar">
			<c:if test="${validation_failed }">
			<div class=error>
				<h2><rb:lang message="validation_failed" locale="${locale}" /></h2>
				</div>
			</c:if>
			
			<c:if test="${login_is_busy }">
			<div class=error>
				<h2><rb:lang message="login_is_busy" locale="${locale}" /></h2>
				</div>
			</c:if>
			
			<c:if test="${form_not_filled }">
			<div class=error>
				<h2><rb:lang message="form_not_filled" locale="${locale}" /></h2>
				</div>
			</c:if>
		
			<div class="add_rec_div">
			<br>
			<form class="reg_rec_form" name="registration" action="ControllerServlet" method="post">
				<input type="hidden" name="action" value="addRecruiter"/>
				<p><rb:lang message="add_rec_form" locale="${locale}" /></p>
				<br>
				<ul class="reg_ul">
					<li><label id="login" for="login"><b><rb:lang message="login" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="login" placeholder=<rb:lang message="login_1" locale="${locale}" /> 
					title="login can contain only latin letters and digits" pattern="[A-Za-z0-9@\\._\\-]{4,12}" required="required"/><span class="err" id="login_err"></span></li>
					<li><label for="pass1"><b><rb:lang message="password" locale="${locale}" />:</b></label></li>
					<li><input type="password" name="pass1" placeholder=<rb:lang message="pass" locale="${locale}" /> 
					title="min 8 signs min one letter in upper case, one letter in lower case and one digit" 
					pattern="(?=^.{8,12}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required"/></li>
					<li><label for="pass2"><b><rb:lang message="confirm" locale="${locale}" />:</b></label></li>
					<li><input type="password" name="pass2" placeholder=<rb:lang message="pass_confirm" locale="${locale}" />
					pattern="(?=^.{8,12}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" required="required"/></li>
					<li><label id="name" for="name"><b><rb:lang message="name" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="name" required="required" placeholder=<rb:lang message="name_field" locale="${locale}" />/></li>
					<li><label id="surname" for="surname"><b><rb:lang message="surname" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="surname" required="required" placeholder=<rb:lang message="surname_field" locale="${locale}" />/></li>
					<li><label for="phone"><b><rb:lang message="phone" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="phone" required="required" placeholder=<rb:lang message="phone_field" locale="${locale}" />/>
					<li><label for="email"><b><rb:lang message="email" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="email" required="required" placeholder=<rb:lang message="email_field" locale="${locale}" />/></li>
					<li><input type="submit" class="btn" name="submit" value=<rb:lang message="reg" locale="${locale}" /> /></li>
				</ul>
				
			</form>
			<br>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>

</body>
</html>