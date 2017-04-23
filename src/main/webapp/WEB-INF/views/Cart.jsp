
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>

<style>
.servive-block {
  padding: 20px 30px;
  text-align: center;
  margin-bottom: 20px;
}
.servive-block-grey {
  background: #95a5a6;
}


</style>
</head>
<body>

${cart_list}





<div class="container">
	<div class="row">
	<c:choose>
	<c:when test="${not empty cart}">
		<div class="col-xs-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<div class="row">
							<div class="col-xs-6">
								<h5><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</h5>
							</div>
							<div class="col-xs-6">
								<button type="button" class="btn btn-primary btn-sm btn-block">
									<span class="glyphicon glyphicon-share-alt"></span> Continue shopping
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
				<c:forEach var="myCart" items="${cart}">
					<div class="row">
						<div class="col-xs-2"><img class="img-responsive" src="http://placehold.it/100x70">
						</div>
						<div class="col-xs-4">
							<h4 class="product-name" style="color:gray"><strong>${myCart.product_name}</strong></h4>
						</div>
						<div class="col-xs-6">
							<div class="col-xs-6 text-right">
								<h6><strong>${myCart.price} <span class="text-muted">x</span></strong></h6>
							</div>
							<div class="col-xs-4">
								<input type="text" class="form-control input-sm" value="1">
							</div>
							<div class="col-xs-2">
								<a type="button" class="btn btn-link btn-xs" href="delete_cart/${myCart.id}">
									<span class="glyphicon glyphicon-trash"> </span>
								</a>
							</div>
						</div>
					</div>
					<hr>
				</c:forEach>
					<hr>
					<div class="row">
						<div class="text-center">
							<div class="col-xs-9">
								<h6 class="text-right">Added items?</h6>
							</div>
							<div class="col-xs-3">
								<button type="button" class="btn btn-default btn-sm btn-block" onclick="window.location = 'cart_checkout';">
									Update cart
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div class="row text-center">
						<div class="col-xs-9">
							<h4 class="text-right" style="color:black">Total <strong>&#x20b9; ${TotalAmount}</strong></h4>
						</div>
						<div class="col-xs-3">
							<button type="button" class="btn btn-success btn-block" onclick="window.location = 'cart_checkout';">
								Checkout
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:when>
		<c:otherwise>
		<div class="text-center servive-block servive-block-grey"><p>Your Cart Is Empty</p></div>
		</c:otherwise>
		</c:choose>
	</div>
</div>