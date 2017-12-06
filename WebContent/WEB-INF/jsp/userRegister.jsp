<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

<form name="form1" method="post" action="register.html">
<center><a style="color: red;"> ${Message }</a></center>
  <table width="258" border="0" align="center">
  
    <tr>
      <td><div align="center">User ID</div></td>
      <td><label for="username"></label>
      <input type="text" name="username" id="username"></td>
    </tr>
    <tr>
      <td><div align="center">Password </div></td>
      <td><label for="password"></label>
      <input type="password" name="password" id="password"></td>
    </tr>
    <tr>
      <td><div align="center">Full Name</div></td>
      <td><label for="email"></label>
      <input type="text" name="fname" id="fname"></td>
    </tr>
    <tr>
      <td><div align="center">Phone Number:</div></td>
      <td><label for="phone"></label>
      <input type="text" name="phone" id="phone"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input name="register" type="submit" id="register" value="Register" onclick="validate()">   <input name="cancel" type="reset" id="cancel" value="Cancel"></td>
    </tr>
  </table>
</form>
</body>
</html>