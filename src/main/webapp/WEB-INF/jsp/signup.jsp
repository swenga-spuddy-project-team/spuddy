<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:page-container title="SignUp" activePage="signup">

    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form:form modelAttribute="userdto" class="needs-validation form-horizontal" method="post" action="addUser"
                       novalidate="novalidate">
                <fieldset>
                    <legend><spring:message code="signup.header"/> </legend>

                    <! ---------------- user name ---------------- -->
                    <div class="form-group">
                        <label for="inputUsername" class="col-md-2 control-label"><spring:message code="adminEditUser.username"/></label>
                        <div class="col-md-10">
                            <form:input id="inputUsername" path="username" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="username" cssClass="invalid-feedback d-block"/>

                        </div>
                    </div>

                    <! ---------------- Password ---------------- -->
                    <! -- Second field + match check is still required -->
                    <div class="form-group">
                        <label for="inputPassword" class="col-md-2 control-label"><spring:message code="adminEditUser.password"/></label>
                        <div class="col-md-10">
                            <form:input id="inputPassword" path="password" type="password" class="form-control"
                                        required="required"/>
                            <form:errors path="password" cssClass="invalid-feedback d-block"/>

                        </div>
                    </div>
                    <! ---------------- Repeat Password ---------------- -->
                    <div class="form-group">
                        <label for="inputPassword" class="col-md-2 control-label"><spring:message code="adminEditUser.passwordRepeat"/></label>
                        <div class="col-md-10">
                            <form:input id="inputPasswordRepeat" path="passwordrepeat" type="password"
                                        class="form-control" required="required"/>
                            <form:errors path="passwordrepeat" cssClass="invalid-feedback d-block"/>

                        </div>
                    </div>

                    <! ---------------- first Name ---------------- -->
                    <div class="form-group">
                        <label for="inputFirstName" class="col-md-2 control-label"><spring:message code="adminEditUser.firstname"/></label>
                        <div class="col-md-10">
                            <form:input id="inputFirstName" path="firstName" type="text" class="form-control"
                                        required="required"/>
                            <form:errors path="firstName" cssClass="invalid-feedback d-block"/>

                        </div>
                    </div>

                    <! ---------------- last Name ---------------- -->
                    <div class="form-group">
                        <label for="inputLastName" class="col-md-2 control-label"><spring:message code="adminEditUser.lastname"/></label>
                        <div class="col-md-10">
                            <form:input id="inputLastName" path="lastName" type="text" class="form-control"
                                        required="required"/>
                            <form:errors path="lastName" cssClass="invalid-feedback d-block"/>
                        </div>
                    </div>

                    <! ---------------- dateOfBirth ---------------- -->
                    <div class="form-group">
                        <label for="inputDate" class="col-md-2 control-label"><spring:message code="adminEditUser.date"/></label>
                        <div class="col-md-10">
                            <form:input id="inputDate" path="dateOfBirth" type="date" class="form-control"
                                        required="required"/>
                            <form:errors path="dateOfBirth" cssClass="invalid-feedback d-block"/>
                        </div>
                    </div>

                    <! ---------------- District ---------------- -->
                    <div class="form-group">
                        <label for="inputDistrict" class="col-md-2 control-label"><spring:message code="adminEditUser.district"/></label>
                        <div class="col-md-10">
                            <form:select path="district" itemValue="district" itemLabel="district"
                                         cssClass="form-control">
                                <form:options items="${districtNames}"/>
                            </form:select>
                            <form:errors path="district" cssClass="invalid-feedback d-block"/>
                        </div>
                    </div>

                    <! ---------------- Gender ---------------- -->
                    <div>
                        <input type="radio" id="male" name="gender" value="${userdto.gender = "MALE"}">
                        <label for="male"><spring:message code="adminEditUser.genderMale"/></label>
                        <input type="radio" id="female" name="gender" value="${userdto.gender = "FEMALE"}">
                        <label for="female"><spring:message code="adminEditUser.genderFemale"/></label>
                        <input type="radio" id="other" name="gender" value="${userdto.gender = "OTHER"}">
                        <label for="other"><spring:message code="adminEditUser.genderOther"/> </label><br>
                        <form:errors path="gender" cssClass="invalid-feedback d-block"/>
                    </div>

                    <! ---------------- IsTeam ---------------- -->
                    <div>
                        <input type="checkbox" id="isTeam" name="isTeam" value="${userdto.isTeam()}">
                        <label for="isTeam"><spring:message code="signup.isTeam"/> </label><br>
                    </div>

                    <! ---------------- EMail ---------------- -->
                    <div class="form-group">
                        <label for="inputEmail" class="col-md-2 control-label"><spring:message code="adminEditUser.email"/></label>
                        <div class="col-md-10">
                            <form:input id="inputEmail" path="email" type="email" class="form-control"
                                        required="required"/>
                            <form:errors path="email" cssClass="invalid-feedback d-block"/>
                        </div>
                    </div>

                    <! ---------------- buttons ---------------- -->
                    <div class="form-group">
                        <div class="col-md-10 col-md-offset-2">
                            <button type="submit" class="btn btn-primary"><spring:message code="adminEditUser.confirm"/></button>
                            <a href="login" class="btn btn-default"><spring:message code="adminEditUser.cancel"/></a>
                        </div>
                    </div>

                </fieldset>
            </form:form>
        </div>
    </div>

</layout:page-container>
