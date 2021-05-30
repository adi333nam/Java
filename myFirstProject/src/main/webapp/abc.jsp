<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addAlien">
<table>
	<tr>
	<td><label>Alien Id</label></td>
	<td><input type="number" name="aId"></td>
	</tr>
	<tr>
	<td><label>Alien Name</label></td>
	<td><input type="text" name="aName"></td>
	</tr>
	<tr>
	<td><label>Language</label></td>
	<td><input type="text" name="lang"></td>
	</tr>
	<tr>
	<td><input type="submit"></td>
	</tr>
	
</table>
</form>
<form action="getAlien">
<table>
<tr>
	<td><label>Find Alien Id</label></td>
	<td><input type="number" name="findaId"></td>
	</tr>
	<tr>
	<td><input type="submit"></td>
	</tr>
</table>
</form>
</body>
</html>