<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:defaultbody>
   <jsp:body>
		<div align="center">
			<h1>Customer King</h1>
			<h3 class="options">
				<a href="/CustomerKing/customer/search" class="anchorButton">Search Customer</a>
				<a href="/CustomerKing/customer/home/0" class="anchorButton">New Customer</a>
			</h3>
			<jsp:include page="responseMessage.jsp"></jsp:include>
			<div id='jqxWidget' style="font-size: 13px; font-family: Verdana;padding: 50px;">
		        <div id="jqxgrid">
		        </div>
		    </div>
		</div>	
   </jsp:body>
</t:defaultbody>
<script>
customerModule.getAllCustomers();
</script>
