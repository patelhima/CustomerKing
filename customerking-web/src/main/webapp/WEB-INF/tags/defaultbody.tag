<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="${resourceURL}/styles/font-awesome.min.css">
<link rel="stylesheet" href="${resourceURL}/styles/jqxgrid/jqx.base.css">
<link rel="stylesheet" href="${resourceURL}/styles/bootstrap.min.css">
<link rel="stylesheet" href="${resourceURL}/styles/style.css">
<t:genericpage>
    <jsp:attribute name="header">
		<%@ include file="../views/header.jsp" %>
    </jsp:attribute>
    <jsp:body>
	    <section>
	      <div class="main-section"  id="mainContent">
	      	<jsp:doBody/>
	      </div>
	    </section>
    </jsp:body>
</t:genericpage>
<script type="text/javascript" src="${resourceURL}/scripts/common/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxcore.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxdata.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxbuttons.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxscrollbar.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxmenu.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxlistbox.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxdropdownlist.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxgrid.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxgrid.sort.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxgrid.pager.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxgrid.selection.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxgrid.edit.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxgrid.columnsresize.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxpanel.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/common/jqxgrid/jqxgrid.filter.js"></script>
<script type="text/javascript" src="${resourceURL}/scripts/app.js"></script>