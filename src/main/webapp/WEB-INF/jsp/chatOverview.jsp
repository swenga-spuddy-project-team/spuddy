<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<layout:page-container title="Chats" activePage="chat">

    <div class="row">
        <div class="col-md-10 col-md-offset-1 </div>">

            <table data-toggle="table" class="table table-striped">
                <thead>
                <tr>

                    <th>Username</th>
                </tr>
                </thead>
                <tbody>


                <tr>

                    <td><img src="${activechatpartner}" alt="picture active chat" width="40px"></td>
                    <td>herbert</td>
                    <td>
                        <a href="chat?id=${userdto.id}" type="submit"
                           class="btn btn-xl btn-link">Chat with me</a>

                    </td>

                </tr>


                <c:forEach items="${matchesList.}" var="userdto">
                    <tr>
                        <td><img src="${userdto.imageUrl}" alt="profilePic chat" width="40px"></td>
                        <td>${userdto.username}</td>
                        <td>
                            <a href="chat?id=${userdto.id}&param2=${receiver}" type="submit"
                               class="btn btn-xl btn-link">Chat with me</a>

                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</layout:page-container>





