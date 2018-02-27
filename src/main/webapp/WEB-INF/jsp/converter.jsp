<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>Currency Converter</title>
</head>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
<body>
	<h2>Currency Converter</h2>
	<h3> Welcome ${userName} </h3>
	<a href="logout">Logout</a>
	<form method="post" action="convert">
	<input type="hidden" name="userId" id="userId" value="${userId}" />
		<table>
			<tbody>
				<tr>
					<td>From Currency: <input type="text" name="fromCurrency" id=""fromCurrency", fromCurrency" size="30" maxlength="5" value='${fromCurrency}'/></td>
					<td>From Amount: <input type="text" name="fromAmount" id="fromAmount" size="30" maxlength="40" value='${fromAmount}' /></td>
				</tr>
				<tr>
					<td>To Currency: <input type="text" name="toCurrency" id="toCurrency" size="30" maxlength="5" value='${toCurrency}' /></td>
					<td>To Amount: <input type="text" name="toAmount" id="toAmount" size="30" maxlength="40" value='${toAmount}'/></td>
				</tr>
				<tr>
                	<td></td>
                	<td><input type="submit" value="Convert" /></td>
                </tr>
			</tbody>
		</table>
	</form>
	<table>
	    <tr>
            <td>
                <c:out value="From Currency"/>
            </td>
            <td>
                <c:out value="From Amount"/>
            </td>
            <td>
                <c:out value="To Currency"/>
            </td>
            <td>
                <c:out value="To Amount"/>
            </td>
            <td>
                <c:out value="Exchange Rate"/>
            </td>
            <td>
                <c:out value="Query time"/>
            </td>
        </tr>
        <c:forEach var="conversion" items="${conversions}">
            <tr>
                <td>
                    <c:out value="${conversion.fromCurrency}"/>
                </td>
                <td>
                    <c:out value="${conversion.fromAmount}"/>
                </td>
                <td>
                    <c:out value="${conversion.toCurrency}"/>
                </td>
                <td>
                    <c:out value="${conversion.toAmount}"/>
                </td>
                <td>
                    <c:out value="${conversion.forex}"/>
                </td>
                <td>
                    <c:out value="${conversion.time}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
