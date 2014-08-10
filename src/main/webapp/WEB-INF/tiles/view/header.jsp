<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tiles:importAttribute name="user" />

<!-- A Nav Bar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"><tiles:getAsString name="brand" /></a>
    </div>
    <c:choose>
        <c:when test="${not empty currentUser}">
            <p class="navbar-text">signed in as ${currentUser.name} <span class="badge">${currentUser.numberOfVisits}</span></p>
        </c:when>
        <c:otherwise>
            <p class="navbar-text">not signed in</p>
        </c:otherwise>
    </c:choose>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <sec:authorize access="isAuthenticated()">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Options ... <span class="caret"></span></a>
	          <ul class="dropdown-menu" role="menu">
	            <li><a href="<c:url value="/home" />">Home</a></li>
                <li class="divider"></li>
	            <li><a href="<c:url value="/admin/greet/Jane" />">Admin as Jane</a></li>
                <li class="divider"></li>
                <li><a href="<c:url value="/admin/user/list" />">List users</a></li>
	            <li class="divider"></li>
		        <li><a href='<c:url value="/logout" />'>Sign out</a></li>
	          </ul>
	        </li>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <li><a href='<c:url value="/login" />'>Sign in</a></li>
        </sec:authorize>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</div>