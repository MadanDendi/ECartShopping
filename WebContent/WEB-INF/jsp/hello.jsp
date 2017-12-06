<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.Cart.numberOfItems > 0}">
		<p>
			<c:url var="url" value="/showcart.html">
				<c:param name="Clear" value="0" />
				<c:param name="Remove" value="0" />
			</c:url>
			<div align="left"><a href="${url}">Show Cart(${sessionScope.Cart.numberOfItems})</a>&nbsp;&nbsp;&nbsp;</div>
			
		</p>
		<p>&nbsp;</p>
	</c:if>
 <c:forEach var="setList" items="${setDetailsList}">
<div align="center">

			<table width="541" height="201" border="1">
				<tr>
					<td width="335" height="33">
					<div align="center">
						<c:url var="url" value="/itemDetails">
						<c:param name="setID" value="${setList.itemId}" />	</c:url>
						<a href="${url}">${setList.itemName}</a>
					</div>	
					</td>
					<td width="355" rowspan="4"><img src="${setList.itemImageUrl }" width="256" height="171" alt="abaddon.png" longdesc="images/Anointed_Armor_of_Ruination_Set.png"></td>
					
				</tr>
				<tr>
							<td height="42"><div align="center">Price:$ ${setList.itemPrice}</div></td>
				</tr>
				<tr>
					<td height="42"><div align="center">
						<!--  Adds the specific item to the cart.-->
							<c:url var="url2" value="/itemcatalog.html">
								<c:param name="Add" value="${setList.itemId}" />
								<c:param name="name" value="${setList.itemName}" />
							</c:url>
							<a href="${url2}">Add to Cart</a>
						</div></td>
				</tr>
			</table>
		</div>
</c:forEach>
</body>
</html>