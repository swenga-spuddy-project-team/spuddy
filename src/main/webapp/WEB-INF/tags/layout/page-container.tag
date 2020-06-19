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
        <a class="navbar-brand">Spuddy</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
        <c:choose>
            <c:when test="${activePage != 'signup'}">

                <a class="nav-item nav-link item1${pageContext.request.requestURI == '/WEB-INF/jsp/swipe.jsp' ? ' active' : ''}" href="/swipe">Swipe <span class="sr-only">(current)</span></a>
                <a class="nav-item nav-link item2${pageContext.request.requestURI == '/WEB-INF/jsp/chat.jsp' ? ' active' : ''}" href="/chat">Chat</a>
                <a class="nav-item nav-link item3${pageContext.request.requestURI == '/WEB-INF/jsp/home.jsp' ? ' active' : ''}" href="/home">Home</a>


                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <a class="nav-item nav-link item4${pageContext.request.requestURI == '/WEB-INF/jsp/admin.jsp' ? ' active' : ''}" href="/admin">manage Sport</a>
                    <a class="nav-item nav-link item5${pageContext.request.requestURI == '/WEB-INF/jsp/admin2.jsp' ? ' active' : ''}" href="/admin">manage District</a>
                    <a class="nav-item nav-link item6${pageContext.request.requestURI == '/WEB-INF/jsp/admin3.jsp' ? ' active' : ''}" href="/admin">manage Profiles</a>
                </sec:authorize>
            </c:when>
            <c:otherwise>
                <a class="nav-item nav-link active" href="/signup">Signup <span class="sr-only">(current)</span></a>
                <a class="nav-item nav-link" href="/login">Login</a>
            </c:otherwise>
        </c:choose>
        </div>
        </div>


        <c:if test="${activePage != 'signup'}">
        </div>
        </li>
        </ul>

        Logged in as ${currentUser.username} |
        <form:form method="post" action="/logout">
            <button class="btn btn-link" type="submit">Log Out</button>
        </form:form>
        </div>
        </c:if>
        </nav>


        ${pageContext.request.requestURI}





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
