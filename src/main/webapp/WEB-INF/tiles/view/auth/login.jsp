<%@ include file="/WEB-INF/tiles/include-for-forms.jsp"%>

    <form id="login-form" class="form-horizontal" method="POST" action="<c:url value="/login" />">
      <fieldset>
        <legend>Sign in <small>to access secure areas of the site.</small></legend>
	    <c:if test="${param.logout != null}">
	        <p class="text-info">You have been signed out.</p>
	    </c:if>
        <c:if test="${not empty param.error}">
            <p class="text-danger">Login error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} - ${param.error}</p>
        </c:if>

       <div class="form-group">
          <label for="inputUsername" class="col-sm-2 control-label">Username</label>
          <div class="col-sm-3">
            <input id="inputUsername" name="username" placeholder="Username">
          </div>
       </div>

       <div class="form-group">
          <label for="inputPassword" class="col-sm-2 control-label">Password</label>
          <div class="col-sm-3">
                <input type="password" id="inputPassword" name="password" placeholder="Password">
          </div>
       </div>

		<div class="form-group">
		   <div class="col-sm-offset-2 col-sm-3">
		     <div class="checkbox">
		       <label>
		         <input type="checkbox" name="_spring_security_remember_me"> Remember me
		       </label>
		     </div>
		   </div>
		 </div>

		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Sign in</button>
            <button type="reset" class="btn">Cancel</button>
		  </div>
		</div>

      </fieldset>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <p>Forgotten password? Request a <a href="<c:url value="/account/password_reset"/>">password reset <i class="icon-share-alt"></i></a>.</p>
