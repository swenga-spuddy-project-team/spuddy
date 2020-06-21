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
    <div class="container" modelAttribute="nextUser">
        <div class="row">
            <div class="col">
                <a href="/swipeDislike?userDislikeId=${nextUser.id}" type ="dislike" class="btn btn-danger">DISLIKE</a>
            </div>
            <div class="col-6">
                <div class="container" id="randomUser" value="<c:out value="${swipe.id}"/>">
                    <div>
                        <img src="${nextUser.profilePicturePath}" class="img-fluid" alt="Responsive image">
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-sm">
                                ${nextUser.district}
                            </div>
                            <div class="col-sm">
                                    ${nextUser.username}
                            </div>
                            <div class="col-sm">

                            </div>
                        </div>
                    </div>
                    <div>
                        User INFO
                    </div>
                </div>
            </div>
            <div class="col">
                <a href="/swipeLike?userLikeId=${nextUser.id}" type ="like" class="btn btn-success">LIKE</a>
            </div>
    </div>
</layout:page-container>