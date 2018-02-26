<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>Currency Converter</title>
</head>
<body>
	<h2>User Registration</h2>
	<form method="post" action="register">
		<table>
			<tbody>
				<tr>
					<td>User Id:</td>
					<td><input type="text" name="userId" id="userId" size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td>User Name:</td>
					<td><input type="text" name="userName" id="userName" size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="30" maxlength="32" /></td>
				</tr>
				<tr>
					<td>email:</td>
					<td><input type="text" name="email" id="email" size="60" maxlength="100" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Register" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
