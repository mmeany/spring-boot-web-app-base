<%@ include file="/WEB-INF/tags/include-for-tags.jsp"%>
<%@ attribute name="label" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="placeholder" required="false"  %>
    
	<div class="form-group">
	  <div class="col-sm-offset-2 col-sm-3">
	    <div class="checkbox">
	      <label><form:checkbox path="${path}"/> ${label}</label>
	    </div>
	  </div>
	</div>
