<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<layout:page-container title="Home" activePage="home">






    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12">
                                <h4>Your Profile</h4>
                                <div class="avatar-wrapper">
                                    <img src="${currentUser.profilePicturePath}" />
                                    <div class="upload-button">
                                        <i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
                                    </div>
                                </div>

                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                            <form:form modelAttribute="currentUser" class="needs-validation form-horizontal" method="post" action="updateUser"
                                       novalidate="novalidate">
                                <! ---------------- user name ---------------- -->
                                <div class="form-group row">
                                    <label for="username" class="col-4 col-form-label">User Name</label>
                                    <div class="col-8">
                                        <form:input id="inputUsername" path="username" type="text" class="form-control"
                                                    required="required" value="${currentUser.username}" readonly="true"/>
                                        <form:errors path="username" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>

                                <! ---------------- update Profile picture ---------------- -->
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
                                                    required="required" value="${currentUser.firstName}"/>
                                        <form:errors path="firstName" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>
                                <! ---------------- lastname ---------------- -->
                                <div class="form-group row">
                                    <label for="inputLastName" class="col-4 col-form-label">Last Name</label>
                                    <div class="col-8">
                                        <form:input id="inputLastName" path="lastName" type="text" class="form-control"
                                                    required="required"  value="${currentUser.lastName}"/>
                                        <form:errors path="lastName" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>

                                    <! ---------------- dateOfBirth ---------------- -->
                                    <div class="form-group row">
                                        <label for="inputDate" class="col-4 col-form-label">Date</label>
                                        <div class="col-8">
                                            <form:input id="inputDate" path="dateOfBirth" type="date" class="form-control"
                                                        required="required" value="${currentUser.dateOfBirth}"/>
                                            <form:errors path="dateOfBirth" cssClass="invalid-feedback d-block"/>
                                        </div>
                                    </div>

                                    <! ---------------- Gender ---------------- -->
                                <div class="form-group row">
                                    <label for="male" class="col-4 col-form-label">Gender</label>
                                    <div class="col-4 ">
                                        <c:choose>
                                            <c:when test="${currentUser.gender == 'MALE'}" >
                                                <c:set var="selectedGenderMale">checked</c:set>
                                                <c:set var="selectedGenderFemale"></c:set>
                                                <c:set var="selectedGenderOther"></c:set>
                                            </c:when>
                                            <c:when test="${currentUser.gender == 'FEMALE'}" >
                                                <c:set var="selectedGenderMale"></c:set>
                                                <c:set var="selectedGenderFemale">checked</c:set>
                                                <c:set var="selectedGenderOther"></c:set>
                                            </c:when>
                                            <c:when test="${currentUser.gender == 'OTHER'}" >
                                                <c:set var="selectedGenderMale"></c:set>
                                                <c:set var="selectedGenderFemale"></c:set>
                                                <c:set var="selectedGenderOther">checked</c:set>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="selectedGenderMale"></c:set>
                                                <c:set var="selectedGenderFemale"></c:set>
                                                <c:set var="selectedGenderOther"></c:set>
                                            </c:otherwise>
                                        </c:choose>
                                        <input type="radio" id="male" name="gender" ${selectedGenderMale} value="${currentUser.gender = "MALE"}">
                                        <label for="male">Male</label>
                                        <input type="radio" id="female" name="gender" ${selectedGenderFemale} value="${currentUser.gender = "FEMALE"}">
                                        <label for="female">Female</label>
                                        <input type="radio" id="other" name="gender" ${selectedGenderOther} value="${currentUser.gender = "OTHER"}">
                                        <label for="other">Other</label><br>
                                        <form:errors path="gender" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>

                                    <! ---------------- District ---------------- -->

                                <div class="form-group row">
                                    <label for="select" class="col-4 col-form-label">District</label>
                                    <div class="col-8">
                                        <form:select path="district" itemValue="district" itemLabel="district"
                                                     cssClass="form-control">
                                            <form:options items="${districtNames}"/>
                                        </form:select>
                                        <form:errors path="district" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>

                                    <! ---------------- Sports ---------------- -->
                                    <div class="form-group row">
                                        <label for="select" class="col-4 col-form-label">Sports</label>
                                        <div class="col-8">
                                            <form:select path="sport" items="${sports}" itemValue="sportId" itemLabel="name"
                                                         cssClass="form-control" />
                                            <form:errors path="sport" cssClass="invalid-feedback d-block"/>
                                        </div>
                                    </div>

                                <! ---------------- Sports ---------------- -->
                                <div class="form-group row">
                                    <label for="select" class="col-4 col-form-label">Sports</label>
                                    <div class="col-8">
                                        <form:select path="sport" items="${currentUser.sport}" itemValue="sportId" itemLabel="name"
                                                     cssClass="form-control" disabled="true" />
                                        <form:errors path="sport" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>


                                    <! ---------------- email ---------------- -->

                                <div class="form-group row">
                                    <label for="email" class="col-4 col-form-label">Email</label>
                                    <div class="col-8">
                                        <form:input id="inputEmail" path="email" type="email" class="form-control"
                                                    required="required" value="${currentUser.email}"/>
                                        <form:errors path="email" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>
                                    <! ---------------- description ---------------- -->
                                <div class="form-group row">
                                    <label for="inputDescriptionText" class="col-4 col-form-label">ProfileDescription</label>
                                    <div class="col-8">
                                        <form:textarea id="inputDescriptionText" path="descriptionText" cols="40" rows="4" class="form-control"/>
                                        <form:errors path="descriptionText" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>
                                    <! ---------------- new password ---------------- -->

                                <div class="form-group row">
                                    <label for="inputPassword" class="col-4 col-form-label">New Password</label>
                                    <div class="col-8">
                                        <form:input id="inputPassword" path="password" type="password" class="form-control"/>
                                        <form:errors path="password" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>
                                    <! ---------------- Repeat Password ---------------- -->


                                <div class="form-group row">
                                    <label for="inputPassword" class="col-4 col-form-label">Repeat Password</label>
                                    <div class="col-8">
                                        <form:input id="inputPasswordRepeat" path="passwordrepeat" type="password" class="form-control"/>
                                        <form:errors path="passwordrepeat" cssClass="invalid-feedback d-block"/>
                                    </div>
                                </div>




                                <div class="form-group row">
                                    <div class="offset-4 col-8">
                                        <button name="submit" type="submit" class="btn btn-primary">Update Profile</button>
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

