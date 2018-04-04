<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<head>
<meta charset="UTF-8">
</head>
<body>

<center>
<h1>Rejestracja</h1>


<p align="center">
<c:out value="${message}"/>
</p>

<sf:form id="registerForm" action="/addUser" modelAttribute="user" enctype="multipart/form-data" method="POST">


            <table border="1" width="30%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2">Zarejestruj się</th>
                    </tr>
                </thead>
                <tbody>

                    <tr>
                        <td><s:message code="register.name"/></td>
                        <td><sf:input path="name" /></td>
                    </tr>
                    <tr><sf:errors path="name"/></tr>

                    <tr>
                        <td><s:message code="register.lastName"/></td>
                        <td><sf:input path="lastName" /></td>
                    </tr>
                    <tr><sf:errors path="lastName"/></tr>


                    <tr>
                        <td><s:message code="register.email"/></td>
                        <td><sf:input path="email" /></td>
                    </tr>
                    <tr><sf:errors path="email"/></tr>


                    <tr>
                        <td><s:message code="register.password"/></td>
                        <td><sf:password path="password" /></td>
                    </tr>
                    <tr><sf:errors path="password"/></tr>



                    <tr>
                        <td><input type="submit" value="Zarejestruj" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>

                </tbody>
            </table>


</sf:form>


<a href="/login">Logowanie</a>
</center>
</body>
</html>