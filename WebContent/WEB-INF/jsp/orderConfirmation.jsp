<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="justify">
<p>Thank you for placing the Order for the below items:
<table>
	<c:forEach var="order" items="${sessionScope.Cart.items }">
		<c:set var="itemOrdered" value="${order.cartItems}" />
		<tr>
			<td>${itemOrdered.itemName}</td>
			<td>${order.cartQuantity}</td>
		</tr>

	</c:forEach>
</table>
</div>