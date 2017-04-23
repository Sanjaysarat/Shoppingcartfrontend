
<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="row"><h1 class="red-text text-center">Electronic Store</h1></div></div>
	 



<nav class="navbar navbar-light" style="background-color: #e3f2fd;">
<div class="container">
   <a href="./" >Home</a> 
 <c:if test="${not empty loginmsg }">
                   <a href="cart"  title="Cart">Cart</a>
					<a  href="logout">Logout</a>
				
				</c:if>
				

<c:if test="${empty loginmsg}">
<a href="register"  title="Register">Register</a>
<a href="login" title="Log In">Log In</a>
</c:if>

</div>
</nav>

