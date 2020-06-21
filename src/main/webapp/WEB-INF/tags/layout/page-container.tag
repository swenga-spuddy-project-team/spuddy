    <%@ attribute name="activePage" required="true" %>
        <%@ attribute name="title" required="true" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
        <%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
        <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>
            <head>

            <bootstrap:bootstrap-metadata/>
            <title>${title}</title>
            <bootstrap:bootstrap-css/>
            <link rel="stylesheet" href="<c:url value="/css/custom.css"/>">
            <link href="/css/chat.css" rel="stylesheet" type="text/css">
            <script src="/js/custom.js"></script>




        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


            </head>
            <body>


        <nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light" >
        <a class="navbar-brand"><spring:message code="pageContainer.Header"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
        aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
        <c:choose>
            <c:when test="${activePage != 'signup' && activePage != 'login'}">

                <a class="nav-item nav-link
                item1${pageContext.request.requestURI == '/WEB-INF/jsp/swipe.jsp' ? ' active' : ''}" href="/swipe">Swipe
                <span class="sr-only"><spring:message code="pageContainer.Current"/></span></a>
                <a class="nav-item nav-link
                item2${pageContext.request.requestURI == '/WEB-INF/jsp/chatOverview.jsp' ? ' active' : ''}"
                href="/chatOverview"><spring:message code="pageContainer.Chats"/></a>
                <a class="nav-item nav-link
                item3${pageContext.request.requestURI == '/WEB-INF/jsp/home.jsp' ? ' active' : ''}"
                href="/home"><spring:message code="pageContainer.Home"/></a>


                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <a class="nav-item nav-link
                    item4${pageContext.request.requestURI == '/WEB-INF/jsp/adminListSports.jsp' ? ' active' : ''}"
                    href="/adminListSports"><spring:message code="pageContainer.ManageSports"/></a>
                    <a class="nav-item nav-link
                    item5${pageContext.request.requestURI == '/WEB-INF/jsp/adminListDistricts.jsp' ? ' active' : ''}"
                    href="/adminListDistricts"><spring:message code="pageContainer.ManageDistricts"/></a>
                    <a class="nav-item nav-link
                    item6${pageContext.request.requestURI == '/WEB-INF/jsp/adminListUsers.jsp' ? ' active' : ''}"
                    href="/adminListUsers"><spring:message code="pageContainer.ManageUsers"/></a>
                    <a class="nav-item nav-link
                    item6${pageContext.request.requestURI == '/WEB-INF/jsp/admin.jsp' ? ' active' : ''}"
                    href="/admin"><spring:message code="pageContainer.ManageSettings"/></a>
                </sec:authorize>
            </c:when>
            <c:otherwise>
                <a class="nav-item nav-link active" href="/signup"><spring:message code="pageContainer.Signup"/> <span class="sr-only"><spring:message code="pageContainer.Current"/></span></a>
                <a class="nav-item nav-link" href="/login"><spring:message code="pageContainer.Login"/></a>
            </c:otherwise>
        </c:choose>
        </div>
        </div>


        <c:if test="${activePage != 'signup' && activePage != 'login'}">
            </div>
            </li>
            </ul>

            <spring:message code="pageContainer.LoggedInAs"/> ${currentUser.username} |
            <form:form method="post" action="/logout">
                <button class="btn btn-link" type="submit"><spring:message code="pageContainer.Logout"/> </button>
            </form:form>
            </div>
        </c:if>
        </nav>

        <div class="container" role="main">


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
