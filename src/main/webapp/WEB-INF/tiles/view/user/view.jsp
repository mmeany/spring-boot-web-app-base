<%@ include file="/WEB-INF/tiles/include.jsp"%>

<h1>User account</h1>
<div class="row">
    <div class="col-md-2">
        <p><strong>Id</strong></p>
        <p><strong>Version</strong></p>
        <p><strong>Name</strong></p>
        <p><strong>Username</strong></p>
        <p><strong>Visits</strong></p>
        <p><strong>Enabled</strong></p>
        <p><strong>Admin</strong></p>
        <p><strong>Date created</strong></p>
        <p><strong>Last updated</strong></p>
        <p><strong>Last signed in</strong></p>
        <p><strong>Authorities</strong></p>
    </div>
    <div class="col-md-2">
        <p>${user.id}&nbsp;</p>
        <p>${user.version}&nbsp;</p>
        <p>${user.name}&nbsp;</p>
        <p>${user.username}&nbsp;</p>
        <p>${user.numberOfVisits}&nbsp;</p>
        <p><c:choose><c:when test="${user.enabled}"><span class="label label-success">Enabled</span></c:when><c:otherwise><span class="label label-default">Disabled</span></c:otherwise></c:choose>&nbsp;</p>
        <p><c:if test="${user.administrator}"><span class="label label-success">Admin</span></c:if>&nbsp;</p>
        <p><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${user.createDate.time}" />&nbsp;</p>
        <p><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${user.lastUpdatedDate.time}" />&nbsp;</p>
        <p><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${user.lastLoginDate.time}" />&nbsp;</p>
        <p>roles</p>
    </div>
</div>
