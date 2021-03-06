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


<layout:page-container title="Admin Users" activePage="adminListUsers">


    <div>
            <a href="/adminEditUser" class="btn btn-success"><spring:message code="adminListUsers.AddUser"/></a>
    </div>

    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <table data-toggle="table" class="table table-striped">
                <thead>
                <tr>
                    <th><spring:message code="adminListUsers.id"/></th>
                    <th><spring:message code="adminListUsers.username"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userdtos}" var="userdto">
                    <tr>
                        <td>${userdto.id}</td>
                        <td>${userdto.username}</td>
                        <td>
                            <a href="adminEditUser?id=${userdto.id}" type="submit"
                               class="btn btn-xs btn-warning"><spring:message code="adminListUsers.EditUser"/></a>

                        </td>
                        <td>
                            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                                <form:form method="post" action="adminDeleteUser?id=${userdto.id}">
                                    <button type="submit" class="btn btn-xs btn-danger"><spring:message code="adminListUsers.DeleteUser"/></button>
                                </form:form>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</layout:page-container>
