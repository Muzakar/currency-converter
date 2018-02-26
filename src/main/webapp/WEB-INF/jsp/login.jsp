<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>Currency Converter</title>
</head>
<body>
	<h2>Currency converter Login</h2>
	<form method="post" action="validate">
		<table>
			<tbody>
				<tr>
					<td>Login:</td>
					<td><input type="text" name="userId" id="userId" size="30"
						maxlength="40" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password"
						size="30" maxlength="32" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
