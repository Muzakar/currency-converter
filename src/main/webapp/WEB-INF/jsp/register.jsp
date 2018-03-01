<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>Currency Converter</title>
</head>
<script type='text/javascript' src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/select2.min.css" rel="stylesheet" />
<script type='text/javascript' src="<%=request.getContextPath()%>/resources/js/select2.min.js"></script>
<body>
	<h2>User Registration</h2>
	<form id="userForm" method="post" action="register">
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
					<td><input type="password" name="password" id="password" size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td>email:</td>
					<td><input type="text" name="email" id="email" size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input type="text" name="address" id="address" size="90" maxlength="40" /></td>
				</tr>
				<tr>
					<td>Post code:</td>
					<td><input type="text" name="postcode" id="postcode" size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td>Country: </td>
					<td>
					    <select name="country" id="country" class="myselect" style="width:200px;">
                        <c:forEach var="country" items="${countries}">
                            <option><c:out value="${country}"/></option>
                        </c:forEach>
					 </td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Register" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
<script>
    jQuery(".myselect").select2();
    jQuery("#email").blur(function(input) {
        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        if (reg.test(input.target.value) == false) {
            input.target.value = "";
            alert('Invalid Email Address');
            return false;
        }
        return true;
    });
    jQuery("#userForm").submit(function() {
        var userId = document.getElementById('userId').value;
        if (userId == "") {
            alert("User Id must be present");
            return false;
        }
        var userName = document.getElementById('userName').value;
        if (userName == "") {
            alert("User Name must be present")
            return false;
        }
        var pass = document.getElementById('password').value;
        if (pass == "") {
            alert("Password must be present")
            return false;
        }
        var email = document.getElementById('email').value;
        if (email == "") {
            alert("Email must be present")
            return false;
        }
        var email = document.getElementById('address').value;
        if (email == "") {
            alert("Address must be present")
            return false;
        }
        var email = document.getElementById('postcode').value;
        if (email == "") {
            alert("Postcode must be present")
            return false;
        }
    });
</script>
</html>
