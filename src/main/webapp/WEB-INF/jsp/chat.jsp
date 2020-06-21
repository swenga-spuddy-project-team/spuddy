<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<layout:page-container title="Admin" activePage="admin">



    <section>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="chat-list-box">
                        <div class="head-box">
                            <ul class="list-inline text-left d-inline-block float-left">
                                <li> <img src="${activechatpartner}" alt="picture active chat" width="40px"> </li>
                            </ul>
                            <ul class="flat-icon list-inline text-right d-inline-block float-right">
                                <li> <a href="#"> <i class="fas fa-search"></i> </a> </li>
                                <li> <a href="#"> <i class="fas fa-ellipsis-v"></i> </a> </li>
                            </ul>
                        </div>

                        <div class="chat-person-list">
                            <ul class="list-inline">



                                    <li> <a href="#" class="flip"> <img src="123" alt="userbild"> <span> 123</span>  </a> </li>
<%---
                                <c:forEach var="user" items="${listUsers.rows}">

                                    <li><c:out value="${userdto.}" /></li>
                                    <li> <a href="#" class="flip"> <img src="${userdto.img}" alt="userbild"> <span> ${userdto.username}</span>  </a> </li>

                                </c:forEach>
-----%>

                            </ul>
                        </div>

                    </div>
                </div> <!-- col-md-4 closed -->

                <div class="col-md-8">
                    <div class="message-box">
                        <div class="head-box-1">

                            <ul class="msg-box list-inline text-left d-inline-block float-left">
                                <li> <i class="fas fa-arrow-left" id="back"></i> </li>
                                <li> <img src="https://i.ibb.co/fCzfFJw/person.jpg" alt="" width="40px"> <span> Naveen mandwariya </span> <br> <small class="timee"> 12:45 Pm </small> </li>
                            </ul>



                        </div>

                        <div class="msg_history">

                                <%---
                                    <c:forEach var="user" items="${listmsgUser.msg}">



                                        <c:choose>
                                            <c:when test="userdto.idAccount == msg.account_sender">
                                                <div class="outgoing_msg">
                                                    <div class="sent_msg">
                                                        <p>msg.content</p>
                                                        <span class="time_date">${msg.timestamp}</span>
                                                     </div>
                                                </div>
                                            </c:when>

                                           <c:when test="userdto.idAccount != msg.account_sender">
                                               <div class="incoming_msg">
                                                   <div class="received_msg">
                                                    <div class="received_withd_msg">
                                                        <p>${msg.content}</p>
                                                        <span class="time_date"> ${msg.timestamp}</span>
                                                     </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                        </c:choose>
                            </c:forEach>
                             -----%>

</div>

                        <div class="incoming_msg">
                            <div class="received_msg">
                                <div class="received_withd_msg">
                                    <p>I am also good thankyou!</p>
                                    <span class="time_date"> 11:01 AM    |    Yesterday</span></div>
                            </div>
                        </div>
                            <div class="outgoing_msg">
                                <div class="sent_msg">
                                    <p> ok </p>
                                    <span class="time_date"> 11:01 AM    |    Today</span> </div>
                            </div>
                            <div class="incoming_msg">
                                <div class="received_msg">
                                    <div class="received_withd_msg">
                                        <p> What's going on ?</p>
                                        <span class="time_date"> 11:01 AM    |    Today</span></div>
                                </div>
                            </div>
                        </div>

                        <div class="send-message">
                            <form action="" method="">
                                <textarea cols="10" rows="2" class="form-control" placeholder="Type your message here ..."> </textarea>
                                <ul class="list-inline">
                                    <li>
                                        <a href="#" id="attach">  <i class="fas fa-paperclip"></i> </a>
                                        <div class="attachement">
                                            <ul class="list-inline">
                                                <li> <a href="#"> <i class="fas fa-file"></i> </a> </li>
                                                <li> <a href="#"> <i class="fas fa-camera"></i> </a> </li>
                                                <li> <a href="#"> <i class="fas fa-image"></i> </a> </li>
                                                <li> <a href="#"> <i class="far fa-play-circle"></i> </a> </li>
                                                <li> <a href="#"> <i class="fas fa-map-marker-alt"></i> </a> </li>
                                                <li> <a href="#"> <i class="fas fa-id-card"></i> </a> </li>
                                            </ul>
                                        </div>
                                    </li>
                                    <li> <i class="fas fa-paper-plane"></i> </li>
                                </ul>
                            </form>
                        </div>


                    </div>
                </div>

            </div>
        </div>
    </section>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>

    <script>
        $("#attach").click(function(){
            $(".attachement").toggle();
        });
    </script>
    <script>
        $("#dset").click(function(){
            $(".setting-drop").toggle('1000');
        });
    </script>

    <script>
        $(document).ready(function(){
            $(".flip").click(function(){
                $(".message-box").show("slide", { direction: "right" }, 10000);
            });
        });
    </script>
    <script>
        $(document).ready(function(){
            $("#back").click(function(){
                $(".message-box").hide("slide", { direction: "left" }, 10000);
            });
        });
    </script>



    </layout:page-container>

