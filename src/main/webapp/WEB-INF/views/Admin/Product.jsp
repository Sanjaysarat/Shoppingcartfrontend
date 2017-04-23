<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet"
	href='<c:url value="/resources/lib/jquery/jquery-ui-1.10.4.custom.css"/>'>
</head>
<body>


	<h4 style="color:black" class="red-text text-center">Product management</h4>
	
	<c:if test="${not empty error }">
		<div class="text-center alert alert-danger">${error }</div>
	</c:if>
	<c:if test="${ not empty success }">
		<div class="text-center alert alert-success">${success }</div>
	</c:if>
	<c:if test=" ${not empty message }">

		<div class="text-center alert alert-success">${message }</div>
	</c:if>
	<div class="container" style="color:black">
		<div class="panel panel-primary">
			<div class="panel-heading">Product Form</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${empty product.id }">
						<c:url var="addAction"
							value="manage_product_create?${_csrf.parameterName}=${_csrf.token}"></c:url>

					</c:when>
					<c:otherwise>
						<c:url var="addAction"
							value="manage_product_update?${_csrf.parameterName}=${_csrf.token}"></c:url>
					</c:otherwise>
				</c:choose>
				<form method="POST" action="${addAction}"
					enctype="multipart/form-data" class="form-inline">
					<div class="form-group">
						<div class="col-xs-6">
							<label for="id" class="col-xs-4 control-label">ID:</label>
							<c:choose>
								<c:when test="${empty product.id || empty createProduct  }">
									<input class="form-control" type="text" required name="id" />
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" required name="id"
										value="${product.id }" readonly="readonly" disabled="disabled">

								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6">
							<label for="name" class="col-xs-3 control-label">Name:</label>
							<c:choose>
								<c:when test="${empty product.id || empty createProduct}">
									<input class="form-control" required type="text" name="name">

								</c:when>
								<c:otherwise>

									<input class="form-control" required type="text" name="name"
										value="${product.name }">

								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6">
							<label for="name" class="col-xs-3 control-label">Price:</label>
							<c:choose>
								<c:when test="${empty product.id || empty createProduct}">
									<input class="form-control" required type="text" name="price">

								</c:when>
								<c:otherwise>

									<input class="form-control" required type="text" name="price"
										value="${product.price }">

								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6">
							<label for="name" class="col-xs-3 ">Category_ID:</label> <select
								class="selectpicker form-control" name="category">
								<c:forEach var="category" items="${categoryList }">
									<c:set var="category_select" value=""></c:set>
									<c:if test="${category.id eq product.category_id}">
										<c:set var="category_select" value="selected"></c:set>

									</c:if>

									<option ${category_select} value="${category.id }">${category.name }</option>

								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-6">
							<label for="name" class="col-xs-3 control-label">Supplier_ID:</label>
							<select class="selectpicker form-control" name="supplier">
								<c:forEach var="supplier" items="${supplierList }">
									<c:set var="supplier_select" value=""></c:set>
									<c:if test="${supplier.id eq product.supplier_id}">
										<c:set var="supplier_select" value="selected"></c:set>

									</c:if>
									<option ${supplier_select} value="${supplier.id }">${supplier.name }</option>

								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6">
							<label for="description" class="col-xs-3 control-label">Description:</label>
							<c:choose>
								<c:when test="${empty product.id || empty createProduct}">
									<input class="form-control" required type="text"
										name="description">

								</c:when>
								<c:otherwise>

									<input class="form-control" required type="text"
										name="description" value="${product.description}">
								</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-6">
						<c:choose>
						<c:when test="${empty product.id || empty createProduct}">
							Select File : <input type="file" name="Image" />
						</c:when>
						<c:otherwise>
						Select File : <input type="file" name="Image" value="${product.id }.png" />
						</c:otherwise>
						</c:choose>
						
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="form-group">
						<div class="col-xs-6">
							<input type="submit" value="Publish">
						</div>
					</div>
					<button type="reset" value="Reset">Reset</button>
				</form>
			</div>
		</div>
	</div>



	<form action=""></form>
	<div class="container"style="color:black;">
		<table border="2" class="table table-striped table-bordered">
			<thead>
				<tr>
					<td>Image</td>
					<td>ID</td>
					<td>Name</td>
					<td>Price</td>
					<td>Description</td>
					<td>Category</td>

					<td>Supplier</td>
					<td>Action</td>
				</tr>
			</thead>
			<c:forEach var="product" items="${productList}">
				<tr>
					<td><img style="width: 150px; height: 150px"
						; src="<c:url value="/resources/productimg/${product.id}.png" />" />
					</td>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>${product.description}</td>
					<td>${product.category.name}</td>
					<td>${product.supplier.name}</td>
					<td><a style="width:120px;" class="btn btn-primary"
						onclick="return confirm('Are you sure you want to edit this category?');"
						href="<c:url value='manage_product_edit/${product.id}' />"> <span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							Edit
					</a> <a class="btn btn-primary"
						onclick="return confirm('Are you sure you want to delete this product?');"
						href="<c:url value='delete_product/${product.id}' />"> <span
							class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							Delete
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>