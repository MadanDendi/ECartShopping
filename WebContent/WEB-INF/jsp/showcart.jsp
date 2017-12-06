<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${scopeSession.Cart.numberOfItems == 0 }">
		<p>The Shopping Cart is Empty</p>
	</c:if>
	<div>
		<table width="485" height="164" border="0">
			<tr>
				<td>Quantity</td>
				<td>SetName</td>
				<td>SetPrice</td>
				<td>SetQuality</td>
			</tr>
			<c:forEach var="item" items="${sessionScope.Cart.items}">
				<c:set var="items" value="${item.cartItems}" />
				<tr>
					<td>${item.cartQuantity}</td>
					<td bgcolor="#00FFFF">${items.itemName}</td>
					<td bgcolor="#00FFFF">${items.itemPrice}</td>
					<td bgcolor="#00FFFF">${items.itemName}</td>
					<td><c:url var="url" value="/showcart.html">
							<c:param name="Remove" value="${items.itemId}" />
						</c:url> <a href="${url}">Remove Item</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div align="justify">
		<p>&nbsp;</p>
		<p>Total Amount:$ ${sessionScope.Cart.total}</p>
		<p>
			<%-- <c:url var="url" value="/cartStore.jsp?page=checkout" /> --%>
			<c:url var="url" value="/userLogin.html" />
			<strong><a href="${url}">Checkout</a></strong> &nbsp;&nbsp;&nbsp;
			<c:url var="url2" value="/hello.html" />
			<strong><a href="${url2}">Continue Shopping</a></strong>
		</p>
	</div>
</body>
</html>