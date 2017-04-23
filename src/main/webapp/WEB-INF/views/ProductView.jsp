
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href='<c:url value="/resources/css/ProductView.css"/>'>
</head>
<body>




<%-- <c:forEach var="product" items="${product}"></c:forEach> --%>
<%-- ${product.id}
${product.name}
${product.price}
${product.category.name}
${product.supplier.name} --%>
	
	<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
						
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="<c:url value="/resources/productimg/${product.id}.png" />" /></div>
					
						</div>
					
						
					</div>
					<div class="details col-md-6">
						<h3 class="product-title" style="color:black">${product.name }</h3>
					
						<p class="product-description" style="color:black">${product.description}</p>
						<h4 class="price" style="color:black">current price: <span>&#x20b9; ${product.price}</span></h4>
						
						
						
						<div class="action">
							<a class="add-to-cart btn btn-default" type="button" href="cart/add/${product.id}">add to cart</a>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>