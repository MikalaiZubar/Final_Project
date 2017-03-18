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
	<script src="${pageContext.request.contextPath}/js/js_validation_new.js"></script>
</head> 
<body>
	<jsp:include page="/jsp/fragment/header.jsp"></jsp:include>

	<div class="main_content">
	
	<c:if test="${candidate_added}">
			<div class=ok>
				<h2><rb:lang message="candidate_added" locale="${locale}" /></h2>
				<a href="jsp/candidate.jsp"></a>
			</div>
		</c:if>
		
		<c:if test="${validation_failed}">
			<div class=error>
				<h2><rb:lang message="validation_failed" locale="${locale}" /></h2>
			</div>
		</c:if>
		
		<c:if test="${login_is_busy}">
			<div class=error>
				<h2><rb:lang message="login_is_busy" locale="${locale}" /></h2>
			</div>
		</c:if>

		<div class="content">
			<h2><rb:lang message="reg_form" locale="${locale}" /></h2>
			<form class="reg_form" name="registration" action="/HR_Project/ControllerServlet" method="post" onSubmit="return formValidation();">
				<input type="hidden" name="action" value="addCand">
				<ul class="reg_ul">
					<li><label id="login" for="login"><b><rb:lang message="login" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="login" placeholder=<rb:lang message="login_1" locale="${locale}" /> 
					title="login can contain only latin letters"/><span class="err" id="login_err"></span></li>
					<li><label for="pass1"><b><rb:lang message="password" locale="${locale}" />:</b></label></li>
					<li><input type="password" name="pass1" placeholder=<rb:lang message="pass" locale="${locale}" /> 
					title="min 8 signs min one letter in upper case, one letter in lower case and one digit"/><span class="err" id="pass_err"></li>
					<li><label for="pass2"><b><rb:lang message="confirm" locale="${locale}" />:</b></label></li>
					<li><input type="password" name="pass2" placeholder=<rb:lang message="pass_confirm" locale="${locale}" />/><span class="err" id="pass2_err"></li>
					<li><label id="name" for="name"><b><rb:lang message="name" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="name" placeholder=<rb:lang message="name_field" locale="${locale}" />/><span class="err" id="name_err"></li>
					<li><label id="surname" for="surname"><b><rb:lang message="surname" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="surname" placeholder=<rb:lang message="surname_field" locale="${locale}" />/><span class="err" id="surname_err"></li>
					<li><label for="phone"><b><rb:lang message="phone" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="phone" placeholder=<rb:lang message="phone_field" locale="${locale}" />/>
						<span class="err" id="phone_err"></li>
					<li><label for="birthdate"><b><rb:lang message="birth" locale="${locale}" />:</b></label></li>
					<li><input type=date name="birthdate" value="birthdate"/> <span class="err" id="country_err"></li>
					<li><label for="email"><b><rb:lang message="email" locale="${locale}" />:</b></label></li>
					<li><input type="text" name="email" placeholder=<rb:lang message="email_field" locale="${locale}" />/><span class="err" id="email_err"></li>
					<li><label id="gender"><b><rb:lang message="sex" locale="${locale}" />:</b></label></li>
					<li><input type="radio" name="sex" value="male" /><span name="sex_span"><rb:lang message="male" locale="${locale}" /></span></li>
					<li><input type="radio" name="sex" value="female" /><span name="sex_span"><rb:lang message="female" locale="${locale}" /></span></li>
					<li><label for="desc"><b><rb:lang message="info" locale="${locale}" />:</b></label></li>
					<li><textarea name="desc" id="desc" placeholder=<rb:lang message="about_field" locale="${locale}" />></textarea></li>
					<li><input type="submit" class="btn" name="submit" value=<rb:lang message="reg" locale="${locale}" /> /></li>
				</ul>
				
			</form>
			
		</div>
		

		<div class="side_bar">
				<form action="/HR_Project/ControllerServlet">
					<input type="hidden" name="action" value="empty"/>
					<input type="submit" class="btn" value="<rb:lang message="go_back" locale="${locale}" />"/>
				
				</form>			
		</div>
		
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>
	
</body>
</html>