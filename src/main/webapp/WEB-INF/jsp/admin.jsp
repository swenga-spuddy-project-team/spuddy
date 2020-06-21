<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="file" tagdir="/WEB-INF/tags/file" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:page-container title="Admin" activePage="admin">

    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <form:form method="get" action="/generateTestData">
                <button class="btn btn-success" type="submit"><spring:message code="admin.generateTestData"/> </button>
            </form:form>
            <form:errors path="generateTestData" cssClass="invalid-feedback d-block"/>
        </div>
    </div>




</layout:page-container>