<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Travel Agency</title>
</head>
<body>
	<h1>Flight Search</h1>
	<form action="${pageContext.servletContext.contextPath}/index.html">
		<table>
			<tr>
				<th><label for="from">Origin:</label> <select name="from">
						<option value="">Select One</option>
						<c:forEach var="airport" items="${airports}">
							<option value="${airport.code}" ${from eq airport.code?'selected="selected"':''}>${airport.name}</option>
						</c:forEach>
				</select></th>
				<th><label for="to">Destination:</label> <select name="to">
						<option value="">Select One</option>
						<c:forEach var="airport" items="${airports}">
							<option value="${airport.code}" ${to eq airport.code?'selected="selected"':''}>${airport.name}</option>
						</c:forEach>
				</select></th>
				<th><label for="departure">Departure:</label> <input
					name="departure" type="date" value="${departure}"></th>
				<th><input type="submit" value="search"></th>
			</tr>
            <tr>
                <td>
                    <input type="checkbox" name="fromNearby" ${fromNearby != null ? 'checked="checked"' :'' }/>
                    <label for="fromNearby">Include Nearby Airports</label> 
                </td>
                <td>
                    <input type="checkbox" name="toNearby" ${toNearby != null ? 'checked="checked"' :'' }/>
                    <label for="toNearby">Include Nearby Airports</label> 
                </td>
                <th colspan="2"></th>
            </tr>
		</table>
	</form>
	<br />
	<br />
	<table border="1">
		<tr>
			<th>Flight Number</th>
			<th>Origin</th>
			<th>Destiny</th>
			<th>Departure</th>
		</tr>
		<c:choose>
		<c:when test="${not empty flights}">
			<c:forEach var="flight" items="${flights}">
				<tr>
					<td>${flight.number}</td>
					<td>${flight.origin.name}(${flight.origin.code})</td>
					<td>${flight.destination.name}(${flight.destination.code})</td>
					<td>${flight.departure}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
				<tr>
					<th width="800px" colspan="4">No flights found for this criteria.</th>
				</tr>		
		</c:otherwise>
		</c:choose>
	</table>

</body>
</html>
