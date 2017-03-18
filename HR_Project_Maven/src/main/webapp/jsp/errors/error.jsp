<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<title>Error</title>
</head>
<body>

<div>
	<div class="error_img">  
    	<img src="${pageContext.request.contextPath}/img/error/500error.png" width="500px" height="300px" align="middle">
    </div> 
    <br>
    <div class="error_text">
	    <p>Request from ${pageContext.errorData.requestURI} is failed</p>
		
		<p>Servlet name: ${pageContext.errorData.servletName}</p>
		
		<p>Status code: ${pageContext.errorData.statusCode}</p>
		
		<p>Exception: ${pageContext.exception}</p>
		
		<p>Exception message: ${pageContext.exception.message}</p>
	</div>
		<br>
		<br>
	<div class="back">
		<button class="btn" onclick="history.back()">
	    	<rb:lang message="go_back" locale="${locale}" />
	    </button>
	</div>
	
</div>
	
</body>
</html>