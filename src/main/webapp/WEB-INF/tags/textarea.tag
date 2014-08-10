<%@ include file="/WEB-INF/tags/include-for-tags.jsp"%>
<%@ attribute name="label" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="placeholder" required="false"  %>
<%@ attribute name="rows" required="true" %>
<%@ attribute name="cols" required="true" %>

    <c:set var="mvmInputClass" value="control-group" />
    <c:set var="domainNameErrors"><form:errors path="${path}"/></c:set>
    <c:if test="${not empty domainNameErrors}">
        <c:set var="mvmInputClass" value="control-group error" />
    </c:if>
    <div class="${mvmInputClass}">
        <label class="control-label" for="${id}">${label}</label>
        <div class="controls">
            <form:textarea id="${id}" path="${path}" rows="${rows}" cols="${cols}" placeholder="${placeholder}"/>
            <c:if test="${not empty domainNameErrors}"><span class="help-inline">${domainNameErrors}</span></c:if>
        </div>
    </div>
