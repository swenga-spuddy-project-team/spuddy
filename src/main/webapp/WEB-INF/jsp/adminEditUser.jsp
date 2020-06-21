<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:page-container title="Admin Users" activePage="adminEditUser">

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>




<c:if test= "${userdto.username != ''}">
    <c:set var="notEditable">true</c:set>
</c:if>



<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Your Profile</h4>
                            <div class="avatar-wrapper">
                                <img src="${userdto.profilePicturePath}" />
                                <div class="upload-button">
                                    <i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
                                </div>

                            </div>

                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form:form modelAttribute="userdto" class="needs-validation form-horizontal" method="post" action="adminAddUser"
                                       novalidate="novalidate" enctype="multipart/form-data">
                                <! ---------------- user name ---------------- -->
                                <div class="form-group row">
                                    <label for="username" class="col-4 col-form-label">User Name</label>
                                    <div class="col-8">
                                        <form:input id="inputUsername" path="username" type="text" class="form-control"
                                                    required="required" value="${userdto.username}" readonly= "${notEditable}"/>
                                        <form:errors path="username" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>
                                <! ---------------- upload profile picture ------>
                                <div class="form-group row">
                                    <label for="profilePicture" class="col-4 col-form-label">Upload a picture</label>
                                    <div class="col-8">
                                        <input class="file-upload" type="file" name="file" accept="image/*"/>
                                    </div>
                                </div>
                                <! ---------------- first name ---------------- -->

                                <div class="form-group row">
                                    <label for="inputFirstName" class="col-4 col-form-label">First Name</label>
                                    <div class="col-8">
                                        <form:input id="inputFirstName" path="firstName" type="text" class="form-control"
                                                    required="required" value="${userdto.firstName}" readonly= "${notEditable}"/>
                                        <form:errors path="firstName" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>
                                <! ---------------- lastname ---------------- -->
                                <div class="form-group row">
                                    <label for="inputLastName" class="col-4 col-form-label">Last Name</label>
                                    <div class="col-8">
                                        <form:input id="inputLastName" path="lastName" type="text" class="form-control"
                                                    required="required"  value="${userdto.lastName}" readonly= "${notEditable}" />
                                        <form:errors path="lastName" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>

                                <! ---------------- dateOfBirth ---------------- -->
                                <div class="form-group row">
                                    <label for="inputDate" class="col-4 col-form-label">Date</label>
                                    <div class="col-8">
                                        <form:input id="inputDate" path="dateOfBirth" type="date" class="form-control"
                                                    required="required" value="${userdto.dateOfBirth}" readonly= "${notEditable}" />
                                        <form:errors path="dateOfBirth" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>

                                <! ---------------- Gender ---------------- -->
                                <div class="form-group row">
                                    <label for="male" class="col-4 col-form-label">Gender</label>
                                    <div class="col-8">
                                        <input type="radio" id="male" name="gender" value="${userdto.gender = "MALE"}" readonly= "${notEditable}">
                                        <label for="male">Male</label>
                                        <input type="radio" id="female" name="gender" value="${userdto.gender = "FEMALE"}" readonly= "${notEditable}">
                                        <label for="female">Female</label>
                                        <input type="radio" id="other" name="gender" value="${userdto.gender = "OTHER"}" readonly= "${notEditable}">
                                        <label for="other">Other</label><br>
                                        <form:errors path="gender" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>

                                <! ---------------- District ---------------- -->

                                <div class="form-group row">
                                    <label for="select" class="col-4 col-form-label">District</label>
                                    <div class="col-8">
                                        <form:select path="district" itemValue="district" itemLabel="district" readonly= "${notEditable}"
                                                     cssClass="form-control">
                                            <form:options items="${districtNames}"/>
                                        </form:select>
                                        <form:errors path="district" cssClass="invalid-feedback d-block"/>
                                    </div>
                                    <! ---------------- email ---------------- -->
                                </div>
                                <div class="form-group row">
                                    <label for="email" class="col-4 col-form-label">Email</label>
                                    <div class="col-8">
                                        <form:input id="inputEmail" path="email" type="email" class="form-control" readonly= "${notEditable}"
                                                    required="required" value="${userdto.email}"/>
                                        <form:errors path="email" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>
                                <! ---------------- description ---------------- -->
                                <div class="form-group row">
                                    <label for="ProfileDescription" class="col-4 col-form-label">ProfileDescription</label>
                                    <div class="col-8">
                                        <textarea id="ProfileDescription" name="ProfileDescription" cols="40" rows="4" class="form-control"></textarea>
                                    </div>
                                </div>
                                <! ---------------- new password ---------------- -->

                                <div class="form-group row">
                                    <label for="inputPassword" class="col-4 col-form-label">New Password</label>
                                    <div class="col-8">
                                        <form:input id="inputPassword" path="password" type="password" class="form-control"/>
                                        <form:errors path="password" cssClass="invalid-feedback d-block" readonly= "${notEditable}" />
                                    </div>
                                </div>
                                <! ---------------- Repeat Password ---------------- -->


                                <div class="form-group row">
                                    <label for="inputPassword" class="col-4 col-form-label">New Password</label>
                                    <div class="col-8">
                                        <form:input id="inputPasswordRepeat" path="passwordrepeat" type="password" class="form-control"/>
                                        <form:errors path="passwordrepeat" cssClass="invalid-feedback d-block" readonly= "${notEditable}" />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="offset-4 col-8">
                                        <c:if test= "${userdto.username == ''}">
                                            <button name="submit" type="submit" class="btn btn-primary">Update Profile</button>
                                        </c:if>
                                        <a href="/adminListUsers" class="btn btn-default">Cancel</a>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


</layout:page-container>