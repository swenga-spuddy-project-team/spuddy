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


    <row>
        <div>
            <form:form method="post" action="/importDistrictData" enctype="multipart/form-data">
                <a href="/adminEditDistrict" class="btn btn-success"><spring:message code="adminListDistricts.AddDistrict"/></a>

                <button class="btn btn-success" type="submit"><spring:message code="adminListDistricts.importDistrictData"/></button>
                <input type="file" name="file"/>
            </form:form>
            <form:errors path="importDistrictData" cssClass="invalid-feedback d-block"/>
        </div>
    </row>


    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <table data-toggle="table" class="table table-striped">
                <thead>
                <tr>
                    <th><spring:message code="adminListDistricts.id"/></th>
                    <th><spring:message code="adminListDistricts.name"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${districts}" var="district">
                    <tr>
                        <td>${district.districtId}</td>
                        <td>${district.districtName}</td>
                        <td>
                            <a href="adminEditDistrict?districtId=${district.districtId}" type="submit" class="btn btn-xs btn-warning"><spring:message code="adminListDistricts.EditDistrict"/></a>

                        </td>
                        <td>
                            <form:form method="post" action="adminDeleteDistrict?districtId=${district.districtId}">
                                <button type="submit" class="btn btn-xs btn-danger"><spring:message code="adminListDistricts.DeleteDistrict"/></button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</layout:page-container>
