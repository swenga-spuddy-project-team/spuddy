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

<layout:page-container title="Admin District" activePage="adminEditDistrict">

    <c:choose>
        <c:when test="${district.districtId != null}">
            <c:set var="districtIdActive"><spring:message code="adminEditDistrict.Header"/> ${district.districtId}</c:set>
        </c:when>
        <c:otherwise><c:set var="districtIdActive"><spring:message code="adminEditDistrict.NewDistrict"/></c:set></c:otherwise>
    </c:choose>

    <div class="col-md-4">
        <h2>${districtIdActive}</h2>
    </div>

    <%--@elvariable id="sport" type="kotlin"--%>
    <form:form modelAttribute="district" class="needs-validation form-horizontal" method="post" action="adminChangeDistrict"
               novalidate="novalidate">
        <fieldset>
            <input type="hidden" name="districtId" value="<c:out value="${district.districtId}"/>">
            <div class="form-group">
                <label for="inputDistrictName" class="col-md-6 control-label"><spring:message code="adminEditDistrict.DistrictName"/></label>
                <div class="col-md-6">
                    <form:input id="inputDistrictName" path="districtName" type="text" class="form-control"
                                required="required"/>
                    <form:errors path="districtName" cssClass="invalid-feedback d-block"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-10 col-md-offset-2">
                    <button type="submit" class="btn btn-primary"><spring:message code="adminEditUser.confirm"/></button>
                    <a href="adminListDistricts" class="btn btn-default"><spring:message code="adminEditUser.cancel"/></a>
                </div>
            </div>
        </fieldset>
    </form:form>

</layout:page-container>