<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>
<body>
	<div class="password_div">
		<h3><rb:lang message="login" locale="${locale}" /></h3>
		<form method="post" action="ControllerServlet" id="login_form" name="lform">
			<input type="HIDDEN" name="action" value="authorization">
			<input type="text" name="login_field" placeholder=<rb:lang message="login_1" locale="${locale}" /> 
			pattern="[A-Za-z1-9]{4,10}" required="required" />
			<input type="password" name="password_field" placeholder=<rb:lang message="pass" locale="${locale}" /> />
			<input type="submit" class="btn" value=<rb:lang message="login_1" locale="${locale}" />>
			<a href="jsp/candidate/candidate_registration.jsp"><rb:lang message="reg" locale="${locale}" /></a>
		</form>
		
	</div>
		<c:if test="${auth_failed}">
			<div class=error>
				<h2 ><rb:lang message="auth_failed" locale="${locale}" /></h2>
			</div>
		</c:if>
	
</body>