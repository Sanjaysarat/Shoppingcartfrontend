<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="row">
	<div class="page-header">
	<h1 class="text-center" style="color:black">TOP CATEGORIES</h1>
	</div>
	<c:forEach items="${categoryList}" var="category">
        <!-- Boxes de Acoes -->
    	<div class="col-xs-12 col-sm-6 col-lg-4" style="padding-top:50px;">
			<div class="box">
			<div class="icon">	
			<div class="info">
						<h3 class="title" style="font-style:italic;color:#80124b">${category.name }</h3>
			<a href="category/${category.id }"><img style="width: 250px; height: 250px"; src="<c:url value="/resources/categoryImage/${category.id}.png" />" /></a>
			</div>
			</div>
			</div>
			</div>
			</c:forEach>
			<br>
			</div>
			</div>
</body>
</html>