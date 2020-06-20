<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<layout:page-container title="swipe" activePage="swipe">
    <h1>Spuddy </br> Let's swipe your Sport Buddy</h1>
    <div class="container">
        <div class="row">
            <div class="col">
                <button type="button" class="btn btn-danger">DISLIKE</button>
            </div>
            <div class="col-6">
                <div class="container" id="randomUser">
                    <div>
                        <img src="..." class="img-fluid" alt="Responsive image">
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-sm">
                                District
                            </div>
                            <div class="col-sm">
                                Username
                            </div>
                            <div class="col-sm">
                                Age
                            </div>
                        </div>
                    </div>
                    <div>
                        User INFO
                    </div>
                </div>
            </div>
            <div class="col">
                <button type="button" class="btn btn-success">LIKE</button>
            </div>
    </div>
</layout:page-container>