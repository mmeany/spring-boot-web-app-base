<%@ include file="/WEB-INF/tiles/include-for-forms.jsp"%>

    <form:form id="user-details-form" class="form-horizontal" method="POST" commandName="userForm" >
        <fieldset>
            <legend>User details</legend>
            <div class="row">
                <div class="col-md-9">
                    <c:if test="${not empty param.error}">
                        <p class="text-error">Please correct the errors below!</p>
                    </c:if>

                    <mvm:input label="Name" type="text" id="inputName" path="name" placeholder="Name" maxlength="80"/>
                    <mvm:input label="Username" type="text" id="inputUsername" path="username" placeholder="Username" maxlength="20"/>
                    <mvm:input label="Pasword" type="password" id="inputPassword" path="password" placeholder="Password" maxlength="20"/>
                    <mvm:input label="Confirm" type="password" id="inputConfirm" path="confirm" placeholder="Confirm" maxlength="20"/>
                    <mvm:boolean label="Enabled" path="enabled"/>
                    <mvm:boolean label="Administrator" path="administrator"/>
                    <mvm:submit submitText="Submit" cancelText="Cancel"/>
                </div>
            </div>
        </fieldset>
    </form:form>
