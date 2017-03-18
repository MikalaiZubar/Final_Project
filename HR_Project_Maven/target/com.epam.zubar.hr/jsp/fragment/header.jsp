<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rb" uri="customtags"%>
<!DOCTYPE html>
<header class="header">
		<div class="main">
			<div class="logo">
				<div class="logo_text">
					<h1><a href="/"><b>iT-T-T</b></a></h1>
					<h2>Information Technology. Telecommunication. Trust.</h2>
				</div>

			</div>


			<c:choose>
			 		<c:when test="${is_user_in}">
					<div class="lang_select">
			 	 		<form action="/HR_Project/ControllerServlet" method="post">
							<input type="hidden" name="action" value="logOut"/>
							<input type="submit" class="btn" value="<rb:lang message="log_out" locale="${locale}" />"/>
						</form>
					</div>
				  	</c:when>
					<c:otherwise>
					<div class="lang_select">
						<form class="lang_form" id="country" action="ControllerServlet" method="get" name="cform">
							<input type="HIDDEN" name="action" value="changelang">
							<select class="country" name="lang" >
								<option value="rus">РУС</option>
						 		<option value="ger">GER</option>  
								<option selected value="eng">ENG</option>
							</select>
							<input class="lang_btn" type="submit" value="ok">
						</form>
						</div>
						<div class="menubar">
				
							<p><rb:lang message="please_register" locale="${locale}" /></p>
							
							<!-- <ul class="menu">
								<li class="selected"><a href="jsp/welcome.jsp"><rb:lang message="main" locale="${locale}" /></a></li>
								<li><a href="#"><rb:lang message="vacancies" locale="${locale}" /></a></li>
								<li><a href="#"><rb:lang message="offices" locale="${locale}" /></a>
									<ul class="city">
										<li><a href="#"><rb:lang message="ny" locale="${locale}" /></a></li>
										<li><a href="#"><rb:lang message="minsk" locale="${locale}" /></a></li>
									</ul>
								</li>
								<li><a href="#"><rb:lang message="contacts" locale="${locale}" /></a></li>
							</ul>  -->
						</div>
					</c:otherwise>
				</c:choose>
			
			
			

			</div>
	</header>