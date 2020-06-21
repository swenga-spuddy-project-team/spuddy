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

<layout:page-container title="Admin Sports" activePage="adminEditSport">

    <c:choose>
        <c:when test="${sport.sportId != null}">
            <c:set var="sportIdActive"><spring:message code="adminEditSport.Header"/> ${sport.sportId}</c:set>
        </c:when>
        <c:otherwise><c:set var="sportIdActive"><spring:message code="adminEditSport.NewSport"/> </c:set></c:otherwise>
    </c:choose>

    <div class="col-md-4">
        <h2>${sportIdActive}</h2>
    </div>

    <%--@elvariable id="sport" type="kotlin"--%>
    <form:form modelAttribute="sport" class="needs-validation form-horizontal" method="post" action="adminChangeSport"
               novalidate="novalidate">
        <fieldset>
            <input type="hidden" name="sportId" value="<c:out value="${sport.sportId}"/>">
            <div class="form-group">
                <label for="inputSportName" class="col-md-6 control-label"><spring:message code="adminEditSport.SportName"/></label>
                <div class="col-md-6">
                    <form:input id="inputSportName" path="name" type="text" class="form-control"
                                required="required"/>
                    <form:errors path="name" cssClass="invalid-feedback d-block"/>
                </div>
            </div>

            <div class="form-group">
                <label for="inputSportDescription" class="col-md-6 control-label"><spring:message code="adminEditSport.Description"/></label>
                <div class="col-md-6">
                    <form:input id="inputSportDescription" path="description" type="text" class="form-control"/>
                    <form:errors path="description" cssClass="invalid-feedback d-block"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-10 col-md-offset-2">
                    <button type="submit" class="btn btn-primary"><spring:message code="adminEditUser.confirm"/></button>
                    <a href="adminListSports" class="btn btn-default"><spring:message code="adminEditUser.cancel"/></a>
                </div>
            </div>
        </fieldset>
    </form:form>

</layout:page-container>