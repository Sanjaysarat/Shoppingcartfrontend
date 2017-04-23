<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

</head>
<body>


	<h4 style="color:black" class="red-text text-center">Category Management</h4>
<c:if test="${not empty error }">
		<div class="text-center alert alert-danger">${error }</div>
	</c:if>
	<c:if test="${ not empty success }">
		<div class="text-center alert alert-success">${success }</div>
	</c:if>
	<div class="container" style="color:black">
	<div class="panel panel-primary">
  <div class="panel-heading">Category Form</div>
  <div class="panel-body">
  <c:choose>
  <c:when test="${empty category.id}">
 
  <c:url var="addAction" value="manage_category_create?${_csrf.parameterName}=${_csrf.token}"></c:url>
 
  </c:when>
  <c:otherwise>
   
    <c:url var="addAction" value="manage_category_update?${_csrf.parameterName}=${_csrf.token}"></c:url>
    
  </c:otherwise>
  </c:choose>
		<form action="${addAction}" method="POST" enctype="multipart/form-data" class="form-inline">
			<div class="form-group">
			<div class="col-xs-8">
				<label for="id" class="col-xs-4 control-label">ID:</label>
				<c:choose>
				<c:when test="${empty category.id || empty admminCreateCategory }">
				<input type="text" class="form-control" name="id" required >
					</c:when>
					<c:otherwise>
					
					<input type="text" class="form-control" required name="id" value="${category.id }" readonly="readonly" disabled="disabled">
					
					</c:otherwise>
					</c:choose>
			</div>
			</div>
			<div class="form-group">
			<div class="col-xs-8">
				<label for="name" class="col-xs-4 control-label">Name:</label>
				<c:choose>
				<c:when test="${empty category.id || empty admminCreateCategory }">
				<input class="form-control" required type="text" name="name">
				
				</c:when>
				
				<c:otherwise>
				
				<input class="form-control" required type="text" name="name" value="${category.name}">
				</c:otherwise>
				</c:choose>
			</div>
			</div>
			<div class="form-group">
			<div class="col-xs-8">
				<label for="description" class="col-xs-4 control-label">Description:</label>
				<c:choose>
				<c:when test="${empty category.id || empty admminCreateCategory }">
				<textarea class="form-control" required  type="text-area" name="description"> </textarea>
				</c:when>
				
				<c:otherwise>
				<textarea class="form-control" required type="text-area" name="description"> ${category.description } </textarea>
				
				
				</c:otherwise>
				
				
				</c:choose>
			</div>
			</div>
			<div class="form-group">
						<div class="col-xs-8">
							Select File : <input type="file" required required name="cat_image" />
						</div>
					</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<c:choose>
			<c:when test="${empty category.id || empty admminCreateCategory }">
			<input  type="submit" value="Publish">
			</c:when>
			<c:otherwise>
			
			<input  type="submit" value="Update">
			</c:otherwise>
			
			</c:choose>
		</form>
		</div>
		</div>
	</div>
	<div class="container" style="color:black">
		<table border="2" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>Image</th>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:set var="count" value="0" scope="page" />
			<c:forEach var="category" items="${categoryList}">
			<c:set var="count" value="${count + 1}" scope="page"/>
				<tr>
					<th scope="row">${count}</th>
					<td><img style="width: 150px; height: 150px"; src="<c:url value="/resources/categoryImage/${category.id}.png" />" />
					</td>
					<td>${category.id}</td>
					<td>${category.name}</td>
					<td>${category.description}</td>
					<td>
					<a class="btn btn-primary"
									onclick="return confirm('Are you sure you want to edit this category?');"
									href="<c:url value='/manage_category_edit/${category.id}' />"> 
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit
								</a>

								<a class="btn btn-primary"
									onclick="return confirm('Are you sure you want to delete this Category?');"
									href="<c:url value='manage_category_delete/${category.id}' />"> 
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
								</a>

							</nobr>
					
					
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>


<nobr>
								