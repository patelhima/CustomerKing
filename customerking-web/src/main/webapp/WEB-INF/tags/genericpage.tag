<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<html>
  <body>
  	<div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div style="padding-bottom:20px;"> </div>
  </body>
</html>
