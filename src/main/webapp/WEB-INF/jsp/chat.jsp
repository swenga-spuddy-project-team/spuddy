<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.io.*,java.util.*" %>
<layout:page-container title="Chats" activePage="chat">


    <div>

        <c:forEach items="${messagelist}" var="message">


            <c:choose>

            <c:when test = "currentUser = mesage.senderId">
                <div class="container">
                    <img src="${currentUser.profilePicturePath}" alt="picture User">
                    <p>${message.content}</p>
                    <span class="time-right">${message.timestamp}</span>
                </div>
            </c:when>

                <c:when test = "currentUser != mesage.senderId">
                    <div class="container darker">
                        <img src="${currentUser.profilePicturePath}" alt="Avatar" class="right">
                        <p>message.content</p>
                        <span class="time-left">${message.timestamp}</span>
                    </div>
                </c:when>

            </c:choose>




        </c:forEach>

        <div class="container darker">
            <img src="/w3images/avatar_g2.jpg" alt="Avatar" class="right">
            <p>Hey! I'm fine. Thanks for asking!</p>
            <span class="time-left">11:01</span>
        </div>

        <div class="container">
            <img src="/w3images/bandmember.jpg" alt="Avatar">
            <p>Sweet! So, what do you wanna do today?</p>
            <span class="time-right">11:02</span>
        </div>

        <div class="container darker">
            <img src="/w3images/avatar_g2.jpg" alt="Avatar" class="right">
            <p>Nah, I dunno. Play soccer.. or learn more coding perhaps?</p>
            <span class="time-left">11:05</span>
        </div>

        <div class="container darker">
            <img src="/w3images/avatar_g2.jpg" alt="Avatar" class="right">
            <p>Hey! I'm fine. Thanks for asking!</p>
            <span class="time-left">11:01</span>
        </div>

        <div class="container">
            <img src="/w3images/bandmember.jpg" alt="Avatar">
            <p>Sweet! So, what do you wanna do today?</p>
            <span class="time-right">11:02</span>
        </div>

        <div class="container darker">
            <img src="/w3images/avatar_g2.jpg" alt="Avatar" class="right">
            <p>Nah, I dunno. Play soccer.. or learn more coding perhaps?</p>
            <span class="time-left">11:05</span>
        </div>

        <div class="container darker">
            <img src="/w3images/avatar_g2.jpg" alt="Avatar" class="right">
            <p>Hey! I'm fine. Thanks for asking!</p>
            <span class="time-left">11:01</span>
        </div>

        <div class="container">
            <img src="/w3images/bandmember.jpg" alt="Avatar">
            <p>Sweet! So, what do you wanna do today?</p>
            <span class="time-right">11:02</span>
        </div>

        <div class="container darker">
            <img src="/w3images/avatar_g2.jpg" alt="Avatar" class="right">
            <p>Nah, I dunno. Play soccer.. or learn more coding perhaps?</p>
            <span class="time-left">11:05</span>
        </div>

    </div>


    <form:form modelAttribute="currentUser" class="needs-validation form-horizontal" method="post" action="sendMessage"
               novalidate="novalidate">


        <form:textarea id="inputEmail" path="email" cols="40" rows="4" class="form-control"/>
        <form:errors path="email" />

        <div class="form-group row">

                <button name="submit" type="submit" class="btn btn-primary btn-lg btn-block">Send Message</button>
        </div>

    </form:form>

</layout:page-container>