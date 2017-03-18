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
			
			<br>
			<div class="assign_f">
				<form action="ControllerServlet" method="post">
					<input type="hidden" name="action" value="addNewInterview"/>
					<ul class="reg_ul">
						<li><label  for="candidate"><b><rb:lang message="chose_candidate" locale="${locale}" />:</b></label></li>
						<li><select class="candidate" name="rec_cand" >
								<c:forEach var="cand" items="${cands_for_assign}">
								<option value="${cand.id}">id-${cand.id}. ${cand.firstName} ${cand.lastName}</option>
								</c:forEach>
						</select></li>
						<li><label for="int_date"><b><rb:lang message="date" locale="${locale}" />:</b></label></li>
						<li><input type=date name="int_date" value="int_date"/> </li>
						<li><label for="time"><b><rb:lang message="time" locale="${locale}" />:</b></label></li>
						<li><input type="time" name="time" />
						<li><label for="comment"><b><rb:lang message="comment" locale="${locale}" />:</b></label></li>
						<li><textarea name="comment" id="desc" placeholder=<rb:lang message="add_comment" locale="${locale}" />></textarea></li>
						<li><input type="submit" class="btn" name="submit" value=<rb:lang message="assign_int" locale="${locale}" /> /></li>
					</ul>
				</form>
			</div>
			
		</div>				
				
		<div class="side_bar">
			<br>
			<div class="back">
				<form action="ControllerServlet" method="post">
			 		<input type="hidden" name="action" value="goFirstPage" >
			 		<input type="hidden" name="user_role" value="${user.role}">
			 		<input type="submit" class="btn" value="<rb:lang message="go_back" locale="${locale}" />"/>
			 	</form>
			</div>
			<div class="error">
				<c:if test="${date_is_busy}">
					<h2><rb:lang message="busy_date" locale="${locale}" /></h2>
				</c:if>
			</div>
		</div>			
				
	</div>
	<jsp:include page="/jsp/fragment/footer.jsp"></jsp:include>

</body>
</html>