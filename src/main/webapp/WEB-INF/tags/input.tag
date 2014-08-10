<%@ include file="/WEB-INF/tags/include-for-tags.jsp"%>
<%@ attribute name="label" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="placeholder" required="false"  %>
<%@ attribute name="maxlength" required="true" %>

    <c:set var="mvmInputClass" value="" />
    <c:set var="domainNameErrors"><form:errors path="${path}"/></c:set>
    <c:if test="${not empty domainNameErrors}">
        <c:set var="mvmInputClass" value="has-error" />
    </c:if>
    
    <div class="form-group ${mvmInputClass}">
      <label for="${id}" class="col-sm-2 control-label">${label}</label>
      <div class="col-sm-3">
        <form:input type="${type}" id="${id}" path="${path}" placeholder="${placeholder}" maxlength="${maxlength}"/>
        <c:if test="${not empty domainNameErrors}"><span class="help-inline">${domainNameErrors}</span></c:if>
      </div>
    </div>
