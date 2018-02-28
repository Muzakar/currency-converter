<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<title>Currency Converter</title>
</head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<body>
	<h2>Currency converter Login</h2>
	<form id="loginForm" method="post" action="validate">
		<table>
			<tbody>
				<tr>
					<td>User Id:</td>
					<td><input type="text" name="userId" id="userId" size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
<script>
    jQuery("#loginForm").submit(function() {
        if (jQuery("#userId").val() == "") {
            alert("User Id must be present");
            return false;
        }
        if (jQuery("#password").val() == "") {
            alert("Password must be present")
            return false;
        }
    });
</script>
</html>
