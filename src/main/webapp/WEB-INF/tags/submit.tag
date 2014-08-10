<%@ include file="/WEB-INF/tags/include-for-tags.jsp"%>
<%@ attribute name="submitText" required="true" %>
<%@ attribute name="cancelText" required="true" %>

	<div class="form-group">
      <div class="col-sm-offset-2 col-sm-3">
        <button type="submit" class="btn btn-default">${submitText}</button>
        <button type="reset" class="btn">${cancelText}</button>
      </div>
    </div>
	