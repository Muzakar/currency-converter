<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>Currency Converter</title>
</head>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<style>
.data th, .data td {
    border: 1px solid black;
}
</style>

<body>
	<h2> Currency Converter </h2>
	<h3> Welcome ${userName}. </h3>
	<form id="conversionForm" method="post" action="convert">
	    <input type="hidden" name="userId" id="userId" value="${userId}" />
		<table>
			<tbody>
				<tr>
					<td>
					    From Currency:
					</td>
					<td>
					    <select name="fromCurrency" id="fromCurrency" class="myselect" style="width:100px;">
					    <c:forEach var="ccy" items="${currencies}">
					        <option><c:out value="${ccy}"/></option>
					    </c:forEach>
					</td>
					<td>
                        To Currency:
                    </td>
                    <td>
                        <select name="toCurrency" id="toCurrency" class="myselect" style="width:100px;">
                        <c:forEach var="ccy" items="${currencies}">
                            <option><c:out value="${ccy}"/></option>
                        </c:forEach>
                    </td>
                    <td>
                        Amount:
                    </td>
                    <td>
                        <input type="text" name="fromAmount" id="fromAmount" size="11" maxlength="40"/>
                    </td>
                    <td>
                        <input type="submit" value="Convert" />
                    </td>
				</tr>
			</tbody>
		</table>
	</form>
	</br>
	<h3> Your Conversions ( Including latest in first row ): </h3>
	<table class="data">
	    <tr>
            <td>From Currency</td>
            <td>From Amount</td>
            <td>To Currency</td>
            <td>To Amount</td>
            <td>Exchange Rate</td>
            <td>Query time</td>
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
    <br/>
    <button onclick="window.location='logout';">Logout</button>
</body>

<script>
    jQuery(".myselect").select2();
    jQuery("#fromAmount").blur(function(input) {
        var resultValue = Number(input.target.value).toFixed(2);
        input.target.value = resultValue;
    });
    jQuery("#conversionForm").submit(function(){
        var fromCurrency = document.getElementById('fromAmount').value;
        if (fromCurrency == "") {
            alert("Amount must be present");
            return false;
        }
    });
</script>

</html>
