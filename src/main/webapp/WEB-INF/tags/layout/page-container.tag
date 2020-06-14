    <%@ attribute name="activePage" required="true" %>
        <%@ attribute name="title" required="true" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
        <%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
        <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
        <bootstrap:bootstrap-metadata/>
        <title>${title}</title>
        <bootstrap:bootstrap-css/>
        <link rel="stylesheet" href="<c:url value="/css/custom.css"/>">
        </head>
        <body>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
        <a class="navbar-brand" href="/">EmployeeManager</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        <!-- Code below uses the "activePage" to set menus active for specific pages -->
        <c:if test="${activePage == 'listEmployees' or activePage == 'editEmployee'}">
            <c:set var="employeesMenuActive">active</c:set>
        </c:if>
        <li class="nav-item ${employeesMenuActive} dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
        aria-haspopup="true" aria-expanded="false">
        Employee
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <a class="dropdown-item" href="/listEmployees">List Employee</a>
        <sec:authorize access="hasAuthority('ROLE_ADMIN')">
            <a class="dropdown-item" href="/editEmployee">Create Employee</a>
        </sec:authorize>
        </div>
        </li>
        </ul>
        Logged in as ${currentUser.username} |
        <form:form method="post" action="/logout">
            <button class="btn btn-link" type="submit">Log Out</button>
        </form:form>
        </div>
        </div>
        </nav>

        <div class="container" role="main">
        <div class="page-header">
        <h1>Employee Management</h1>
        </div>

        <!-- Messages ----------------------------------------------------------- -->

        <!-- Error message ----------------------------------------------------------- -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">${errorMessage}</div>
        </c:if>
        <!-- Error message ----------------------------------------------------------- -->

        <!-- Warning message ----------------------------------------------------------- -->
        <c:if test="${not empty warningMessage}">
            <div class="alert alert-warning" role="warning">${warningMessage}</div>
        </c:if>
        <!-- Warning message ----------------------------------------------------------- -->

        <!-- successful message ----------------------------------------------------------- -->
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="warning">${message}</div>
        </c:if>
        <!-- successful message ----------------------------------------------------------- -->

        <!-- Messages ----------------------------------------------------------- -->
        <!-- JSP DoBody ist die Stelle an der HTML von JSPs wie zB die Tabelle von listEmployees gepackt werden -->
        <jsp:doBody/>
        </div>
        <bootstrap:bootstrap-js/>
        <script type="text/javascript" src="<c:url value="/js/custom.js"/>"></script>
        </body>
        </html>
