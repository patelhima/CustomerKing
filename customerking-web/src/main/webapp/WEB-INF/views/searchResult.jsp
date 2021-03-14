<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:defaultbody>
   <jsp:body>
		<div align="center">
			<h1>Search Customer</h1>
			<h3 class="options">
				<input type="text" id="searchKeyword" name="searchKeyword" /> &nbsp; <input type="button" value="Search" id="search" />
				<a href="/CustomerKing/" class="btn btn-sm btn-secondary mr-2" id="searchBack">Back</a>
			</h3>
			<div id='jqxWidget' style="font-size: 13px; font-family: Verdana;padding: 50px;">
		        <div id="searchresultjqxgrid">
		        </div>
		    </div>
		</div>	
   </jsp:body>
</t:defaultbody>

