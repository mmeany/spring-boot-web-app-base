<%@ include file="/WEB-INF/tiles/include.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title><tiles:getAsString name="title" /></title>

        <!-- Latest compiled and minified CSS 3.2.0 -->
        <link rel="stylesheet" href='<c:url value="/public/css/bootstrap.min.css" />'>
        
        <!-- Optional theme bootstrap-theme.min.css -->
        <link rel="stylesheet" href='<c:url value="/public/css/bootstrap-theme.min.css"/>'>

        <link rel="stylesheet" href='<c:url value="/public/css/site.css"/>'>
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <!-- Header -->
        <tiles:insertAttribute name="header" />
        <!-- Body -->
        <div class="container">
	        <tiles:insertAttribute name="body" />
	        <!-- Footer -->
	        <tiles:insertAttribute name="footer" />
        </div>
        <tilesx:useAttribute id="files" name="jsfiles" classname="java.util.List" />
		<c:forEach var="file" items="${files}">
		  <script src='<c:url value="${file}"/>'></script>
		</c:forEach>
    </body>
</html>