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


<layout:page-container title="Admin Districts" activePage="adminListDistricts">


    <div class="col-md-10">
        <p>
            <a href="/adminEditDistrict" class="btn btn-success">Add Sport</a>
        </p>
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <table data-toggle="table" class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>District Name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${districts}" var="district">
                    <tr>
                        <td>${district.districtId}</td>
                        <td>${district.districtName}</td>
                        <td>
                            <a href="adminEditDistrict?districtId=${district.districtId}" type="submit" class="btn btn-xs btn-warning">Edit</a>

                        </td>
                        <td>
                            <form:form method="post" action="adminDeleteDistrict?districtId=${district.districtId}">
                                <button type="submit" class="btn btn-xs btn-danger">Delete</button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</layout:page-container>
