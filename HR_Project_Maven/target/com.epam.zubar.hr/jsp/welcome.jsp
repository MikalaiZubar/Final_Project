<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITTT-HR</title>
	<meta name="description" content="it, Information Technology, Telecommunication"/>
	<meta name="keywords" content="it, vacancies, job"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/> 
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon/icon.png" type="image/png"/>
</head> 
<body>
	<jsp:include page="/jsp/fragment/header.jsp"></jsp:include>

	<div class="main_content">

		<div class="content">
			<div class="info">
				<h2><rb:lang message="about" locale="${locale}" /></h2>
				<p>
					<rb:lang message="text_1" locale="${locale}" />
				</p>
			</div>

			<div class="career">
				<h2><rb:lang message="career" locale="${locale}" /></h2>
				<p>
					<rb:lang message="text_2" locale="${locale}" />
				</p>
			</div>

			<div class="study">
				<h2><rb:lang message="edu" locale="${locale}" /></h2>
				<p>			
					<rb:lang message="text_3" locale="${locale}" />
				</p>
			</div>
		</div>

		<div class="side_bar">
			
			<jsp:include page="/jsp/fragment/login_form.jsp"></jsp:include>
					
			<jsp:include page="/jsp/fragment/social_field.jsp"></jsp:include>
		</div>
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>
	
</body>
</html>