
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<style>
.navbar .navbar-nav {
    display: inline-block;
    float: none;
}

.navbar .navbar-collapse {
    text-align: center;
}
</style>

</head>

       

		<ul class="nav navbar-nav  "  role="tablist">
			<c:forEach items="${categoryList}" var="category">
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="product/${category.id}"> ${category.name} <span
						class="caret"></span></a>

<c:if test="${not empty category.products}">
					<ul class="dropdown-menu" role="menu">
					
						<c:forEach items="${category.products}" var="product">
						
							<li><a href="display_product/get/${product.id}">${product.name}</a></li>
							
						</c:forEach>
						<li><a href="category/${category.id}">All Product</a></li>
					</ul></c:if></li>
			</c:forEach>

		</ul>


