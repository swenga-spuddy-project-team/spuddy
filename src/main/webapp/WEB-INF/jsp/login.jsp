<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<layout:page-container title="Login" activePage="login">
    <h1>Login</h1>
    <form:form name='f' action="login" method='POST'>
        <row>
            <br>User:</br>
            <div class="col-md4">

                <td><input type='text' name='username' value=''></td>
            </div>
            <br>Password:</br>
            <div class="col-md4">

                <td><input type='password' name='password' /></td>
            </div>
            <div class="col-md4">
                <br>
                <button name="submit" type="submit" class="btn btn-primary">Login</button>
                <a href="/signup" class="btn btn-link">No Account? Sign Up!</a>
            </div>
        </row>
    </form:form>
</layout:page-container>