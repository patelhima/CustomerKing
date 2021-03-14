<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:defaultbody>
   <jsp:body>
	<div align="center">
	 <c:choose>
		<c:when test="${not empty customer.id && customer.id != '0'}">
			<h2>Edit Customer</h2>
		</c:when>
		<c:otherwise>
			<h2>Add Customer</h2>
		</c:otherwise>   
	</c:choose> 
		<input type="hidden" id="id" name="id" value="${customer.id}"/>
		<jsp:include page="responseMessage.jsp"></jsp:include>
		<div class="options">
			<table border="0" cellpadding="5">
				<tr>
					<td>Name: </td>
					<td><input type="text" id="name" name="name" value="${customer.name}"/></td>
				</tr>
				<tr>
					<td>Email: </td>
					<td><input type="text" id="email" name="email" value="${customer.email}"/></td>
				</tr>
				<tr>
					<td>Address: </td>
					<td><textarea class="form-control " name="address" id="address" rows="3" style="width: 100%;">${customer.address}</textarea></td>
				</tr>	
				<tr>
					<td>Gender: </td>
					<td>
					<%-- <input type="text" id="gender" name="gender" value="${customer.gender}"/> --%>
						<input type="radio" name="gender-radio" id="gender" value="Male" <c:out value="${customer.gender == 'Male' ? 'checked' : ''}"/>> Male
						<input type="radio"name="gender-radio" id="gender" value="Female" <c:out value="${customer.gender == 'Female' ? 'checked' : ''}"/>> Female
						<input type="radio" name="gender-radio" id="gender" value="Other" <c:out value="${customer.gender == 'Other' ? 'checked' : ''}"/>> Other
					</td>
				</tr>		
				<tr>
					<td>Date Of Birth: </td>
					<td><input type="date" id="dob" name="dob" style="width: 100%;" value="${customer.dob}"/></td>
				</tr>
				<tr>
					<td>Profession: </td>
					<td><input type="text" id="profession" name="profession" value="${customer.profession}"/></td>
				</tr>
				<tr>
					<td><input type="button" class="btn btn-sm btn-primary mr-2" id="addCustomer" value="Save"></td>
					<td><a href="/CustomerKing/" class="btn btn-sm btn-secondary mr-2">Back</a></td>
				</tr>						
			</table>
			</div>
	</div>
   </jsp:body>
</t:defaultbody>

