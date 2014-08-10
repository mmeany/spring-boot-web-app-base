<%@ include file="/WEB-INF/tiles/include.jsp"%>
<div id="ajaxload">
<h1>Visitor List<small> - viewing page ${page.number + 1} of ${page.totalPages}</small></h1>
<div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Name</th>
                <th>Username</th>
                <th>Enabled</th>
                <th>Administrator</th>
                <th>Date created</th>
                <th>Last updated</th>
                <th>Visits</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${page.content}">
                <c:choose>
                    <c:when test="${not user.enabled}">
                       <c:set var="clazz" value="class='warning'"/>
                    </c:when>
                    <c:when test="${user.administrator}">
                       <c:set var="clazz" value="class='info'"/>
                    </c:when>
                    <c:otherwise>
                       <c:set var="clazz" value=""/>
                    </c:otherwise>
                </c:choose>
                
            <tr ${clazz}>
                <td>${user.name}</td>
                <td>${user.username}</td>
                <td>
	                <c:choose>
		                <c:when test="${user.enabled}">
		                   <span class="label label-success">Enabled</span>
		                </c:when>
		                <c:otherwise>
	                        <span class="label label-default">Disabled</span>
		                </c:otherwise>
	                </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.administrator}">
                           <span class="label label-success">Administrator</span>
                        </c:when>
                    </c:choose>
                </td>
                <td><fmt:formatDate value="${user.createDate.time}" type="date" pattern="dd/MM/yyyy HH:mm" /></td>
                <td><fmt:formatDate value="${user.lastUpdatedDate.time}" type="date" pattern="dd/MM/yyyy HH:mm" /></td>
                <td>${user.numberOfVisits}</td>
                <td>
                    <a href="<c:url value="/admin/user/view/${user.id}"/>">view</a> |
                    <a href="<c:url value="/admin/user/edit/${user.id}"/>">edit</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<mvm:pagination surrounding="2" showSearch="false"/>

</div>
